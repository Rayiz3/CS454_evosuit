package org.apache.commons.lang3;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class LocaleUtils_LLMTest {
    @Test
    public void test0() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
        try {
            LocaleUtils.toLocale("_G");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_Gb");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_gB");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_1B");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_G1");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GB_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GBAP");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test1() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
    }
    @Test
    public void test2() {
        assertEquals(null, LocaleUtils.toLocale((String) null));

        assertValidToLocale("cn");
        assertValidToLocale("ja");
        assertValidToLocale("it");
        assertValidToLocale("es");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("qq");

        try {
            LocaleUtils.toLocale("PJ");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("pq");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("Adj");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("Jjj$");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("c");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("aaa");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("aa_1");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test3() {
        assertValidToLocale("cn_CN", "cn", "CN");
        //valid though doesnt exist
        assertValidToLocale("us_US", "us", "US");

        try {
            LocaleUtils.toLocale("it-IT");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("de_De");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("ja_ja");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("cn_cN");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("IT_IT");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_u5");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test4() {
        assertValidToLocale("cn_CN_EXT", "cn", "CN", "EXT");
        // this isn't pretty, but was caused by a jdk bug it seems
        // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4210525
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("us_US_az", "us", "US", "az");
            assertValidToLocale("us_US_$FdaqwErasdfasdfasdf", "us", "US", "$FdaqwErasdfasdfasdf");
        } else {
            assertValidToLocale("us_US_az", "us", "US", "AZ");
            assertValidToLocale("us_US_sadsQQasdawqEFWsdfFAdfefdsQ", "us", "US", "SADSQQASDAWQEFWSDFFADFEFDSQ");
        }

        try {
            LocaleUtils.toLocale("us_EN-");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("ms_MY_MY");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test5() {
        assertValidToLocale("_US", "", "US", "");
        assertValidToLocale("_US_Z", "", "US", "Z");
        assertValidToLocale("_US_JP", "", "US", "JP");
        try {
            LocaleUtils.toLocale("_US_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_U1");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("__JP");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test6() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
        
        // Regression tests
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ, LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR_CA, null, new Locale[]{LOCALE_FR_CA, LOCALE_FR, LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_GB, null, new Locale[]{LOCALE_EN_GB, LOCALE_EN});
        assertLocaleLookupList(LOCALE_ES_AR, null, new Locale[]{LOCALE_ES_AR, LOCALE_ES, LOCALE_EN});
    }        
    @Test
    public void test7() {
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, 
                new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN});
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ});
        
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ});
        
        // Regression tests
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, new Locale[]{LOCALE_QQ, LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_GB, LOCALE_FR, new Locale[]{LOCALE_EN_GB, LOCALE_EN, LOCALE_FR});
        assertLocaleLookupList(LOCALE_ES_AR, LOCALE_FR_CA, new Locale[]{LOCALE_ES_AR, LOCALE_ES, LOCALE_FR_CA, LOCALE_FR, LOCALE_EN});
    }
@Test
public void test8() {
    // Adding different locale
    assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
    // Changing the default locale
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_DE, new Locale[]{LOCALE_EN_US, LOCALE_EN, LOCALE_DE});
}
@Test
public void test9() {
    // Changing the default locale
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR_CA, new Locale[]{LOCALE_EN_US, LOCALE_EN, LOCALE_FR_CA});
    // Changing both locale and default locale
    assertLocaleLookupList(LOCALE_FR_CA, LOCALE_ES, new Locale[]{LOCALE_FR_CA, LOCALE_FR, LOCALE_ES});
}
    @Test
    public void test10() {
        assertValidToLocale("_gb", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
        try {
            LocaleUtils.toLocale("_G");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_Gb");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_gB");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_1B");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_G1");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GB_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GBAP");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test11() {
        assertEquals(null, LocaleUtils.toLocale((String) null));

        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("qq");

        try {
            LocaleUtils.toLocale("Us");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("u#");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("u");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("uuu");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("uu_U");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test12() {
        assertValidToLocale("us_EN", "us", "EN");
        //valid though doesnt exist
        assertValidToLocale("us_ZH", "us", "ZH");

        try {
            LocaleUtils.toLocale("us-EN");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_En");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_en");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_eN");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS_EN");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test13() {
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        // this isn't pretty, but was caused by a jdk bug it seems
        // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4210525
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("us_EN_a", "us", "EN", "a");
            assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFsafdFDsdfF");
        } else {
            assertValidToLocale("us_EN_a", "us", "EN", "A");
            assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFSAFDFDSDFF");
        }

        try {
            LocaleUtils.toLocale("us_EN-a");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test14() {
        assertValidToLocale("_gb", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");

        // Input with numbers in the first part
        assertValidToLocale("_123", "", "123", "");

        // Input with numbers in the second part
        assertValidToLocale("_gb_123", "", "GB", "123");

        // Input with numbers in the third part
        assertValidToLocale("_gb_P_123", "", "GB", "P_123");

        // Input with special characters in the first part
        assertValidToLocale("_@#$_123", "", "@#$_123", "");

        // Input with special characters in the second part
        assertValidToLocale("_gb_@#$_123", "", "GB", "@#$_123");

        // Input with special characters in the third part
        assertValidToLocale("_gb_P_@#$_123", "", "GB", "P_@#$_123");
    }
    @Test
    public void test15() {
        assertEquals(null, LocaleUtils.toLocale((String) null));

        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("qq");

        try {
            LocaleUtils.toLocale("Us");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("u#");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("u");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("uuu");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("uu_U");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        // Input with numbers
        assertValidToLocale("123");
        assertValidToLocale("u1");
        assertValidToLocale("uu3");

        // Input with special characters
        assertValidToLocale("@#$");
        assertValidToLocale("u@");
        assertValidToLocale("uu_$");
    }
    @Test
    public void test16() {
        // New input: Locale.JAPAN
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        jdkLocaleSet.add(Locale.JAPAN); // Adding a new locale
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test17() {
        // New input: Locale.JAPAN
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        jdkLocaleSet.add(Locale.JAPAN); // Adding a new locale
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        // New assertion: Confirming Locale.JAPAN is available
        assertEquals(set.contains(Locale.JAPAN), LocaleUtils.isAvailableLocale(Locale.JAPAN));
    }
    @Test
    public void test18() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
        try {
            LocaleUtils.toLocale("_G");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_Gb");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_gB");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_1B");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_G1");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GB_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GBAP");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test19() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("qq");
        
        try {
            LocaleUtils.toLocale("Us");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("u#");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        
        try {
            LocaleUtils.toLocale("u");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
       
        try {
            LocaleUtils.toLocale("uuu");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uu_U");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test20() {
        assertValidToLocale("us_EN", "us", "EN");
        //valid though doesnt exist
        assertValidToLocale("us_ZH", "us", "ZH");
        
        try {
            LocaleUtils.toLocale("us-EN");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_En");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_en");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_eN");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS_EN");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test21() {
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        // this isn't pretty, but was caused by a jdk bug it seems
        // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4210525
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("us_EN_a", "us", "EN", "a");
            assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFsafdFDsdfF");
        } else {
            assertValidToLocale("us_EN_a", "us", "EN", "A");
            assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFSAFDFDSDFF");
        }
        
        try {
            LocaleUtils.toLocale("us_EN-a");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test22() {
        assertValidToLocale("us_EN_abc", "us", "EN", "abc");
        assertValidToLocale("us_EN_DEF", "us", "EN", "DEF");
        assertValidToLocale("us_FR_ghi", "us", "FR", "ghi");
    }
    @Test
    public void test23() {
        assertValidToLocale("us_EN_1abc", "us", "EN", "1abc");
        assertValidToLocale("us_EN_$def", "us", "EN", "$def");
        assertValidToLocale("us_EN_*ghi", "us", "EN", "*ghi");
    }
    @Test
    public void test24() {
        assertValidToLocale("us_EN_A_ABCDE", "us", "EN", "A_ABCDE");
        assertValidToLocale("us_EN_B_DEFGH", "us", "EN", "B_DEFGH");
        assertValidToLocale("us_FR_C_IJKLM", "us", "FR", "C_IJKLM");
    }
    @Test
    public void test25() {
        assertValidToLocale("us_EN_A_abcde", "us", "EN", "A_abcde");
        assertValidToLocale("us_EN_B_defgh", "us", "EN", "B_defgh");
        assertValidToLocale("us_FR_C_ijklm", "us", "FR", "C_ijklm");
    }
    @Test
    public void test26() {
        assertValidToLocale("us_EN_A_1abcde", "us", "EN", "A_1abcde");
        assertValidToLocale("us_EN_B_$defgh", "us", "EN", "B_$defgh");
        assertValidToLocale("us_FR_C_*ijklm", "us", "FR", "C_*ijklm");
    }
    @Test
    public void test27() {
        assertValidToLocale("us_EN_A_ABCDE_FGHIJKL", "us", "EN", "A_ABCDE_FGHIJKL");
        assertValidToLocale("us_EN_B_DEFGH_IJKLMN", "us", "EN", "B_DEFGH_IJKLMN");
        assertValidToLocale("us_FR_C_IJKLM_NOPQRS", "us", "FR", "C_IJKLM_NOPQRS");
    }
    @Test
    public void test28() {
        assertValidToLocale("us_EN_A_ABCDE_fghijk", "us", "EN", "A_ABCDE_fghijk");
        assertValidToLocale("us_EN_B_DEFGH_ijklmn", "us", "EN", "B_DEFGH_ijklmn");
        assertValidToLocale("us_FR_C_IJKLM_nopqrs", "us", "FR", "C_IJKLM_nopqrs");
    }
    @Test
    public void test29() {
        assertValidToLocale("us_EN_A_ABCDE_1fghijk", "us", "EN", "A_ABCDE_1fghijk");
        assertValidToLocale("us_EN_B_DEFGH_$ijklmn", "us", "EN", "B_DEFGH_$ijklmn");
        assertValidToLocale("us_FR_C_IJKLM_*nopqrs", "us", "FR", "C_IJKLM_*nopqrs");
    }
@Test
public void test30() {
    assertLanguageByCountry(null, new String[0]);
    assertLanguageByCountry("GB", new String[]{"en"});
    assertLanguageByCountry("ZZ", new String[0]);
    assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    assertLanguageByCountry("CH1", new String[0]);
    assertLanguageByCountry("CH ", new String[0]);
    assertLanguageByCountry("gB", new String[0]);
    assertLanguageByCountry("gb", new String[]{"en"});
}
    @Test
    public void test31() {
        assertCountriesByLanguage(null, new String[0]); // original test case
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"}); // original test case
        assertCountriesByLanguage("zz", new String[0]); // original test case
        assertCountriesByLanguage("it", new String[]{"IT", "CH"}); // original test case
        
        assertCountriesByLanguage("es", new String[]{}); // additional test case: language code not found
        
        assertCountriesByLanguage("en", new String[]{"US", "GB", "CA", "AU", "NZ", "IN"}); // additional test case: more countries associated with language code "en"
        
        assertCountriesByLanguage("vi", new String[]{"VN"}); // additional test case: only one country associated with language code "vi"
        
        assertCountriesByLanguage("ko", new String[]{}); // additional test case: no countries associated with language code "ko"
    }
}
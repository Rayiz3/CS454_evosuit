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
        
        // additional test cases
        assertValidToLocale("A_GB_POSIX", "A", "GB", "POSIX");
        assertValidToLocale("GB_GB_Posix", "GB", "GB", "Posix");
    }
    @Test
    public void test1() {
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
        
        // additional test cases
        assertValidToLocale("a");
        assertValidToLocale("b");
        assertValidToLocale("cc");
        assertValidToLocale("yy");
        assertValidToLocale("qqq");
        assertValidToLocale("q#");
    }
    @Test
    public void test2() {
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
        
        // additional test cases
        assertValidToLocale("A_B", "A", "B");
        assertValidToLocale("B_x", "B", "X");
        assertValidToLocale("cc_X", "CC", "X");
        assertValidToLocale("yy_y", "YY", "Y");
        assertValidToLocale("qqq_z", "QQQ", "Z");
        assertValidToLocale("q#_z#", "Q#", "Z#");
    }
    @Test
    public void test3() {
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
        
        // additional test cases
        assertValidToLocale("a_B_C", "A", "B", "C");
        assertValidToLocale("B_X_y", "B", "X", "Y");
        assertValidToLocale("cc_EEEE", "CC", "EEEE", "EEE");
        assertValidToLocale("yy_uuuu", "YY", "UUUU", "UUUU");
        assertValidToLocale("qqq_aaaa", "QQQ", "AAAA", "AAAA");
        assertValidToLocale("q#_z#", "Q#", "Z#", "Z#");
    }
    @Test
    public void test4() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        
        // additional test cases
        assertValidToLocale("A_B_C", "A", "B", "C");
        assertValidToLocale("B_C_D", "B", "C", "D");
    }
    private void assertValidToLocale(String str, String l, String c, String v) {
        Locale locale = LocaleUtils.toLocale(str);
        assertEquals("Language code is incorrect", l, locale.getLanguage());
        assertEquals("Country code is incorrect", c, locale.getCountry());
        assertEquals("Variant code is incorrect", v, locale.getVariant());
    }
    @Test
    public void test5() {
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
        // additional regression test case 1
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        // additional regression test case 2
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US});
    }
    @Test
    public void test6() {
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
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
        // additional regression test case 1
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
        // additional regression test case 2
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_FR_CA});
    }
    @Test
    public void test7() {
        assertLocaleLookupList(LOCALE_QQ_ZZ, null, new Locale[]{LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN, LOCALE_QQ});
    }
    @Test
    public void test8() {
        assertLocaleLookupList(LOCALE_QQ_ZZ, LOCALE_QQ_ZZ, 
                new Locale[]{LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ});

        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_QQ,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN,
                LOCALE_QQ});
    }
    @Test
    public void test9() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertUnmodifiableCollection(list);
        
        // Add regression test cases here
        assertValidToLocale("qq");
        assertValidToLocale("it");
        assertValidToLocale("fr_");
        assertValidToLocale("us_F");
        assertValidToLocale("en_US_POSIX");
    }
    @Test
    public void test10() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertUnmodifiableCollection(set);
        
        // Add regression test cases here
        assertTrue(LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertTrue(LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertTrue(LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertFalse(LocaleUtils.isAvailableLocale(LOCALE_ZZ));
        assertFalse(LocaleUtils.isAvailableLocale(LOCALE_ZZ_UU));
        assertTrue(LocaleUtils.isAvailableLocale(LOCALE_IT));
    }
    @Test
    public void test11() {
        assertTrue(LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("zz")));
        
        // Add regression test cases here
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("pq")));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("de_DE")));
        assertTrue(LocaleUtils.isAvailableLocale(new Locale("fr")));
        assertTrue(LocaleUtils.isAvailableLocale(new Locale("pt_PT")));
    }
    @Test
    public void test12() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test13() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Additional test cases
        assertEquals(set.contains(LOCALE_DE), LocaleUtils.isAvailableLocale(LOCALE_DE));  // Kill mutant that removes DE Locale from available locale set
        assertEquals(set.contains(LOCALE_ES), LocaleUtils.isAvailableLocale(LOCALE_ES));  // Kill mutant that removes ES Locale from available locale set
        assertEquals(set.contains(LOCALE_IT), LocaleUtils.isAvailableLocale(LOCALE_IT));  // Kill mutant that removes IT Locale from available locale set
    }
    @Test
    public void test14() {
        Locale locale = new Locale("de", "DE");
        assertFalse(LocaleUtils.isAvailableLocale(locale));
    }
    @Test
    public void test15() {
        Locale locale = new Locale("en", "");
        assertTrue(LocaleUtils.isAvailableLocale(locale));
    }
    @Test
    public void test16() {
        Locale locale = new Locale("", "US");
        assertFalse(LocaleUtils.isAvailableLocale(locale));
    }
    @Test
    public void test17() {
        Locale locale = new Locale("fr", "FR");
        assertTrue(LocaleUtils.isAvailableLocale(locale));
    }
    @Test
    public void test18() {
        Locale locale = new Locale("zz", "");
        assertFalse(LocaleUtils.isAvailableLocale(locale));
    }
 @Test
    public void test19() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("GB", new String[]{"de"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        assertLanguageByCountry("CH", new String[]{"sv", "it"});
    }
    @Test
    public void test20() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});

        assertCountriesByLanguage("", new String[0]);   // additional test case: empty language code
        assertCountriesByLanguage("es", new String[]{"ES"});   // additional test case: valid language code with one matching country
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "LU", "CA"});   // additional test case: valid language code with multiple matching countries
        assertCountriesByLanguage("zh", new String[]{"CN", "HK", "MO", "SG", "TW"});   // additional test case: valid language code with multiple matching countries
        assertCountriesByLanguage("en", new String[]{"US", "GB", "AU", "CA", "IN", "IE", "NZ", "ZA"});   // additional test case: valid language code with multiple matching countries
    }
}
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
        assertLocaleLookupList(new Locale("test"), null, new Locale[]{new Locale("test")});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
    }        
    @Test
    public void test1() {
        assertLocaleLookupList(LOCALE_EN, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN,
                LOCALE_QQ});
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
        assertLocaleLookupList(new Locale("test"), new Locale("test2"), 
                new Locale[]{new Locale("test"), new Locale("test2")});
    }
    @Test
    public void test2() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_ES, null, new Locale[]{LOCALE_ES});
        assertLocaleLookupList(LOCALE_DE, null, new Locale[]{LOCALE_DE});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_FR,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR});
    }        
    @Test
    public void test3() {
        assertLocaleLookupList(LOCALE_FR, LOCALE_FR, 
                new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_ES, LOCALE_ES, 
                new Locale[]{LOCALE_ES});
        assertLocaleLookupList(LOCALE_DE, LOCALE_DE, 
                new Locale[]{LOCALE_DE});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR_CA});
        
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
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_FR,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR_CA});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
    }
    @Test
    public void test4() {
        assertValidToLocale("_GB", "", "GB", ""); // should fail with "_AB"
        assertValidToLocale("_GB_P", "", "GB", "P"); // should fail with "_AB_P"
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX"); // should fail with "_AB_P"
        try {
            LocaleUtils.toLocale("_G"); // should fail with "_F"
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_Gb"); // should fail with "_Ab"
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_gB"); // should fail with "_aB"
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_1B"); // should fail with "_1B"
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_G1"); // should fail with "_F1"
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GB_"); // should fail with "_AB_"
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GBAP"); // should fail with "_ABAP"
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test5() {
        assertEquals(null, LocaleUtils.toLocale((String) null)); // should fail with "AB"
        
        assertValidToLocale("us"); // should fail with "AB"
        assertValidToLocale("fr"); // should fail with "AB"
        assertValidToLocale("de"); // should fail with "AB"
        assertValidToLocale("zh"); // should fail with "AB"
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("qq"); // should fail with "AB"
        
        try {
            LocaleUtils.toLocale("Us"); // should fail with "Us"
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US"); // should fail with "Us"
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS"); // should fail with "Us"
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("u#"); // should fail with "U#"
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        
        try {
            LocaleUtils.toLocale("u"); // should fail with "U"
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
       
        try {
            LocaleUtils.toLocale("uuu"); // should fail with "Uuu"
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("uu_U"); // should fail with "Uu_U"
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test6() {
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
    public void test7() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ}); // should fail with LOCALE_AB
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN}); // should fail with LOCALE_AB
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN}); // should fail with LOCALE_AB
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN}); // should fail with LOCALE_AB
    }        
    @Test
    public void test8() {
        assertNotNull(new LocaleUtils());
        Constructor<?>[] cons = LocaleUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(LocaleUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(LocaleUtils.class.getModifiers()));
    }
    @Test
    public void test9() {
        assertValidToLocale("us_EN", "us", "EN"); // should fail with "ab_EN"
        //valid though doesnt exist
        assertValidToLocale("us_ZH", "us", "ZH"); // should fail with "ab_ZH"
        
        try {
            LocaleUtils.toLocale("us-EN"); // should fail as not underscore
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_En"); // should fail second part not uppercase
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_en"); // should fail second part not uppercase
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_eN"); // should fail second part not uppercase
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS_EN"); // should fail first part not lowercase
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_E3"); // should fail second part not uppercase
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test10() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
    }
    @Test
    public void test11() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test12() {
        assertValidToLocale("us_EN_A", "us", "EN", "A"); // should fail with "AB_EN_A"
        // this isn't pretty, but was caused by a jdk bug it seems
        // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4210525
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("us_EN_a", "us", "EN", "a"); // should fail with "ab_EN_a"
            assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFsafdFDsdfF"); // should fail with "ab_EN_SFsafdFDsdfF"
        } else {
            assertValidToLocale("us_EN_a", "us", "EN", "A"); // should fail with "ab_EN_a"
            assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFSAFDFDSDFF"); // should fail with "ab_EN_SFsafdFDsdfF"
        }
        
        try {
            LocaleUtils.toLocale("us_EN-a"); // should fail as not underscore
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uu_UU_"); // should fail, it must be 3, 5 or 7+ in length
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test13() {
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, 
                new Locale[]{LOCALE_QQ}); // should fail with LOCALE_AB
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN});
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN}); // should fail LOCALE_AB
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ}); // should fail LOCALE_AB
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ}); // should fail LOCALE_AB
        
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN}); // should fail LOCALE_AB
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN}); // should fail LOCALE_AB
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ}); // should fail LOCALE_AB
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ}); // should fail LOCALE_AB
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN}); // should fail LOCALE_ABI
    }
    @Test
    public void test14() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertSame(list, list2);
        assertUnmodifiableCollection(list);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        assertEquals(jdkLocaleList, list);
    }
    @Test
    public void test15() {
        assertValidToLocale("fr__P", "fr", "", "P"); // should fail with "ab__P"
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX"); // should fail with "ab__POSIX"
    }
    @Test
    public void test16() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ)); // should fail with LOCALE_AB
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ)); // should fail with LOCALE_AB
    }
    @Test
    public void test17() {
        // Existing test case
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        
        // Regression test cases
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set2);
        assertSame(set2, set); // compare with the original set
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set2); // compare with the original set
        
        Set<Locale> emptySet = Collections.emptySet();
        Set<Locale> nullSet = null;
        assertThrows(NullPointerException.class, () -> {
            Set<Locale> set3 = LocaleUtils.availableLocaleSet();
            set3.addAll(emptySet); // try to modify the set
        });
        assertThrows(NullPointerException.class, () -> {
            Set<Locale> set4 = LocaleUtils.availableLocaleSet();
            set4.addAll(nullSet); // try to modify the set
        });
    }
    @Test
    public void test18() {
        // Existing test case
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        
        // Regression test cases
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN)); // same input value as before
        
        assertFalse(LocaleUtils.isAvailableLocale(null)); // null input value
        
        Locale unknownLocale = new Locale("en", "XX");
        assertFalse(LocaleUtils.isAvailableLocale(unknownLocale)); // unknown locale
        
        Locale unsupportedLocale = new Locale("aa", "AA", "AA");
        assertFalse(LocaleUtils.isAvailableLocale(unsupportedLocale)); // unsupported locale
        
        assertNotNull(LocaleUtils.isAvailableLocale(LOCALE_EN_US)); // not null output
    }
    @Test
    public void test19() {
        assertTrue(LocaleUtils.isAvailableLocale(Locale.US));
    }
    @Test
    public void test20() {
        assertFalse(LocaleUtils.isAvailableLocale(Locale.JAPAN));
    }
    @Test
    public void test21() {
        assertFalse(LocaleUtils.isAvailableLocale(Locale.GERMAN));
    }
    @Test
    public void test22() {
        assertFalse(LocaleUtils.isAvailableLocale(Locale.KOREA));
    }
    @Test
    public void test23() {
        assertFalse(LocaleUtils.isAvailableLocale(Locale.CHINESE));
    }
    @Test
    public void test24() {
        assertTrue(LocaleUtils.isAvailableLocale(Locale.UK));
    }
    @Test
    public void test25() {
        assertFalse(LocaleUtils.isAvailableLocale(Locale.FRENCH));
    }
    @Test
    public void test26() {
        assertFalse(LocaleUtils.isAvailableLocale(Locale.ITALIAN));
    }
    @Test
    public void test27() {
        assertFalse(LocaleUtils.isAvailableLocale(Locale.CANADA));
    }
    @Test
    public void test28() {
        assertFalse(LocaleUtils.isAvailableLocale(Locale.CHINA));
    }
    @Test
    public void test29() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("", new String[0]);
        assertLanguageByCountry("QQ", new String[0]);
        assertLanguageByCountry("CA", new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test30() {
        // original test cases
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});

        // additional test cases
        assertCountriesByLanguage("en", new String[]{"US", "GB", "AU", "CA", "IE"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CH", "BE", "CA"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO"});
    }
}
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
        
        // Regression Test 1: empty string
        try {
            LocaleUtils.toLocale("");
            fail("Should fail if empty string");
        } catch (IllegalArgumentException iae) {}
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
        
        // Regression Test 2: lowercase lang code
        assertValidToLocale("us_en", "us", "EN");
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
        
        // Regression Test 3: lowercase variant code
        assertValidToLocale("us_EN_a", "us", "EN", "A");
    }
    @Test
    public void test4() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        
        // Regression Test 4: lowercase variant code
        assertValidToLocale("fr__p", "fr", "", "P");
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
        
        // Additional test cases
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_PT, null, new Locale[]{LOCALE_PT});
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
        
        // Additional test cases
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR_CA,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR_CA,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_ES, LOCALE_EN_US_ZZZZ,
            new Locale[]{
                LOCALE_ES,
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
    }
    @Test
    public void test7() {
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
        
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR, null,
                new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR_FR, null,
            new Locale[] {
                LOCALE_FR_FR,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR_FR_CA, null,
            new Locale[] {
                LOCALE_FR_FR_CA,
                LOCALE_FR_FR,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR_FR_CA, null,
            new Locale[] {
                LOCALE_FR_FR_CA,
                LOCALE_FR_FR,
                LOCALE_FR});
    }
    @Test
    public void test8() {
        assertValidToLocale("_GB_", "", "GB", "");
        assertValidToLocale("_GB_P_", "", "GB", "P");
        assertValidToLocale("_GB_POSIX_", "", "GB", "POSIX");
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
            LocaleUtils.toLocale("_GB__");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GBAP_");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test9() {
        assertValidToLocale("us_", "us", "", "");
        assertValidToLocale("fr_", "fr", "", "");
        assertValidToLocale("de_", "de", "", "");
        assertValidToLocale("zh_", "zh", "", "");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("qq_", "qq", "", "");

        try {
            LocaleUtils.toLocale("Us");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("US");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("uS");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("u#");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {
        }

        try {
            LocaleUtils.toLocale("u");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {
        }

        try {
            LocaleUtils.toLocale("uuu");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {
        }

        try {
            LocaleUtils.toLocale("uu_U");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {
        }
    }
    @Test
    public void test10() {
        assertValidToLocale("us_EN_", "us", "EN", "");
        //valid though doesnt exist
        assertValidToLocale("us_ZH_", "us", "ZH", "");

        try {
            LocaleUtils.toLocale("us-EN");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("us_En");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("us_en");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("us_eN");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("uS_EN");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {
        }
    }
    @Test
    public void test11() {
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
        } catch (IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {
        }
    }
    private static void assertValidToLocale(final String localeStr) {
        assertValidToLocale(localeStr, null, null, null);
    }
    private static void assertValidToLocale(final String localeStr, final String language, final String country, final String variant) {
        Locale expected = new Locale(language, country, variant);
        Locale actual = LocaleUtils.toLocale(localeStr);
        assertEquals(expected, actual);
    }
    @Test
    public void test12() {
        Set<Locale> set1 = LocaleUtils.availableLocaleSet(); // test original case
        Set<Locale> set2 = LocaleUtils.availableLocaleSet(); // test original case
        Set<Locale> set3 = LocaleUtils.availableLocaleSet(); // test original case
        Set<Locale> set4 = LocaleUtils.availableLocaleSet(); // test original case
        Set<Locale> set5 = LocaleUtils.availableLocaleSet(); // test original case

        assertNotNull(set1);
        assertNotNull(set2);
        assertNotNull(set3);
        assertNotNull(set4);
        assertNotNull(set5);

        assertSame(set1, set2);
        assertSame(set1, set3);
        assertSame(set1, set4);
        assertSame(set1, set5);

        assertUnmodifiableCollection(set1);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);

        assertEquals(jdkLocaleSet, set1);
        assertEquals(jdkLocaleSet, set2);
        assertEquals(jdkLocaleSet, set3);
        assertEquals(jdkLocaleSet, set4);
        assertEquals(jdkLocaleSet, set5);
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

        assertEquals(set.contains(LOCALE_EN_UK), LocaleUtils.isAvailableLocale(LOCALE_EN_UK)); // add this test case
        assertEquals(set.contains(LOCALE_EN_CA), LocaleUtils.isAvailableLocale(LOCALE_EN_CA)); // add this test case
        assertEquals(set.contains(LOCALE_FR_FR), LocaleUtils.isAvailableLocale(LOCALE_FR_FR)); // add this test case
        assertEquals(set.contains(LOCALE_DE_DE), LocaleUtils.isAvailableLocale(LOCALE_DE_DE)); // add this test case
        assertEquals(set.contains(LOCALE_ES_ES), LocaleUtils.isAvailableLocale(LOCALE_ES_ES)); // add this test case
    }
    @Test
    public void test14() {
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
    public void test15() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});

        // Regression test cases
        assertLanguageByCountry("", new String[0]); // Empty string
        assertLanguageByCountry("US", new String[]{"en"}); // Different country code
        assertLanguageByCountry("CH", new String[]{"de", "it"}); // Changing order of languages
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it", "es"}); // Adding extra language
    }
    @Test
    public void test16() {
        // original test cases
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});

        // additional test cases
        assertCountriesByLanguage("en", new String[]{"US", "GB", "CA"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CA"});
    }
}
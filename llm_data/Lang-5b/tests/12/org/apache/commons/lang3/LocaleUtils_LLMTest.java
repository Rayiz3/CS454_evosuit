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
        // Null input
        try {
            LocaleUtils.toLocale(null);
            fail("Null input should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Less than 2 characters
        try {
            LocaleUtils.toLocale("a");
            fail("Input with less than 2 characters should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Underscore missing after first two characters
        try {
            LocaleUtils.toLocale("usA");
            fail("Input with underscore missing after first two characters should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Lowercase letter after underscore
        try {
            LocaleUtils.toLocale("us_a");
            fail("Input with lowercase letter after underscore should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Non-alphabetic character after underscore
        try {
            LocaleUtils.toLocale("us_1");
            fail("Input with non-alphabetic character after underscore should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Underscore missing after country
        try {
            LocaleUtils.toLocale("usEN");
            fail("Input with underscore missing after country should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Lowercase letter after country underscore
        try {
            LocaleUtils.toLocale("us_En");
            fail("Input with lowercase letter after country underscore should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Non-alphabetic character after country underscore
        try {
            LocaleUtils.toLocale("us_1N");
            fail("Input with non-alphabetic character after country underscore should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Underscore missing after variant
        try {
            LocaleUtils.toLocale("us_ENA");
            fail("Input with underscore missing after variant should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Variant with lowercase letters
        try {
            LocaleUtils.toLocale("us_EN_a");
            fail("Input with variant containing lowercase letters should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }

        // Variant with non-alphabetic characters
        try {
            LocaleUtils.toLocale("us_EN_1");
            fail("Input with variant containing non-alphabetic characters should throw an exception");
        } catch (IllegalArgumentException iae) {
            // Exception is expected
        }
    }
    public void test1() {
        assertLocaleLookupList(LOCALE_FR_CA, null, new Locale[]{LOCALE_FR_CA, LOCALE_FR, LOCALE_EN});
    }
    public void test2() {
        assertLocaleLookupList(null, LOCALE_FR_CA, new Locale[]{LOCALE_FR_CA, LOCALE_FR, LOCALE_EN});
    }
    public void test3() {
        assertLocaleLookupList(null, LOCALE_EN_US_ZZZZ, new Locale[]{LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_EN});
    }
    public void test4() {
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US, new Locale[]{LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_EN});
    }
    public void test5() {
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US_ZZZZ, new Locale[]{LOCALE_EN_US, LOCALE_EN});
    }
    @Test
    public void test6() {
        assertLocaleLookupList(null, null, new Locale[0]); // no change needed, covers null case
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ}); // no change needed, covers single locale case
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN}); // no change needed, covers single locale case
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN}); // no change needed, duplicative test case
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN}); // no change needed, covers locale with country
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN}); // no change needed, covers locale with variant
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ,
            new Locale[]{LOCALE_QQ}); // no change needed, covers preferred locale case
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN,
            new Locale[]{LOCALE_EN}); // no change needed, covers preferred locale case
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US,
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN}); // add Locale.ENGLISH to the expected list
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ}); // add preferred locale to the expected list
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ}); // add preferred locale to the expected list
        
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN}); // no change needed, covers locale with variant
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN}); // no change needed, covers locale with variant
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ}); // no change needed, covers locale with variant and preferred locale
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ}); // no change needed, covers locale with variant and preferred locale
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN}); // no change needed, covers locale with country
        assertLocaleLookupList(LOCALE_HAWAIIAN, LOCALE_EN, 
            new Locale[]{
                LOCALE_HAWAIIAN,
                LOCALE_EN}); // add Locale.ENGLISH to the expected list
    }
    @Test
    public void test7() {
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
    public void test8() {
        assertValidToLocale("_GB_", "", "GB", "");
    }
    @Test
    public void test9() {
        assertValidToLocale("_GB_OL", "", "GB", "OL");
    }
    @Test
    public void test10() {
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
    public void test11() {
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
    public void test12() {
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
    public void test13() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
    }
    @Test
    public void test14() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
        assertNotEquals(new HashSet<Locale>(), set); // Additional assertion
    }
    @Test
    public void test15() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("en", "US"))); // Changed input to a locale not in the set
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
    }
    @Test
    public void test16() {
        assertInvalidToLocale("_GA", "", "GB", ""); // Changed input: "_GB" to "_GA"
        assertInvalidToLocale("_GB_P", "", "GB", "P"); // Changed input: "_GB_P" to "_GB_A"
        assertInvalidToLocale("_GB_POSIX", "", "GB", "POSIX"); // Changed input: "_GB_POSIX" to "_GB_XYZ"
        assertInvalidToLocale("_1234", "", "GB", ""); // Changed input: "_1B" to "_1234"
        assertInvalidToLocale("_G1", "", "GB", ""); // Changed input: "_G1" to "_GA"
    }
    @Test
    public void test17() {
        assertInvalidToLocale("Us", "", "GB", ""); // Changed input: "us" to "Us"
        assertInvalidToLocale("uS", "", "GB", ""); // Changed input: "us" to "uS"
        assertInvalidToLocale("u#", "", "GB", ""); // Changed input: "us" to "u#"
        assertInvalidToLocale("u", "", "GB", ""); // Changed input: "us" to "u"
        assertInvalidToLocale("uuu", "", "GB", ""); // Changed input: "us" to "uuu"
        assertInvalidToLocale("uu_U", "", "GB", ""); // Changed input: "us" to "uu_U"
    }
    @Test
    public void test18() {
        assertInvalidToLocale("us-EN", "", "GB", ""); // Changed input: "us_EN" to "us-EN"
        assertInvalidToLocale("us_En", "", "GB", ""); // Changed input: "us_EN" to "us_En"
        assertInvalidToLocale("us_en", "", "GB", ""); // Changed input: "us_EN" to "us_en"
        assertInvalidToLocale("us_eN", "", "GB", ""); // Changed input: "us_EN" to "us_eN"
        assertInvalidToLocale("uS_EN", "", "GB", ""); // Changed input: "us_EN" to "uS_EN"
        assertInvalidToLocale("us_E3", "", "GB", ""); // Changed input: "us_EN" to "us_E3"
    }
    @Test
    public void test19() {
        assertInvalidToLocale("us_EN-a", "", "GB", ""); // Changed input: "us_EN_A" to "us_EN-a"
        assertInvalidToLocale("uu_UU_", "", "GB", ""); // Changed input: "us_EN_A" to "uu_UU_"
    }
    @Test
    public void test20() {
        assertInvalidToLocale("fr__P", "", "", "P"); // Changed input: "fr" to ""
        assertInvalidToLocale("fr__POSIX", "", "", "POSIX"); // Changed input: "fr" to ""
    }
    @Test
    public void test21() {
        assertLanguageByCountry("", new String[0]);
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("xx", new String[0]);
        assertLanguageByCountry("FR", new String[]{"fr"});
        assertLanguageByCountry("ch", new String[0]);
        assertLanguageByCountry("RU", new String[0]);
    }
    @Test
    public void test22() {
        // Additional test cases to kill more mutants
        assertCountriesByLanguage("", new String[0]); // Empty language code
        assertCountriesByLanguage("en_US", new String[0]); // Language code with country code
        assertCountriesByLanguage("fr", new String[]{"FR", "CH", "BE", "LU"}); // Valid language code with multiple countries
        
        // Original test cases
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
    }
}
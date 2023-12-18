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
        // Valid input
        assertValidToLocale("_GB", "", "GB", "");
        // Mutant 1: Changing the language code to uppercase
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("_GB");
            fail("Must be lowercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        // Mutant 2: Changing the length of the input to 1
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("_G");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        // Mutant 3: Changing the length of the input to 3 and removing the underscore
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("_Gb");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test1() {
        // Valid lowercase input
        assertValidToLocale("us");
        // Mutant 1: Changing the language code to uppercase
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("US");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        // Mutant 2: Changing the length of the input to 1
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("u");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test2() {
        // Valid input
        assertValidToLocale("us_EN", "us", "EN");
        // Mutant 1: Changing the separator from underscore to hyphen
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("us-EN");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        // Mutant 2: Changing the second part to lowercase
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("us_en");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test3() {
        // Valid input
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        // Mutant 1: Changing the separator from underscore to hyphen
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("us_EN-a");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        // Mutant 2: Changing the length of the input to 2
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test4() {
        // Valid input
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        // Mutant 1: Changing the country code from uppercase to lowercase
        // Expected: IllegalArgumentException should be thrown
        try {
            LocaleUtils.toLocale("fr__P");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test5() {
        assertLocaleLookupList(LOCALE_DE, null, new Locale[]{LOCALE_DE});
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_JA, null, new Locale[]{LOCALE_JA});
        assertLocaleLookupList(LOCALE_ES, null, new Locale[]{LOCALE_ES});
    }        
    @Test
    public void test6() {
        assertLocaleLookupList(LOCALE_DE, LOCALE_DE, new Locale[]{LOCALE_DE});
        assertLocaleLookupList(LOCALE_FR, LOCALE_FR, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_JA, LOCALE_JA, new Locale[]{LOCALE_JA});
        assertLocaleLookupList(LOCALE_ES, LOCALE_ES, new Locale[]{LOCALE_ES});
    }
    @Test
    public void test7() {
        // Test case 1
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
        // Test case 2
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN_FR});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN_US_FR});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_FR,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN,
                LOCALE_EN_US});
    }        
    @Test
    public void test8() {
        // Test case 1
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
                LOCALE_EN_US,
                LOCALE_EN});
        // Additional test cases
        // Test case 2
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN_FR});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US_FR});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US_FR,
                LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR_FR,
                LOCALE_EN});
    }
    @Test
    public void test9() {
        assertValidToLocale("_Gb", "", "GB", "");
        assertValidToLocale("_gB_P", "", "GB", "P");
        assertValidToLocale("_GB_POS", "", "GB", "POS");
        try {
            LocaleUtils.toLocale("_g");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GB");
            fail("Must be at least 4 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_1B");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GB_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test10() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("US");
        assertValidToLocale("RU");
        assertValidToLocale("JP");
        assertValidToLocale("BR");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("xx");
        
        try {
            LocaleUtils.toLocale("US_");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("Us");
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
            LocaleUtils.toLocale("UUU");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("UU_U");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test11() {
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
    public void test12() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_XX, null, new Locale[]{LOCALE_XX});
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
    }
    @Test
    public void test13() {
        assertNotNull(new LocaleUtils());
        Constructor<?>[] cons = LocaleUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(LocaleUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(LocaleUtils.class.getModifiers()));
    }
    @Test
    public void test14() {
        assertValidToLocale("US_EN", "US", "EN");
        //valid though doesnt exist
        assertValidToLocale("US_ZH", "US", "ZH");
        
        try {
            LocaleUtils.toLocale("us-EN");
            fail("Should fail as not underscore");
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
            LocaleUtils.toLocale("us_e3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test15() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "LU", "CA"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
    }
    @Test
    public void test16() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("IN", new String[]{"hi", "en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test17() {
        assertValidToLocale("US_EN_A", "US", "EN", "A");
        // this isn't pretty, but was caused by a jdk bug it seems
        // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4210525
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("US_EN_a", "US", "EN", "a");
            assertValidToLocale("US_EN_SFsafdFDsdfF", "US", "EN", "SFsafdFDsdfF");
        } else {
            assertValidToLocale("US_EN_a", "US", "EN", "A");
            assertValidToLocale("US_EN_SFsafdFDsdfF", "US", "EN", "SFSAFDFDSDFF");
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
    public void test18() {
        assertLocaleLookupList(LOCALE_XX, LOCALE_XX, 
                new Locale[]{LOCALE_XX});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN});
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_XX,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_XX});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_XX_YY,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_XX_YY});
        
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
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_XX,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_XX});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_XX_YY,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_XX_YY});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
    }
    @Test
    public void test19() {
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
    public void test20() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POS", "fr", "", "POS");
    }
    @Test
    public void test21() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_XX), LocaleUtils.isAvailableLocale(LOCALE_XX));
        assertEquals(set.contains(LOCALE_XX_YY), LocaleUtils.isAvailableLocale(LOCALE_XX_YY));
    }
    @Test
    public void test22() {
        // Original test case
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        // Additional test cases with different input
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        
        // Test case with empty array
        Locale[] emptyArray = new Locale[0];
        Set<Locale> emptySet = new HashSet<Locale>();
        assertNotEquals(emptySet, LocaleUtils.availableLocaleSet(emptyArray));
        
        // Test case with single locale array
        Locale[] singleLocaleArray = new Locale[1];
        singleLocaleArray[0] = new Locale("fr");
        Set<Locale> singleSet = new HashSet<Locale>(Arrays.asList(singleLocaleArray));
        assertEquals(singleSet, LocaleUtils.availableLocaleSet(singleLocaleArray));
        
        // Test case with jdkLocaleArray as input
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, LocaleUtils.availableLocaleSet(jdkLocaleArray));
    }
    @Test
    public void test23() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        
        // Original test case
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Additional test cases with different input
        
        // Test case with non-existent locale
        assertEquals(set.contains(new Locale("xx")), LocaleUtils.isAvailableLocale(new Locale("xx")));
        
        // Test case with null input
        assertFalse(LocaleUtils.isAvailableLocale(null));
    }
    @Test
    public void test24() {
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
    public void test25() {
        assertEquals(null, LocaleUtils.toLocale((String) null));

        // Additional test cases
        assertValidToLocale("in");
        assertValidToLocale("ja");
        assertValidToLocale("ko");

        try {
            LocaleUtils.toLocale("In");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("IN");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("iN");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("i#");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("uu");
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
    public void test26() {
        assertValidToLocale("us_EN", "us", "EN");
        assertValidToLocale("us_ZH", "us", "ZH");

        // Additional test cases
        assertValidToLocale("th_TH", "th", "TH");
        assertValidToLocale("pl_PL", "pl", "PL");
        assertValidToLocale("pt_PT", "pt", "PT");

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
    public void test27() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});

        // Regression tests

        // Test case with an empty string as countryCode
        assertLanguageByCountry("", new String[]{"ca", "es"});

        // Test case with countryCode containing only whitespace characters
        assertLanguageByCountry("   ", new String[0]);

        // Test case with a non-existent countryCode
        assertLanguageByCountry("XY", new String[0]);

        // Test case with a valid countryCode
        assertLanguageByCountry("US", new String[]{"en"});
    }
    @Test
    public void test28() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        assertCountriesByLanguage("en", new String[]{"US", "GB", "AU", "CA", "NZ", "IE", "ZA"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO", "PE", "VE", "CL"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CA", "BE", "CH", "LU"});
    }
}
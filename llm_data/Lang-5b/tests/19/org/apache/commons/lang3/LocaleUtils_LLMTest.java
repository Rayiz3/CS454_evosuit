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
        // CASE 1: str is null
        try {
            LocaleUtils.toLocale(null);
            fail("str is null");
        } catch (final NullPointerException npe) {
        }

        // CASE 2: len < 2
        try {
            LocaleUtils.toLocale("G");
            fail("len < 2");
        } catch (final IllegalArgumentException iae) {
        }

        // CASE 3: ch1 is not lowercase
        try {
            LocaleUtils.toLocale("gB");
            fail("ch1 is not lowercase");
        } catch (final IllegalArgumentException iae) {
        }

        // CASE 4: len is not 2, 3, 5 or 7
        try {
            LocaleUtils.toLocale("GBACP");
            fail("len is not 2, 3, 5 or 7");
        } catch (final IllegalArgumentException iae) {
        }

        // CASE 5: ch3 and ch4 are not uppercase
        try {
            LocaleUtils.toLocale("GBAp");
            fail("ch3 and ch4 are not uppercase");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test1() {
        // CASE 1: str is null
        assertEquals(null, LocaleUtils.toLocale((String) null));

        // CASE 2: length > 2
        try {
            LocaleUtils.toLocale("abc");
            fail("length > 2");
        } catch (final IllegalArgumentException iae) {
        }

        // CASE 3: str is not lowercase
        try {
            LocaleUtils.toLocale("Us");
            fail("str is not lowercase");
        } catch (final IllegalArgumentException iae) {
        }

        // CASE 4: str contains non-alphabetic characters
        try {
            LocaleUtils.toLocale("u#");
            fail("str contains non-alphabetic characters");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test2() {
        // CASE 1: second part is not uppercase
        try {
            LocaleUtils.toLocale("us_en");
            fail("second part is not uppercase");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test3() {
        // CASE 1: str contains non-alphabetic characters
        try {
            LocaleUtils.toLocale("us_en_");
            fail("str contains non-alphabetic characters");
        } catch (final IllegalArgumentException iae) {
        }

        // CASE 2: length is not 3, 5 or 7
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("length is not 3, 5 or 7");
        } catch (final IllegalArgumentException iae) {
        }
    }
@Test
    public void test4() {
        assertLocaleLookupList(LOCALE_EN_US, null, new Locale[]{LOCALE_ZH_CN});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_DE, null, new Locale[]{LOCALE_IT});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_ZH_CN,
                LOCALE_FR,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_ES,
                LOCALE_PT,
                LOCALE_AM});
    }        
    @Test
    public void test5() {
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ, 
                new Locale[]{LOCALE_ZH_CN});
        assertLocaleLookupList(LOCALE_EN, LOCALE_FR, 
                new Locale[]{LOCALE_FR});
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_ZH_CN,
                LOCALE_FR,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_ZH_CN,
                LOCALE_FR,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_DE,
            new Locale[] {
                LOCALE_ZH_CN,
                LOCALE_FR,
                LOCALE_IT});
        
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_ES,
                LOCALE_PT,
                LOCALE_PT_BR});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
            new Locale[] {
                LOCALE_ES,
                LOCALE_PT,
                LOCALE_AM});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_ES,
            new Locale[] {
                LOCALE_ES,
                LOCALE_PT,
                LOCALE_PT_BR,
                LOCALE_ZH_TW});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_ES,
                LOCALE_PT,
                LOCALE_PT_BR,
                LOCALE_FR_CA});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_ES,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_IT});
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
        assertLocaleLookupList(LOCALE_EN_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_ZZZZ,
                LOCALE_EN, 
                LOCALE_EN_US,
                LOCALE_EN_US_ZZZZ});
        assertLocaleLookupList(LOCALE_EN_CA, null,
            new Locale[] {
                LOCALE_EN_CA,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_QQ_ZZZZ, null,
            new Locale[] {
                LOCALE_QQ_ZZZZ,
                LOCALE_QQ,
                LOCALE_EN_US,
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN});
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
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
        
        // Regression tests
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN_CA,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR, LOCALE_EN,
            new Locale[] {
                LOCALE_FR,
                LOCALE_EN_CA,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR_CA,
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR_CA,
                LOCALE_FR});
    }
    @Test
    public void test8() {
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
        
        // Additional test case to kill more mutants
        try {
            LocaleUtils.toLocale("_GBG");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        
        // Additional test case to kill more mutants
        try {
            LocaleUtils.toLocale("_GB-POSIX");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test9() {
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
        
        // Additional test case to kill more mutants
        try {
            LocaleUtils.toLocale("uu");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test10() {
        // Create an empty Locale array
        Locale[] emptyLocaleArray = new Locale[0];
        
        // Set the AVAILABLE_LOCALE_SET field to emptyLocaleArray
        FieldUtils.writeField(LocaleUtils.class.getDeclaredField("AVAILABLE_LOCALE_SET"), emptyLocaleArray, true);
        
        // Test the method availableLocaleSet()
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertTrue(set.isEmpty());
        
        // Reset the AVAILABLE_LOCALE_SET field to the original value
        FieldUtils.writeField(LocaleUtils.class.getDeclaredField("AVAILABLE_LOCALE_SET"), Locale.getAvailableLocales(), true);
    }
    @Test
    public void test11() {
        // Set the AVAILABLE_LOCALE_SET field to null
        FieldUtils.writeField(LocaleUtils.class.getDeclaredField("AVAILABLE_LOCALE_SET"), null, true);
        
        // Test the method availableLocaleSet()
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertTrue(set.isEmpty());
        
        // Reset the AVAILABLE_LOCALE_SET field to the original value
        FieldUtils.writeField(LocaleUtils.class.getDeclaredField("AVAILABLE_LOCALE_SET"), Locale.getAvailableLocales(), true);
    }
    @Test
    public void test12() {
        // Test the method isAvailableLocale() with a null Locale
        assertFalse(LocaleUtils.isAvailableLocale(null));
    }
    @Test
    public void test13() {
        // Test the method isAvailableLocale() with an unavailable Locale
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("zh_TW")));
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
    public void test16() {
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
    }        
    @Test
    public void test17() {
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
    public void test18() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
    }
    @Test
    public void test19() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test20() {
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
    public void test21() {
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
    }
    @Test
    public void test22() {
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
    public void test23() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
    }
    @Test
    public void test24() {
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
    public void test25() {
        assertLanguageByCountry(null, new String[0]);
    }
    @Test
    public void test26() {
        assertLanguageByCountry("ZZ", new String[0]);
    }
    @Test
    public void test27() {
        assertLanguageByCountry("gb", new String[]{"en"});
    }
    @Test
    public void test28() {
        assertLanguageByCountry("CH_LV", new String[0]);
    }
    @Test
    public void test29() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        
        // Regression tests
        
        // Test with a language code that has no corresponding countries
        assertCountriesByLanguage("fr", new String[0]);
        
        // Test with a language code that has multiple corresponding countries
        assertCountriesByLanguage("en", new String[]{"US", "GB", "AU", "CA"});
        
        // Test with an empty language code
        assertCountriesByLanguage("", new String[0]);
        
        // Test with a language code that is not a string
        assertCountriesByLanguage(123, new String[0]);
    }
}
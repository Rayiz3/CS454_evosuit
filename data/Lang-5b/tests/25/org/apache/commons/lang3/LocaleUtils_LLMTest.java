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
        assertValidToLocale("fu_E", "fu", "", "E");
        assertValidToLocale("fu_EPOSIX", "fu", "", "EPOSIX");
    }
    @Test
    public void test1() {
        assertValidToLocale("up_US", "up", "US");
        //valid though doesnt exist
        assertValidToLocale("up_ES", "up", "ES");
        
        try {
            LocaleUtils.toLocale("up-US");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("up_uS");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("up_us");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("up_Us");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uP_US");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("up_U2");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test2() {
        assertValidToLocale("_NP", "", "NP", "");
        assertValidToLocale("_NP_P", "", "NP", "P");
        assertValidToLocale("_NP_POSIX", "", "NP", "POSIX");
        try {
            LocaleUtils.toLocale("_N");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_Np");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_nP");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_3P");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_NQ");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_NP_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_LPCK");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test3() {
        assertValidToLocale("jp_JP_A", "jp", "JP", "A");
        // this isn't pretty, but was caused by a jdk bug it seems
        // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4210525
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("jp_JP_a", "jp", "JP", "a");
            assertValidToLocale("jp_JP_ZFsavgFsfGa", "jp", "JP", "ZFsavgFsfGa");
        } else {
            assertValidToLocale("jp_JP_a", "jp", "JP", "A");
            assertValidToLocale("jp_JP_ZFsavgFsfGa", "jp", "JP", "ZFSAVGFSFGA");
        }
        
        try {
            LocaleUtils.toLocale("jp_JP-a");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("su_SU_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test4() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("jp");
        assertValidToLocale("it");
        assertValidToLocale("us");
        assertValidToLocale("au");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("cc");
        
        try {
            LocaleUtils.toLocale("Jp");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("JP");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("jP");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("j#");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        
        try {
            LocaleUtils.toLocale("j");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
       
        try {
            LocaleUtils.toLocale("jjj");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("jj_J");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
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
        
        // Regression tests
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR_CA, null, new Locale[]{LOCALE_FR_CA});
        assertLocaleLookupList(LOCALE_ES, null, new Locale[]{LOCALE_ES});
        assertLocaleLookupList(LOCALE_PT_BR, null, new Locale[]{LOCALE_PT_BR});
        assertLocaleLookupList(LOCALE_ZH_CN, null, new Locale[]{LOCALE_ZH_CN});
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
        
        // Regression tests
        assertLocaleLookupList(LOCALE_FR, LOCALE_QQ, new Locale[]{LOCALE_FR, LOCALE_QQ});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN_US, new Locale[]{LOCALE_FR_CA, LOCALE_FR, LOCALE_EN_US});
        assertLocaleLookupList(LOCALE_ES, LOCALE_QQ_ZZ, new Locale[]{LOCALE_ES, LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_PT_BR, LOCALE_EN, new Locale[]{LOCALE_PT_BR, LOCALE_EN});
        assertLocaleLookupList(LOCALE_ZH_CN, LOCALE_QQ, new Locale[]{LOCALE_ZH_CN, LOCALE_QQ});
    }
    @Test
    public void test7() {
        assertLocaleLookupList(null, null, new Locale[0]); // original test case
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ}); // original test case
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN}); // original test case
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN}); // original test case
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
    public void test8() {
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, 
                new Locale[]{LOCALE_QQ}); // original test case
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN}); // original test case
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN}); // original test case
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ}); // original test case
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ}); // original test case
        
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN}); // original test case
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
                LOCALE_QQ}); // additional test case
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ}); // additional test case
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN}); // additional test case
                        
  // New additional test case
  assertLocaleLookupList(LOCALE_QQ_ZZ, null,
            new Locale[]{
                LOCALE_QQ_ZZ,
                LOCALE_QQ});
    }
    @Test
    public void test9() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);

        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
        assertFalse(set.contains(LOCALE_FR_CA));
    }
    @Test
    public void test10() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertSame(list, list2);
        assertUnmodifiableCollection(list);

        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        assertEquals(jdkLocaleList, list);
        assertFalse(list.contains(LOCALE_FR_CA));
    }
    @Test
    public void test11() {
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
        assertFalse(list.contains(LOCALE_FR_CA));
    }
    @Test
    public void test12() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        assertFalse(set.contains(LOCALE_FR_CA));
    }
    @Test
    public void test13() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        assertLanguageByCountry("FR", new String[]{"fr"});
    }
    @Test
    public void test14() {
        Locale[] emptyLocaleArray = new Locale[0];
        LocaleUtils.SyncAvoid.AVAILABLE_LOCALE_SET = new HashSet<>(Arrays.asList(emptyLocaleArray));

        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertTrue(set.isEmpty());
    }
    @Test
    public void test15() {
        LocaleUtils.SyncAvoid.AVAILABLE_LOCALE_SET = null;

        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertTrue(set.isEmpty());
    }
    @Test
    public void test16() {
        assertFalse(LocaleUtils.isAvailableLocale(null));
    }
    @Test
    public void test17() {
        LocaleUtils.SyncAvoid.AVAILABLE_LOCALE_SET = new HashSet<>(Arrays.asList(Locale.getAvailableLocales()));

        assertFalse(LocaleUtils.isAvailableLocale(LOCALE_FA));
    }
    @Test
    public void test18() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        
        // Regression test case to kill the Mutant 1: Changing the input value to null
        assertFalse(LocaleUtils.isAvailableLocale(null));
        
        // Regression test case to kill the Mutant 2: Changing the input value to a non-available locale
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("xx")));
    }
    @Test
    public void test19() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        
        // Regression test case to kill the Mutant 3: Changing the input value to null
        assertFalse(LocaleUtils.isAvailableLocale(null));
        
        // Regression test case to kill the Mutant 4: Changing the input value to a non-available locale
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("xx")));
    }
    @Test
    public void test20() {

        // Regression test case to kill the Mutant 5: Changing the expected output to false
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("us")));
        
        // Regression test case to kill the Mutant 6: Changing the expected output to true
        assertTrue(LocaleUtils.isAvailableLocale(new Locale("en")));
    }
    @Test
    public void test21() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});

        // Regression test case 1: countryCode is empty string
        assertLanguageByCountry("", new String[0]);

        // Regression test case 2: countryCode does not exist in available locales
        assertLanguageByCountry("US", new String[0]);

        // Regression test case 3: countryCode is in lowercase
        assertLanguageByCountry("ch", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test22() {
        // Original test cases
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        
        // Regression test cases
        
        // Changing language code to "th" which is not in the test data.
        // Expected: Empty list
        assertCountriesByLanguage("th", new String[0]);
        
        // Changing language code to "de" which is already tested before.
        // Expected: List of countries ["DE", "CH", "AT", "LU"]
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        
        // Changing language code to "it" which is already tested before.
        // Expected: List of countries ["IT", "CH"]
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        
        // Changing language code to "en" which is not in the test data.
        // Expected: Empty list
        assertCountriesByLanguage("en", new String[0]);
        
        // Changing language code to "fr" which is not in the test data.
        // Expected: Empty list
        assertCountriesByLanguage("fr", new String[0]);
    }
}
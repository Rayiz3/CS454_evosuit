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
        try {
            LocaleUtils.toLocale("_GB");
            fail("Missing language part and underscore after it");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test1() {
        try {
            LocaleUtils.toLocale("__GB_POSIX");
            fail("Missing language part and underscore after it");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test2() {
        try {
            LocaleUtils.toLocale("_gb_GB_POSIX");
            fail("Language part should be in uppercase");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test3() {
        try {
            LocaleUtils.toLocale("us_");
            fail("Missing second part of the locale");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test4() {
        try {
            LocaleUtils.toLocale("us__________");
            fail("Longer locale should not be accepted");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test5() {
        try {
            LocaleUtils.toLocale("us_Gb");
            fail("Second part should be in uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test6() {
        try {
            LocaleUtils.toLocale("_EN");
            fail("Missing first part of the locale");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test7() {
        try {
            LocaleUtils.toLocale("us_EN_f");
            fail("Third part should be in uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test8() {
        try {
            LocaleUtils.toLocale("us_E_");
            fail("Missing third part of the locale");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test9() {
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
        
        // Regression test cases
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR_CA, null, new Locale[]{LOCALE_FR_CA});
        assertLocaleLookupList(LOCALE_IT, null, new Locale[]{LOCALE_IT});
        assertLocaleLookupList(LOCALE_IT_CH, null, new Locale[]{LOCALE_IT_CH});
        assertLocaleLookupList(LOCALE_JA, null, new Locale[]{LOCALE_JA});
    }        
    @Test
    public void test10() {
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
        
        // Regression test cases
        assertLocaleLookupList(LOCALE_JA_JP, LOCALE_EN,
            new Locale[] {
                LOCALE_JA_JP,
                LOCALE_JA,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_IT_CH, LOCALE_EN_US,
            new Locale[] {
                LOCALE_IT_CH,
                LOCALE_IT,
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR, LOCALE_EN_US,
            new Locale[] {
                LOCALE_FR,
                LOCALE_EN_US,
                LOCALE_EN});
    }
    @Test
    public void test11() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR_CA, null,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
    }        
    @Test
    public void test12() {
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, 
                new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR, LOCALE_FR, 
                new Locale[]{LOCALE_FR});
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN_FR_CA,
            new Locale[]{
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR_CA,
            new Locale[]{
                LOCALE_FR_CA,
                LOCALE_FR});
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
    public void test13() {
        assertValidToLocale("_NE", "", "NE", "");
        assertValidToLocale("_NE_P", "", "NE", "P");
        assertValidToLocale("_NE_POSIX", "", "NE", "POSIX");
        try {
            LocaleUtils.toLocale("_N");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_Ne");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_nE");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_1E");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_N1");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_NE_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_NEAP");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test14() {
        assertEquals(null, LocaleUtils.toLocale((String) null));

        assertValidToLocale("ne");
        assertValidToLocale("es");
        assertValidToLocale("pt");
        assertValidToLocale("ja");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("qq");

        try {
            LocaleUtils.toLocale("Ne");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("NE");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("nE");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("n#");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("n");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("nnn");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        try {
            LocaleUtils.toLocale("nn_N");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test15() {
        assertValidToLocale("ne_EN", "ne", "EN");
        //valid though doesnt exist
        assertValidToLocale("ne_ZH", "ne", "ZH");

        try {
            LocaleUtils.toLocale("ne-EN");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("ne_En");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("ne_en");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("ne_eN");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("nE_EN");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("ne_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test16() {
        assertValidToLocale("ne_EN_A", "ne", "EN", "A");
        // this isn't pretty, but was caused by a jdk bug it seems
        // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4210525
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("ne_EN_a", "ne", "EN", "a");
            assertValidToLocale("ne_EN_SFsafdFDsdfF", "ne", "EN", "SFsafdFDsdfF");
        } else {
            assertValidToLocale("ne_EN_a", "ne", "EN", "A");
            assertValidToLocale("ne_EN_SFsafdFDsdfF", "ne", "EN", "SFSAFDFDSDFF");
        }

        try {
            LocaleUtils.toLocale("ne_EN-a");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("nn_NN_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test17() {
        // Regression test 1: Empty set
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        set.clear();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertNotSame(set, set2);
        assertTrue(set.isEmpty());
        assertNotEquals(set, set2);
        
        // Regression test 2: Single element set
        set = LocaleUtils.availableLocaleSet();
        set.clear();
        set.add(Locale.US);
        set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertNotSame(set, set2);
        assertEquals(1, set.size());
        assertEquals(set, set2);
        assertTrue(set.contains(Locale.US));
        assertTrue(set2.contains(Locale.US));
        
        // Original test cases
        set = LocaleUtils.availableLocaleSet();
        set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test18() {
        // Regression test 1: Locale not in set
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        set.remove(LOCALE_EN_US_ZZZZ);
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertFalse(LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        
        // Regression test 2: Locale in set
        set = LocaleUtils.availableLocaleSet();
        set.add(LOCALE_EN_US);
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        
        // Original test cases
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
    }
    @Test
    public void test19() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_DE), LocaleUtils.isAvailableLocale(LOCALE_DE));
        assertEquals(set.contains(LOCALE_ZH_CN), LocaleUtils.isAvailableLocale(LOCALE_ZH_CN));
        assertEquals(set.contains(LOCALE_JA_JP), LocaleUtils.isAvailableLocale(LOCALE_JA_JP));
        assertEquals(set.contains(LOCALE_ES), LocaleUtils.isAvailableLocale(LOCALE_ES));
        assertEquals(set.contains(LOCALE_IT), LocaleUtils.isAvailableLocale(LOCALE_IT));
        assertEquals(set.contains(LOCALE_PT_BR), LocaleUtils.isAvailableLocale(LOCALE_PT_BR));
        assertEquals(set.contains(LOCALE_NL), LocaleUtils.isAvailableLocale(LOCALE_NL));
    }
    @Test
    public void test20() {
        // Original test cases
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});

        // Additional test cases
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("", new String[0]);
        assertLanguageByCountry("FR", new String[]{"fr"});
    }
    @Test
    public void test21() {
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("fr", new String[]{"FR", "BE"});
        assertCountriesByLanguage("es", new String[]{"ES"});
    }
}
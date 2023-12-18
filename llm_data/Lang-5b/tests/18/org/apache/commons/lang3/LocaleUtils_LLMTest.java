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
        // Changing input from "_GB" to "_G"
        assertValidToLocale("_G", "", "GB", "");
        // Changing input from "_GB_P" to "_GB_1"
        assertValidToLocale("_GB_1", "", "GB", "P");
        // Changing input from "_GB_POSIX" to "_GBPO"
        assertValidToLocale("_GBPO", "", "GB", "POSIX");
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
        // Changing input from "us" to "Us"
        assertEquals(null, LocaleUtils.toLocale("Us"));
        
        // Changing input from "fr" to "Fr"
        assertValidToLocale("Fr");
        
        // Changing input from "de" to "De"
        assertValidToLocale("De");
        
        // Changing input from "zh" to "Zu"
        assertValidToLocale("Zu");
        
        // Changing input from "qq" to "Qq"
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("Qq");
        
        try {
            LocaleUtils.toLocale("US");
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
    public void test2() {
        // Changing input from "us_EN" to "us-eN"
        assertValidToLocale("us-eN", "us", "EN");
        // valid though doesnt exist
        // Changing input from "us_ZH" to "us-zH"
        assertValidToLocale("us-zH", "us", "ZH");
        
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
    public void test3() {
        // Changing input from "us_EN_A" to "us+EN+a"
        assertValidToLocale("us+EN+a", "us", "EN", "A");
        // Changing input from "us_EN_a" to "us_EN+z"
        assertValidToLocale("us_EN+z", "us", "EN", "a");
        
         // Changing input from "us_EN_SFsafdFDsdfF" to "us_EN_SFasfdFDsdfF"
        assertValidToLocale("us_EN_SFasfdFDsdfF", "us", "EN", "SFsafdFDsdfF");

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
    public void test4() {
        // Changing input from "fr__P" to "fr__p"
        assertValidToLocale("fr__p", "fr", "", "P");
        // Changing input from "fr__POSIX" to "fr__pOSIX"
        assertValidToLocale("fr__pOSIX", "fr", "", "POSIX");
    }
    @Test
    public void test5() {
        assertLocaleLookupList(null, LOCALE_EN, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(null, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(null, LOCALE_EN_US_ZZZZ, 
            new Locale[]{
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
    }
    @Test
    public void test6() {
        assertLocaleLookupList(LOCALE_QQ, null, 
                new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, null, 
                new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, null, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null, 
            new Locale[]{
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
    }
    @Test
    public void test7() {
        assertLocaleLookupList(LOCALE_QQ, LOCALE_EN_US, 
                new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN, LOCALE_QQ, 
                new Locale[]{
                LOCALE_EN,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
    }
    @Test
    public void test8() {
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
    public void test9() {
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
        assertLocaleLookupList(LOCALE_IT, LOCALE_EN_US,
                new Locale[] {
                        LOCALE_IT,
                        LOCALE_EN_US,
                        LOCALE_EN});
        assertLocaleLookupList(LOCALE_IT_CH, LOCALE_EN_US,
                new Locale[] {
                        LOCALE_IT_CH,
                        LOCALE_IT,
                        LOCALE_EN_US,
                        LOCALE_EN});
        assertLocaleLookupList(LOCALE_ES_MX, LOCALE_EN_US,
                new Locale[] {
                        LOCALE_ES_MX,
                        LOCALE_ES,
                        LOCALE_EN_US,
                        LOCALE_EN});
    }
    @Test
    public void test10() {
        assertValidToLocale("_GB1", "", "GB", "1");
        assertValidToLocale("_GB1_", "", "GB", "1_");
    }
    @Test
    public void test11() {
        assertValidToLocale("us1");
        assertValidToLocale("de1");
        assertValidToLocale("qqq");

        try {
            LocaleUtils.toLocale("US1");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        
        try {
            LocaleUtils.toLocale("u");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
       
        try {
            LocaleUtils.toLocale("uu4");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test12() {
        assertValidToLocale("us1_EN", "us1", "EN");

        try {
            LocaleUtils.toLocale("us-EN");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_En");
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
        assertValidToLocale("us_EN_A1", "us", "EN", "A1");
        
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
        assertValidToLocale("fr__P2", "fr", "", "P2");
        assertValidToLocale("fr__POSIX2", "fr", "", "POSIX2");
    }
    @Test
    public void test15() {
        assertEquals(set.contains(LOCALE_EN2), LocaleUtils.isAvailableLocale(LOCALE_EN2));
        assertEquals(set.contains(LOCALE_EN_US2), LocaleUtils.isAvailableLocale(LOCALE_EN_US2));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ2), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ2));
        assertEquals(set.contains(LOCALE_FR2), LocaleUtils.isAvailableLocale(LOCALE_FR2));
        assertEquals(set.contains(LOCALE_FR_CA2), LocaleUtils.isAvailableLocale(LOCALE_FR_CA2));
        assertEquals(set.contains(LOCALE_QQ3), LocaleUtils.isAvailableLocale(LOCALE_QQ3));
        assertEquals(set.contains(LOCALE_QQ_ZZ3), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ3));
    }
    @Test
    public void test16() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.isEmpty());
    }
    @Test
    public void test17() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        set.clear();
        assertTrue(set.isEmpty());
    }
    @Test
    public void test18() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        set.add(new Locale("en", "US"));
        assertTrue(set.contains(new Locale("en", "US")));
    }
    @Test
    public void test19() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertFalse(set.contains(null));
    }
    @Test
    public void test20() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(new Locale("de")));
    }
    @Test
    public void test21() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(new Locale("en", "GB")));
    }
    @Test
    public void test22() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(new Locale("fr", "FR", "Euro")));
    }
    @Test
    public void test23() {
        assertValidToLocale("_Gb", "", "GB", ""); // Change from "_GB" to "_Gb"
        try {
            LocaleUtils.toLocale("_g");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test24() {
        assertValidToLocale("Us"); // Change from "us" to "Us"
        try {
            LocaleUtils.toLocale("US");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test25() {
        assertLocaleLookupList(null, null, new Locale[1]); // Change from new Locale[0] to new Locale[1]
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
    }
    @Test
    public void test26() {
        assertValidToLocale("us_En", "us", "EN"); // Change from "us_EN" to "us_En"
        try {
            LocaleUtils.toLocale("us_en");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test27() {
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT"}); // Removed "LU"
        assertCountriesByLanguage("zz", new String[0]);
    }
    @Test
    public void test28() {
        assertLanguageByCountry("GB", new String[]{"en"}); // Removed "fr"
        assertLanguageByCountry("ZZ", new String[0]);
    }
    @Test
    public void test29() {
        assertValidToLocale("us_EN_a", "us", "EN", "A"); // Change from "a" to "A"
        try {
            LocaleUtils.toLocale("uu_UU_"); // Change from "uu_U" to "uu_UU_"
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test30() {
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ, new Locale[]{LOCALE_EN_US, LOCALE_EN, LOCALE_QQ}); // Removed LOCALE_QQ_ZZ
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ, new Locale[]{LOCALE_EN_US, LOCALE_EN, LOCALE_QQ_ZZ});
    }
    @Test
    public void test31() {
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
    public void test32() {
        assertValidToLocale("fr__a", "fr", "", "a"); // Change from "P" to "a"
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
    }
    @Test
    public void test33() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_FR)); // Change from LOCALE_EN to LOCALE_FR
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR_CA)); // Change from LOCALE_FR to LOCALE_FR_CA
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
    }
    @Test
    public void test34() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry(null, new String[0]); // regression test: passing null again
        assertLanguageByCountry("GB", new String[]{"et"}); // regression test: changing expected value
        assertLanguageByCountry("GB", new String[]{"et"}); // regression test: changing expected value
        assertLanguageByCountry("GB", new String[]{"et", "fr"}); // regression test: adding another expected value
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("ZZ", new String[0]); // regression test: passing invalid country code again
        assertLanguageByCountry("CH", new String[]{"en", "fr", "it"}); // regression test: changing expected value
        assertLanguageByCountry("CH", new String[]{"en", "fr", "it"}); // regression test: changing expected value
        assertLanguageByCountry("CH", new String[]{"et", "fr", "it"}); // regression test: adding another expected value
    }
    @Test
    public void test35() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CH", "LU"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO", "CL", "PE", "VE", "EC", "GT", "CU", "BO", "DO", "HN", "PY", "NI", "SV", "CR", "NO", "PR", "PA", "UY", "GY", "QT", "GT", "QA", "ON", "XI", "QY", "MI", "WE", "XX", "DZ", "XK", "FO", "XD", "XM", "XL", "XN", "SI", "OO", "SX", "WJ", "FG", "NV", "EW", "QW", "XE", "XD", "SF", "QD", "WO", "VV", "FY", "GW", "HR", "BH", "TG", "KM", "JK", "UA", "BT"});
    }
}
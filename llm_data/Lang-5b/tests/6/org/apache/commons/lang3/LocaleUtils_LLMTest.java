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
        assertValidToLocale("_US", "", "US", "");
        assertValidToLocale("_US_P", "", "US", "P");
        assertValidToLocale("_US_POSIX", "", "US", "POSIX");
        try {
            LocaleUtils.toLocale("_U");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_Us");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_uS");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_1S");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_U1");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_US_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_USAP");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test1() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("US");
        assertValidToLocale("FR");
        assertValidToLocale("DE");
        assertValidToLocale("ZH");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("QQ");
        
        try {
            LocaleUtils.toLocale("uS");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US");
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
        assertValidToLocale("US_EN", "US", "EN");
        //valid though doesnt exist
        assertValidToLocale("US_ZH", "US", "ZH");
        
        try {
            LocaleUtils.toLocale("US-EN");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US_En");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US_EN");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US_eN");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("Us_EN");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("US_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test3() {
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
            LocaleUtils.toLocale("US_EN-a");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test4() {
        assertValidToLocale("FR__P", "FR", "", "P");
        assertValidToLocale("FR__POSIX", "FR", "", "POSIX");
    }
    @Test
    public void test5() {
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
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
    }
    @Test
    public void test7() {
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US_ZZZZ,
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_EN_US_ZZZZ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
            new Locale[]{
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ});
    }
    @Test
    public void test8() {
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
            new Locale[]{
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[]{
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
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
        // Regression tests
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR_CA});
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
        // Regression test
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_QQ_ZZ});
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
        assertEquals(true, LocaleUtils.isAvailableLocale(Locale.ENGLISH));
    }
    @Test
    public void test13() {
        assertEquals(false, LocaleUtils.isAvailableLocale(Locale.JAPANESE));
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
        assertEquals(true, LocaleUtils.isAvailableLocale(Locale.ENGLISH));
    }
    @Test
    public void test16() {
        assertEquals(false, LocaleUtils.isAvailableLocale(Locale.JAPANESE));
    }
    @Test
    public void test17() {
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
    public void test18() {
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("ru", "RU")));
    }
    @Test
    public void test19() {
        assertEquals(true, LocaleUtils.isAvailableLocale(new Locale("es", "ES")));
    }
    @Test
    public void test20() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test21() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(LOCALE_EN));
    }
    @Test
    public void test22() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(LOCALE_EN_US));
    }
    @Test
    public void test23() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(LOCALE_EN_US_ZZZZ));
    }
    @Test
    public void test24() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(LOCALE_FR));
    }
    @Test
    public void test25() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(LOCALE_FR_CA));
    }
    @Test
    public void test26() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(LOCALE_QQ));
    }
    @Test
    public void test27() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(set.contains(LOCALE_QQ_ZZ));
    }
    @Test
    public void test28() {
        // changing the locale to a valid but not available locale
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("de"))); 
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("es")));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("zh")));
    }
    @Test
    public void test29() {
        // changing the locale to a valid but not available locale
        assertEquals(LocaleUtils.toLocale("de"), new Locale("de"));
        assertEquals(LocaleUtils.toLocale("es"), new Locale("es"));
        assertEquals(LocaleUtils.toLocale("zh"), new Locale("zh"));
    }        
    @Test
    public void test30() {
        // changing the second part to an invalid character
        try {
            LocaleUtils.toLocale("us_ENa");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test31() {
        // changing the third part to an invalid character
        try {
            LocaleUtils.toLocale("us_EN_Aa");
            fail("Should fail as not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test32() {
        assertLanguageByCountry(null, new String[0]); // does not cover countryCode != null
        assertLanguageByCountry("GB", new String[]{"en"}); // covers countryCode.equals(locale.getCountry()) == true, locale.getVariant().isEmpty() == true
        assertLanguageByCountry("ZZ", new String[0]); // covers cLanguagesByCountry.get(countryCode) == null
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"}); // covers countryCode.equals(locale.getCountry()) == true, locale.getVariant().isEmpty() == true

        // Regression test cases
        assertLanguageByCountry("", new String[0]); // covers countryCode equals empty String
        assertLanguageByCountry("US", new String[]{"en"}); // covers countryCode not found in availableLocaleList()
        assertLanguageByCountry("XX", new String[0]); // covers countryCode not found in availableLocaleList(), cLanguagesByCountry.get(countryCode) is null
        assertLanguageByCountry("CH", new String[]{"en", "fr", "de", "it"}); // covers countryCode.equals(locale.getCountry()) == true
        
    }
    @Test
    public void test33() {
        assertCountriesByLanguage(null, new String[0]);
    }
    @Test
    public void test34() {
        assertCountriesByLanguage("fr", new String[0]);
    }
    @Test
    public void test35() {
        assertCountriesByLanguage("", new String[0]);
    }
    @Test
    public void test36() {
        assertCountriesByLanguage("xx", new String[0]);
    }
    @Test
    public void test37() {
        assertCountriesByLanguage("en", new String[]{"GB", "US", "AU", "CA", "IE", "NZ", "ZA"});
    }
}
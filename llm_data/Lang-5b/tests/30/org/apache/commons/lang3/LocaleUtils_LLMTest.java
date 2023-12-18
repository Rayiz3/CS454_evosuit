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
        // Mutation test case for null input
        assertEquals(null, LocaleUtils.toLocale((String) null));
        assertEquals(null, LocaleUtils.toLocale(""));

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
    public void test1() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
        
        // Mutation test cases for invalid locale format
        try {
            LocaleUtils.toLocale("");
            fail("Should fail if empty");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("_GBAdd");
            fail("Should fail if length not `3`, `5` or grater than `7`");
        } catch (final IllegalArgumentException iae) {}
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
    public void test2() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
    }
    @Test
    public void test3() {
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
        
        // Mutation test case for invalid locale format
        try {
            LocaleUtils.toLocale("_en");
            fail("3rd character must be `_` if 1st and 2nd character are alphabets");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("_En");
            fail("3rd character must be `_` if 1st and 2nd character are alphabets");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("_eN");
            fail("3rd character must be `_` if 1st and 2nd character are alphabets");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("_1N");
            fail("3rd character must be `_` if 1st and 2nd character are alphabets");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("__N");
            fail("3rd character must be `_` if 1st and 2nd character are alphabets");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test4() {
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
   
        // Mutation test case for invalid locale format
        try {
            LocaleUtils.toLocale("_en_A");
            fail("4th character must be `_` if 1st, 2nd and 3rd character are alphabets");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("_aN_A");
            fail("4th character must be `_` if 1st, 2nd and 3rd character are alphabets");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("_An_A");
            fail("4th character must be `_` if 1st, 2nd and 3rd character are alphabets");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("_AN_A");
            fail("4th character must be `_` if 1st, 2nd and 3rd character are alphabets");
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
        
        // Additional regression tests
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN
            });
        
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
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
        
        // Additional regression tests
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN_US_ZZZ,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
    }
@Test
public void test7() {
    assertLocaleLookupList(null, null, new Locale[0]);
    assertLocaleLookupList(new Locale("en", "US"), null, new Locale[]{new Locale("en", "US")});
    assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN_US, null,
        new Locale[] {
            LOCALE_EN_US,
            LOCALE_EN,
            new Locale("en", "")});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
        new Locale[] {
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN_US,
            LOCALE_EN,
            new Locale("en", "")});
}        
@Test
public void test8() {
    assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, 
            new Locale[]{LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
            new Locale[]{LOCALE_EN});
    
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
        new Locale[]{
            LOCALE_EN_US,
            LOCALE_EN,
            new Locale("")});// Changed LOCALE_EN to new Locale("") to kill more mutants
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
        new Locale[] {
            LOCALE_EN_US,
            LOCALE_EN,
            LOCALE_QQ,
            new Locale("")});// Added new Locale("") to kill more mutants
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
        new Locale[] {
            LOCALE_EN_US,
            LOCALE_EN,
            LOCALE_QQ_ZZ,
            new Locale("")});// Added new Locale("") to kill more mutants
    
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
        new Locale[] {
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN_US,
            LOCALE_EN,
            new Locale("")});// Changed LOCALE_EN to new Locale("") to kill more mutants
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
        new Locale[] {
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN_US,
            LOCALE_EN,
            new Locale("")});// Changed LOCALE_EN to new Locale("") to kill more mutants
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
        new Locale[] {
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN_US,
            LOCALE_EN,
            LOCALE_QQ,
            new Locale("")});// Changed LOCALE_QQ_ZZ to LOCALE_QQ and added new Locale("") to kill more mutants
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
        new Locale[] {
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN_US,
            LOCALE_EN,
            LOCALE_QQ_ZZ,
            new Locale("")});// Changed LOCALE_EN to new Locale("") and added new Locale("") to kill more mutants
    assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
        new Locale[] {
            LOCALE_FR_CA,
            LOCALE_FR,
            LOCALE_EN});
}
    @Test
    public void test9() {
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        //Add a new locale to the array to create a different array
        Locale extraLocale = new Locale("xx", "XX");
        Locale[] newJdkLocaleArray = Arrays.copyOf(jdkLocaleArray, jdkLocaleArray.length + 1);
        newJdkLocaleArray[jdkLocaleArray.length] = extraLocale;
        
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertSame(list, list2);
        assertUnmodifiableCollection(list);
        
        assertEquals(jdkLocaleList, list);
        //Add the extra locale to the expected list
        List<Locale> expectedList = new ArrayList<Locale>(jdkLocaleList);
        expectedList.add(extraLocale);
        assertEquals(expectedList, list);
    }
    @Test
    public void test10() {
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        //Add a new locale to the array to create a different array
        Locale extraLocale = new Locale("xx", "XX");
        Locale[] newJdkLocaleArray = Arrays.copyOf(jdkLocaleArray, jdkLocaleArray.length + 1);
        newJdkLocaleArray[jdkLocaleArray.length] = extraLocale;
        
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        assertEquals(jdkLocaleSet, set);
        //Add the extra locale to the expected set
        Set<Locale> expectedSet = new HashSet<Locale>(jdkLocaleSet);
        expectedSet.add(extraLocale);
        assertEquals(expectedSet, set);
    }
    @Test
    public void test11() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(new Locale("en")), LocaleUtils.isAvailableLocale(new Locale("en")));
        assertEquals(set.contains(new Locale("en", "US")), LocaleUtils.isAvailableLocale(new Locale("en", "US")));
        assertEquals(set.contains(new Locale("en", "US", "ZZZZ")), LocaleUtils.isAvailableLocale(new Locale("en", "US", "ZZZZ")));
        assertEquals(set.contains(new Locale("fr")), LocaleUtils.isAvailableLocale(new Locale("fr")));
        assertEquals(set.contains(new Locale("fr", "CA")), LocaleUtils.isAvailableLocale(new Locale("fr", "CA")));
        assertEquals(set.contains(new Locale("qq")), LocaleUtils.isAvailableLocale(new Locale("qq")));
        assertEquals(set.contains(new Locale("qq", "ZZ")), LocaleUtils.isAvailableLocale(new Locale("qq", "ZZ")));
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
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("en_US")));
    }
    @Test
    public void test14() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("fr_FR")));
    }
    @Test
    public void test15() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("de_DE")));
    }
    @Test
    public void test16() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("zh_CN")));
    }
    @Test
    public void test17() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("pt_BR")));
    }
    @Test
    public void test18() {
        assertTrue(LocaleUtils.isAvailableLocale(new Locale("it_IT")));
    }
    @Test
    public void test19() {
        assertTrue(LocaleUtils.isAvailableLocale(new Locale("es_ES")));
    }
    @Test
    public void test20() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("ru_RU")));
    }
    @Test
    public void test21() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("ja_JP")));
    }
    @Test
    public void test22() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("ko_KR")));
    }
@Test
    public void test23() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        
        // regression test cases
        
        // countryCode is empty string
        assertLanguageByCountry("", new String[0]);
        
        // countryCode is valid but not in the availableLocaleList
        assertLanguageByCountry("US", new String[0]);
        
        // countryCode is valid and has variant in Locale
        assertLanguageByCountry("GB_zz", new String[0]);
        
        // countryCode is valid and has multiple variants in Locale
        assertLanguageByCountry("GB_zz_YY", new String[0]);
    }
    @Test
    public void test24() {
        // original test cases
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});

        // additional test cases
        assertCountriesByLanguage("", new String[0]);
        assertCountriesByLanguage("en", new String[0]);
        assertCountriesByLanguage("de_DE", new String[0]);
        assertCountriesByLanguage("DE", new String[]{"DE"});
        assertCountriesByLanguage("fr", new String[]{"FR", "LU"});
    }
}
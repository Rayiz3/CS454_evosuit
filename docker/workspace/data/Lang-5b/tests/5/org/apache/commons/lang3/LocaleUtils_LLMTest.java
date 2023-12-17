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
        // Regression test 1: Changing the first character of the locale to a non-lowercase character
        try {
            LocaleUtils.toLocale("FG");
            fail("Must start with a lowercase character");
        } catch (final IllegalArgumentException iae) {
        }

        // Regression test 2: Changing the third character of the locale to a non-underscore character
        try {
            LocaleUtils.toLocale("enGB");
            fail("Invalid format");
        } catch (final IllegalArgumentException iae) {
        }

        // Regression test 3: Changing the fourth character of the locale to a non-uppercase character
        try {
            LocaleUtils.toLocale("en_gb");
            fail("Invalid format");
        } catch (final IllegalArgumentException iae) {
        }

        // Regression test 4: Changing the sixth character of the locale to a non-underscore character
        try {
            LocaleUtils.toLocale("en_GB.P");
            fail("Invalid format");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test1() {
        // Regression test 1: Providing an empty string as input
        assertEquals(null, LocaleUtils.toLocale(""));

        // Regression test 2: Providing a single character string as input
        try {
            LocaleUtils.toLocale("u");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {
        }

        // Regression test 3: Providing a three-character string as input
        try {
            LocaleUtils.toLocale("usa");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {
        }

        // Regression test 4: Changing the second character of the locale to a non-lowercase character
        try {
            LocaleUtils.toLocale("usA");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {
        }

        // Regression test 5: Changing the second character of the locale to a non-alphabetic character
        try {
            LocaleUtils.toLocale("u#");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {
        }
    }
    @Test
    public void test2() {
        // Regression test 1: Using a hyphen instead of an underscore between the language and country parts
        try {
            LocaleUtils.toLocale("us-EN");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {
        }

        // Regression test 2: Changing the second part to a non-uppercase character
        try {
            LocaleUtils.toLocale("us_en");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {
        }

        // Regression test 3: Changing the second part to a non-alphabetic character
        try {
            LocaleUtils.toLocale("us_3N");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {
        }
    }
    @Test
    public void test3() {
        // Regression test 1: Using a hyphen instead of an underscore between the first and second parts
        try {
            LocaleUtils.toLocale("us-EN-A");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {
        }

        // Regression test 2: Providing a four-character string as input
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {
        }
    }
    @Test
    public void test4() {
        // Regression test 1: Changing the third character of the locale to a non-underscore character
        assertValidToLocale("usU_P", "us", "U", "P");

        // Regression test 2: Changing the third character of the locale to a non-underscore character
        assertValidToLocale("usU_POSIX", "us", "U", "POSIX");
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
        
        // Additional Test Cases
        assertLocaleLookupList(LOCALE_EN, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_FR});
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
        
        // Additional Test Cases
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ, 
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_QQ_ZZ});
    }
    @Test
    public void test7() {
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
        // Regression test case 1: Change the input locale with LOCALE_FR_CA
        assertLocaleLookupList(LOCALE_FR_CA, null,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
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
        // Regression test case 2: Change the input defaultLocale with LOCALE_FR
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
    }
    @Test
    public void test9() {
        assertValidToLocale("_FR", "", "FR", "");
        assertValidToLocale("_FR_P", "", "FR", "P");
        assertValidToLocale("_FR_POSIX", "", "FR", "POSIX");
        try {
            LocaleUtils.toLocale("_F");
            fail("Must be at least 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_Fr");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_fR");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_2R");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_F2");
            fail("Must be letter if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_FR_");
            fail("Must be at least 5 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_FRAP");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
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
            LocaleUtils.toLocale("uu_F");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test11() {
        assertValidToLocale("us_FR", "us", "FR");
        //valid though doesnt exist
        assertValidToLocale("us_CZ", "us", "CZ");
        
        try {
            LocaleUtils.toLocale("us-FR");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_Fr");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_fr");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_fR");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS_FR");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_F3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test12() {
        assertValidToLocale("us_FR_A", "us", "FR", "A");
        // this isn't pretty, but was caused by a jdk bug it seems
        // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4210525
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("us_FR_a", "us", "FR", "a");
            assertValidToLocale("us_FR_SFsafdFDsdfF", "us", "FR", "SFsafdFDsdfF");
        } else {
            assertValidToLocale("us_FR_a", "us", "FR", "A");
            assertValidToLocale("us_FR_SFsafdFDsdfF", "us", "FR", "SFSAFDFDSDFF");
        }
        
        try {
            LocaleUtils.toLocale("us_FR-a");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uuU");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test13() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
    }
    @Test
    public void test14() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
    }
    @Test
    public void test15() {
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
    public void test16() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        
        // Change input to coverage different code paths
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_DE)); // Changed input
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_DE_DE)); // Changed input
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_DE_DE_DE)); // Changed input
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_EN_US)); // Changed input
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
    }
@Test
public void test17() {
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
    assertValidToLocale("_GB", "", "GB", "A");
    assertValidToLocale("_GB", "", "GB", "AA");
    assertValidToLocale("_GB", "", "GB", "AAAAAAAAAAAAAAAAAAAAAAAA");
    assertValidToLocale("_GB", "", "GB", "_AAAAAA");
}
@Test
public void test18() {
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
    
    assertValidToLocale("us", "A");
    assertValidToLocale("us", "AA");
    assertValidToLocale("us", "AAAAAAAAAAAAAAAAAAAAAAAA");
    assertValidToLocale("us", "_AAAAAA");
}        
@Test
public void test19() {
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
    assertValidToLocale("us_EN", "us", "EN", "A");
    assertValidToLocale("us_EN", "us", "EN", "AA");
    assertValidToLocale("us_EN", "us", "EN", "AAAAAAAAAAAAAAAAAAAAAAAA");
    assertValidToLocale("us_EN", "us", "EN", "_AAAAAA");
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
    assertValidToLocale("us_EN_A", "us", "EN", "AA");
    assertValidToLocale("us_EN_A", "us", "EN", "AAAAAAAAAAAAAAAAAAAAAAAA");
    assertValidToLocale("us_EN_A", "us", "EN", "_AAAAAA");
}
private static void assertValidToLocale(final String localeStr, final String language, final String country) {
    LocaleUtils.toLocale(localeStr);
    assertEquals(new Locale(language, country), LocaleUtils.toLocale(localeStr));
}
private static void assertValidToLocale(final String localeStr, final String language, final String country, final String variant) {
    LocaleUtils.toLocale(localeStr);
    assertEquals(new Locale(language, country, variant), LocaleUtils.toLocale(localeStr));
}
    @Test
    public void test21() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("MX", new String[]{"es"});
    }
    @Test
    public void test22() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        assertCountriesByLanguage("ja", new String[]{"JP"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR"});
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "LU", "CA", "CH"});
    }
}
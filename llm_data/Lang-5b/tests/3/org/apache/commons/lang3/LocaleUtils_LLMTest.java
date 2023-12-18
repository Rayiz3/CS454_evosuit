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
        
        try {
            LocaleUtils.toLocale((String)null);
            fail("String is null");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("U");
            fail("Must be 2 chars if less than 5");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("UU");
            fail("Must be 2 chars if less than 5");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("UU_");
            fail("Must be 2 chars if less than 5");
        } catch (final IllegalArgumentException iae) {
        }
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
        try {
            LocaleUtils.toLocale("us_EN-a");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("fr__P");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("fr__POSIX");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
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
        
        try {
            LocaleUtils.toLocale("u=");
            fail("Should fail if not lowercase");
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
        try {
            LocaleUtils.toLocale("us_E-");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
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
        try {
            LocaleUtils.toLocale("us_EN-S");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test4() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        
        try {
            LocaleUtils.toLocale("fr_P");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("fr_3P");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
@Test
public void test5() {
    assertLocaleLookupList(LOCALE_EN_US, null,
        new Locale[]{
            LOCALE_EN_US,
            LOCALE_EN});
}
@Test
public void test6() {
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US,
        new Locale[]{
            LOCALE_EN_US,
            LOCALE_EN});
}
    @Test
    public void test7() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_FR, null,
            new Locale[] {
                LOCALE_EN_FR,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_EN_FR});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_EN_FR});
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
                LOCALE_EN_FR});
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
                LOCALE_EN,
                LOCALE_EN_FR});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_EN_FR});
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
                LOCALE_EN,
                LOCALE_EN_FR});
    }
    @Test
    public void test9() {
        assertValidToLocale("_GB", "", "GB", "");//change "_GB" to ""
        assertValidToLocale("_GB_P", "", "GB", "P");//change "_GB_P" to ""
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");//change "_GB_POSIX" to ""
        // Add more regression tests
    }
    @Test
    public void test10() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("us");//change "us" to "US"
        assertValidToLocale("fr_HELLO");//change "fr_HELLO" to "fr"
        assertValidToLocale("de_ST");//change "de_ST" to "de"
        assertValidToLocale("zh_CN");//change "zh_CN" to "zh"
        assertValidToLocale("qq"); //add invalid language
        // Add more regression tests
    }        
    @Test
    public void test11() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_EN_UK), LocaleUtils.isAvailableLocale(LOCALE_EN_UK));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        assertEquals(set.contains(LOCALE_ES), LocaleUtils.isAvailableLocale(LOCALE_ES));
    }
    @Test
    public void test12() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
        
        assertInvalidToLocale("_G");
        assertInvalidToLocale("_Gb");
        assertInvalidToLocale("_gB");
        assertInvalidToLocale("_1B");
        assertInvalidToLocale("_G1");
        assertInvalidToLocale("_GB_");
        assertInvalidToLocale("_GBAP");
    }
    @Test
    public void test13() {
        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        assertValidToLocale("qq");
        
        assertInvalidToLocale("Us");
        assertInvalidToLocale("US");
        assertInvalidToLocale("uS");
        assertInvalidToLocale("u#");
        assertInvalidToLocale("u");
        assertInvalidToLocale("uuu");
        assertInvalidToLocale("uu_U");
    }
    @Test
    public void test14() {
        assertValidToLocale("us_EN", "us", "EN");
        assertValidToLocale("us_ZH", "us", "ZH");
        
        assertInvalidToLocale("us-EN");
        assertInvalidToLocale("us_En");
        assertInvalidToLocale("us_en");
        assertInvalidToLocale("us_eN");
        assertInvalidToLocale("uS_EN");
        assertInvalidToLocale("us_E3");
    }
    @Test
    public void test15() {
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        
        assertInvalidToLocale("us_EN-a");
        assertInvalidToLocale("uu_UU_");
    }
    private void assertValidToLocale(String localeString, String expectedLanguage, String expectedCountry, String expectedVariant) {
        Locale locale = LocaleUtils.toLocale(localeString);
        assertEquals(expectedLanguage, locale.getLanguage());
        assertEquals(expectedCountry, locale.getCountry());
        assertEquals(expectedVariant, locale.getVariant());
    }
    private void assertInvalidToLocale(String invalidLocaleString) {
        try {
            LocaleUtils.toLocale(invalidLocaleString);
            fail("Invalid locale string should throw exception");
        } catch (IllegalArgumentException iae) {
            // Expected exception
        }
    }
    @Test
    public void test16() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        
        // Regression tests
        assertLanguageByCountry("AR", new String[]{"es"});
        assertLanguageByCountry("JP", new String[]{"ja"});
    }
    @Test
    public void test17() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        
        // Regression tests
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "CH", "LU"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO"});
    }
}
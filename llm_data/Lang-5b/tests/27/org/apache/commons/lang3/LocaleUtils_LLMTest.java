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
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");

        // Additional test cases
        assertValidToLocale("F4__P", "F4", "", "P");
        assertValidToLocale("f3__POSIX", "f3", "", "POSIX");
    }
    @Test
    public void test1() {
        assertValidToLocale("us_EN", "us", "EN");
        //valid though doesnt exist
        assertValidToLocale("us_ZH", "us", "ZH");

        // Additional test cases
        assertValidToLocale("fr_A2", "fr", "A2");
        assertValidToLocale("au_BG", "au", "BG");
    }  
    @Test
    public void test2() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");

        // Additional test cases
        assertValidToLocale("_AU", "", "AU", "");
        assertValidToLocale("_NZ_T", "", "NZ", "T");
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
        
        // Additional test cases
        assertValidToLocale("th_TH_ภาษาอังกฤษ", "th", "TH", "ภาษาอังกฤษ");
        assertValidToLocale("de_DE_Österreich", "de", "DE", "Österreich");
    }
    @Test
    public void test4() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        // Valid format but lang doesnt exist, should make instance anyway
        assertValidToLocale("qq");

        // Additional test cases
        assertValidToLocale("gg");
        assertValidToLocale("jj");
        assertValidToLocale("kk");
        assertValidToLocale("ss");
    }
    @Test
    public void test5() {
        assertLocaleLookupList(LOCALE_ZH, null, new Locale[]{LOCALE_ZH});
        assertLocaleLookupList(LOCALE_PT, null, new Locale[]{LOCALE_PT});
        assertLocaleLookupList(LOCALE_DE, null, new Locale[]{LOCALE_DE});
    }
    @Test
    public void test6() {
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR_CA,
            new Locale[]{
                LOCALE_FR_CA,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_DE,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_DE});
        assertLocaleLookupList(LOCALE_JA_JP, LOCALE_JA,
            new Locale[] {
                LOCALE_JA_JP,
                LOCALE_JA});
    }
@Test
public void test7() {
    assertLocaleLookupList(null, null, new Locale[0]);
    assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
    assertLocaleLookupList(LOCALE_ES, null, new Locale[]{LOCALE_ES});
    assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
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
            new Locale[]{LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
            new Locale[]{LOCALE_EN});
    assertLocaleLookupList(LOCALE_ES, LOCALE_ES, 
            new Locale[]{LOCALE_ES});
    assertLocaleLookupList(LOCALE_FR, LOCALE_FR, 
            new Locale[]{LOCALE_FR});
    
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
    assertLocaleLookupList(LOCALE_ES_MX, LOCALE_EN,
        new Locale[] {
            LOCALE_ES_MX,
            LOCALE_ES,
            LOCALE_EN});
    assertLocaleLookupList(LOCALE_FR_CA_QQ_ZZ, LOCALE_EN,
        new Locale[] {
            LOCALE_FR_CA_QQ_ZZ,
            LOCALE_FR_CA_QQ,
            LOCALE_FR_CA,
            LOCALE_FR,
            LOCALE_EN});
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
    }        
    @Test
    public void test12() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
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
    public void test15() {
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
    public void test16() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
    }
    @Test
    public void test17() {
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
    public void test18() {
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
    public void test19() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test20() {
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
    public void test23() {
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
    public void test24() {
        // Test with an available locale
        assertFalse(LocaleUtils.isAvailableLocale(Locale.CHINA));
        
        // Test with an unavailable locale
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("zzz")));
    }
    @Test
    public void test25() {
        // Original tests
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});

        // Additional tests
        assertLanguageByCountry("", new String[0]);
        assertLanguageByCountry("US", new String[0]);
        assertLanguageByCountry("CA", new String[]{"en", "fr"});
    }
    @Test
    public void test26() {
        //existing test cases
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        
        //additional test cases
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "CA", "CH", "LU"});
        assertCountriesByLanguage("es", new String[]{"ES", "AR", "MX", "CO", "CL", "EC", "PE", "VE"});
    }
}
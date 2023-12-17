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
        assertValidToLocale("_G#", null, null, null);
        assertValidToLocale("_GBA", null, null, null);
        assertValidToLocale("_23", null, null, null);
        assertValidToLocale("_Gg", null, null, null);
        assertValidToLocale("_Jjjj", null, null, null);
    }
    @Test
    public void test1() {
        assertValidToLocale("Uu", null, null, null);
        assertValidToLocale("us2", null, null, null);
        assertValidToLocale("u$", null, null, null);
    }
    @Test
    public void test2() {
        assertValidToLocale("us_En", null, null, null);
        assertValidToLocale("Us_De", null, null, null);
        assertValidToLocale("Fr_E3", null, null, null);
    }
    @Test
    public void test3() {
        assertValidToLocale("us_EN_Aa", null, null, null);
        assertValidToLocale("US_AB_C", null, null, null);
        assertValidToLocale("zu_12_345", null, null, null);
    }
    @Test
    public void test4() {
        assertValidToLocale("fr__P_", null, null, null);
        assertValidToLocale("fr__Po", null, null, null);
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
       
        // regression test cases
        assertLocaleLookupList(LOCALE_ZZ, null, new Locale[]{LOCALE_ZZ});
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_ES_ES, null, new Locale[]{LOCALE_ES_ES});
        assertLocaleLookupList(LOCALE_PT_BR, null, new Locale[]{LOCALE_PT_BR});
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
       
        // regression test cases
        assertLocaleLookupList(LOCALE_EN, LOCALE_ES, new Locale[]{LOCALE_EN, LOCALE_ES});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_ES_ES, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_ES_ES});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR_CA_ZZZZ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR_CA_ZZZZ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_FR_CA_ZZZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR_CA_ZZZZ});
    }
    @Test
    public void test7() {
        // original tests
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

        // regression tests
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN_US, new Locale[]{LOCALE_EN, LOCALE_EN_US});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ, new Locale[]{LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_EN, LOCALE_QQ});
    }
    @Test
    public void test8() {
        // original tests
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, new Locale[]{LOCALE_EN_US, LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ, new Locale[]{LOCALE_EN_US, LOCALE_EN, LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ, new Locale[]{LOCALE_EN_US, LOCALE_EN, LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null, new Locale[]{LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ, new Locale[]{LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ, new Locale[]{LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_EN, LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ, new Locale[]{LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_EN, LOCALE_QQ_ZZ});
        
        // regression tests
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR, new Locale[]{LOCALE_FR_CA, LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN_US_ZZZZ, new Locale[]{LOCALE_FR_CA, LOCALE_FR, LOCALE_EN_US_ZZZZ});
    }
    @Test
    public void test9() {
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
        
        // Regression tests
        try {
            LocaleUtils.toLocale("_GBZ");
            fail("Must not be more than 3 chars if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_G_B");
            fail("Must not have underscore in the middle if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_GB__P");
            fail("Must not have consecutive underscores if starts with underscore");
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
            LocaleUtils.toLocale("uu_U");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
        
         // Regression tests
        try {
            LocaleUtils.toLocale("u_");
            fail("Must not have underscore at the end if it is 2 chars");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("kg");
        } catch (IllegalArgumentException iae) {
             fail("Should not fail if it is 2 chars");
        }
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
        
        // Regression test
        assertValidToLocale("kg");
    }
    @Test
    public void test12() {
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
        assertLocaleLookupList(LOCALE_QQ_ZZ, null, new Locale[]{LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_AA, null, new Locale[]{LOCALE_AA});
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
        
        // Regression tests
        try {
            LocaleUtils.toLocale("us_ENG");
            fail("Should fail as not 2 or 3 chars");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us__EN");
            fail("Should fail as not 2 or 3 chars");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test15() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        
        // Regression tests
        assertCountriesByLanguage("aa", new String[]{"DJ", "ER", "ET"});
        assertCountriesByLanguage("zzz", new String[0]);
    }
    @Test
    public void test16() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        
        // Regression tests
        assertLanguageByCountry("ER", new String[]{"ti", "ar", "en"});
        assertLanguageByCountry("SS", new String[]{"en"});
        assertLanguageByCountry("ZZZ", new String[0]);
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
        
        // Regression tests
        try {
            LocaleUtils.toLocale("us_EN__P");
            fail("Should fail as not 3 or 5 chars");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_EN_3A");
            fail("Should fail as not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test18() {
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
        assertLocaleLookupList(LOCALE_ZZ, null, new Locale[]{LOCALE_ZZ});
        assertLocaleLookupList(LOCALE_IT_CH, LOCALE_DE_AT, 
            new Locale[] {
                LOCALE_IT_CH,
                LOCALE_DE_CH,
                LOCALE_FR_CH,
                LOCALE_DE,
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
        
        // Regression tests
        assertValidToLocale("zz");
        assertValidToLocale("kg");
        assertValidToLocale("zzz");
    }
    @Test
    public void test20() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        
        // Regression tests
        assertValidToLocale("fr_", "fr", "", "");
        assertValidToLocale("fr_aaaa", "fr", "", "aaaa");
    }
    @Test
    public void test21() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Regression tests
        assertEquals(set.contains(LOCALE_AA), LocaleUtils.isAvailableLocale(LOCALE_AA));
        assertEquals(set.contains(LOCALE_ZZ), LocaleUtils.isAvailableLocale(LOCALE_ZZ));
        assertEquals(set.contains(LOCALE_IT_CH), LocaleUtils.isAvailableLocale(LOCALE_IT_CH));
        assertEquals(set.contains(LOCALE_DE_AT), LocaleUtils.isAvailableLocale(LOCALE_DE_AT));
    }
    @Test
    public void test22() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(new HashSet<Locale>(), set);
    }
    @Test
    public void test23() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        set2.remove(LOCALE_EN);
        set2.add(LOCALE_DE);
        assertNotSame(set, set2);
        assertUnmodifiableCollection(set2);
    }
    @Test
    public void test24() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(LocaleUtils.isAvailableLocale(LOCALE_INVALID));
    }
    @Test
    public void test25() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertFalse(LocaleUtils.isAvailableLocale(LOCALE_UNSUPPORT));
    }
@Test
public void test26() {
    Set<Locale> set = LocaleUtils.availableLocaleSet();
    
    // changing the locale to one that is not available
    assertFalse(LocaleUtils.isAvailableLocale(new Locale("ru")));
    
    // changing the locale to one that is available
    assertTrue(LocaleUtils.isAvailableLocale(new Locale("fr")));
    
}
    @Test
    public void test27() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en", "en_GB"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it", "rm"});
        assertLanguageByCountry("US", new String[]{"en", "es", "es_MX", "es_US"});
        assertLanguageByCountry("FR", new String[]{"fr", "ca", "co", "fr_FR"});
    }
    @Test
    public void test28() {
        // Original test cases
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        
        // Additional test cases
        assertCountriesByLanguage("en", new String[]{"US", "GB", "AU", "CA"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CA", "BE", "CH", "LU"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO", "PE"});
        assertCountriesByLanguage("nl", new String[]{"NL", "BE"});
    }
}
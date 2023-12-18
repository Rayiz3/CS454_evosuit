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
        assertValidToLocale("fz__P", "fz", "", "P");
        assertValidToLocale("fz__POSIX", "fz", "", "POSIX");
    }
    @Test
    public void test1() {
        assertValidToLocale("us_EN_A", "us", "EN");
        assertValidToLocale("us_ZH_B", "us", "ZH");
    }
    @Test
    public void test2() {
        assertValidToLocale("_GB_X", "", "GB", "X");
        assertValidToLocale("_GB_Y", "", "GB", "Y");
    }
    @Test
    public void test3() {
        assertValidToLocale("us_EN_C", "us", "EN");
        assertValidToLocale("us_EN_D", "us", "EN");
    }
    @Test
    public void test4() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
    }
    @Test
    public void test5() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_GB, null, new Locale[]{LOCALE_EN_GB});
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
    public void test6() {
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, 
                new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN_GB, 
                new Locale[]{LOCALE_EN, LOCALE_EN_GB});
        
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
    public void test7() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_ZH, null, new Locale[]{LOCALE_ZH});
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
        assertLocaleLookupList(LOCALE_FR, LOCALE_FR, 
                new Locale[]{LOCALE_FR});
        assertLocaleLookupList(LOCALE_ZH, LOCALE_ZH, 
                new Locale[]{LOCALE_ZH});
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_ZH,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_ZH});
        
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
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_ZH,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_ZH});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
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
        
        Set<Locale> jdkLocaleSet = new HashSet<Locale>();
        jdkLocaleSet.add(LOCALE_EN);
        jdkLocaleSet.add(LOCALE_FR);
        jdkLocaleSet.add(LOCALE_DE);
        jdkLocaleSet.add(LOCALE_ZH);
        jdkLocaleSet.add(LOCALE_QQ);
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test10() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        Set<Locale> jdkLocaleSet = new HashSet<Locale>();
        jdkLocaleSet.add(LOCALE_EN);
        jdkLocaleSet.add(LOCALE_FR);
        jdkLocaleSet.add(LOCALE_DE);
        jdkLocaleSet.add(LOCALE_ZH);
        jdkLocaleSet.add(LOCALE_QQ);
        jdkLocaleSet.add(LOCALE_FR_CA);
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test11() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertSame(list, list2);
        assertUnmodifiableCollection(list);

        List<Locale> jdkLocaleList = new ArrayList<Locale>();
        jdkLocaleList.add(LOCALE_EN);
        jdkLocaleList.add(LOCALE_FR);
        jdkLocaleList.add(LOCALE_DE);
        jdkLocaleList.add(LOCALE_ZH);
        jdkLocaleList.add(LOCALE_QQ);
        jdkLocaleList.add(LOCALE_FR_CA);
        assertEquals(jdkLocaleList, list);
    }
    @Test
    public void test12() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertSame(list, list2);
        assertUnmodifiableCollection(list);

        List<Locale> jdkLocaleList = new ArrayList<Locale>();
        jdkLocaleList.add(LOCALE_EN);
        jdkLocaleList.add(LOCALE_FR);
        jdkLocaleList.add(LOCALE_DE);
        jdkLocaleList.add(LOCALE_ZH);
        jdkLocaleList.add(LOCALE_QQ);
        assertEquals(jdkLocaleList, list);
    }
    @Test
    public void test13() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ, LOCALE_FR, LOCALE_EN, LOCALE_DE, LOCALE_ZH});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN, LOCALE_FR, LOCALE_DE, LOCALE_ZH});
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR, LOCALE_EN, LOCALE_DE, LOCALE_ZH});
        assertLocaleLookupList(LOCALE_DE, null, new Locale[]{LOCALE_DE, LOCALE_EN, LOCALE_FR, LOCALE_ZH});
        assertLocaleLookupList(LOCALE_ZH, null, new Locale[]{LOCALE_ZH, LOCALE_EN, LOCALE_FR, LOCALE_DE});
    }
    @Test
    public void test14() {
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR,
                LOCALE_DE,
                LOCALE_ZH
            });
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_FR,
                LOCALE_DE,
                LOCALE_ZH,
                LOCALE_QQ
            });

    }
    @Test
    public void test15() {
        Set<Locale> set = new HashSet<>();
        set.add(LOCALE_EN);
        set.add(LOCALE_FR);
        set.add(LOCALE_DE);
        set.add(LOCALE_ZH);
        set.add(LOCALE_QQ);
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_DE), LocaleUtils.isAvailableLocale(LOCALE_DE));
        assertEquals(set.contains(LOCALE_ZH), LocaleUtils.isAvailableLocale(LOCALE_ZH));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(false, LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
    }
    @Test
    public void test16() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertNotSame(set, set2);  // Change: assertNotSame instead of assertSame
        
        // Rest of the code remains the same
        
    }
    @Test
    public void test17() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(Locale.US));  // Change: LOCALE_EN_US to Locale.US
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Additional test cases to cover more possible locales
        assertEquals(set.contains(Locale.CHINA), LocaleUtils.isAvailableLocale(Locale.CHINA));
        assertEquals(set.contains(Locale.JAPAN), LocaleUtils.isAvailableLocale(Locale.JAPAN));
        assertEquals(set.contains(Locale.KOREA), LocaleUtils.isAvailableLocale(Locale.KOREA));
        assertEquals(set.contains(new Locale("pt", "BR")), LocaleUtils.isAvailableLocale(new Locale("pt", "BR")));
        assertEquals(set.contains(new Locale("ru", "RU")), LocaleUtils.isAvailableLocale(new Locale("ru", "RU")));
    }
    @Test
    public void test18() {
        // Test with a valid available locale
        assertTrue(LocaleUtils.isAvailableLocale(Locale.ENGLISH));
        
        // Test with a valid unavailable locale
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("zz")));
        
        // Test with a null locale
        assertFalse(LocaleUtils.isAvailableLocale(null));
    }
    @Test
    public void test19() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("", new String[0]);
        assertLanguageByCountry("US", new String[] {"en"});
        assertLanguageByCountry("RU", new String[] {"ru"});
        assertLanguageByCountry("CHINA", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test20() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CD", "BE", "CG", "CM", "CI", "DZ", "GA", "GN", "HT", "MG", "ML", "MC", "MQ", "MU", "NC", "NE", "RE", "SN", "TG"});

        // Regression test case 1
        assertCountriesByLanguage("pt", new String[]{"PT", "MZ", "BR", "AO", "GW", "CV"});

        // Regression test case 2
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO", "PE", "VE", "CL", "EC", "CU", "GT", "CR", "DO", "HT", "PY", "BO", "SV", "HN", "PA", "NI", "PR", "UY", "GQ"});

        // Regression test case 3
        assertCountriesByLanguage("nl", new String[]{"NL", "SR", "AW", "AN"});
    }
}
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
        assertValidToLocale("US"); // change lowercase to uppercase
        assertValidToLocale("it123"); // change 2-letter language code to 3-letter language code
        assertValidToLocale("z"); // change 1-letter language code to 2-letter language code
    }
    @Test
    public void test1() {
        assertValidToLocale("_GBAP", "", "GB", "POSIX"); // remove underscore after the country
        assertValidToLocale("_gB", "", "GB", ""); // both parts lowercase
        assertValidToLocale("_1B", "", "1B", ""); // change first part to a non-letter character
    }
    @Test
    public void test2() {
        assertValidToLocale("fr_FR", "fr", "FR"); // change double underscore to single underscore
    }
    @Test
    public void test3() {
        assertValidToLocale("us_en", "us", "en"); // both parts lowercase
        assertValidToLocale("us_EN_A", "us", "EN", "a"); // change third part to lowercase
        assertValidToLocale("us_DE", "us", "EN"); // change 2-letter language code in second part to another 2-letter language code
    }
    @Test
    public void test4() {
        assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "sfsafdfdsdff"); // change third part to lowercase
        assertValidToLocale("us_EN_xyz", "us", "EN", "ABC"); // change third part to a non-alphabetic character
        assertValidToLocale("us_EN_AAAAAAA", "us", "EN", "AAAAAAA"); // change third part to longer than 8 characters
    }
    @Test
    public void test5() {
        assertLocaleLookupList(null, null, new Locale[0]);
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
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN_US, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US_ZZZZ, new Locale[]{LOCALE_EN_US});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN, new Locale[]{LOCALE_FR_CA});
        assertLocaleLookupList(LOCALE_EN, LOCALE_QQ, new Locale[]{LOCALE_EN});
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
        assertLocaleLookupList(null, LOCALE_EN, new Locale[0]);
        assertLocaleLookupList(LOCALE_QQ, LOCALE_EN, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN_US, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US_ZZZZ, new Locale[]{LOCALE_EN_US});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_QQ, new Locale[]{LOCALE_FR_CA});
        assertLocaleLookupList(LOCALE_EN, LOCALE_FR, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_QQ_ZZ, new Locale[]{LOCALE_FR_CA});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR_CA, new Locale[]{LOCALE_EN_US});
    }
    @Test
    public void test7() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
    }        
    @Test
    public void test8() {
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
    }
    @Test
    public void test9() {
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US_ZZZZ, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN,
                LOCALE_QQ_ZZ});
    }
    @Test
    public void test10() {
        // Change the input value of the method to null
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
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Regression test: Changing input value to a locale not in the available locale set
        assertEquals(false, LocaleUtils.isAvailableLocale(Locale.JAPAN));
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
        
        // Regression test: Changing input value to an empty set
        Set<Locale> emptySet = new HashSet<>();
        assertEquals(emptySet, LocaleUtils.availableLocaleSet());
    }
    @Test
    public void test13() {
        // Additional test case with a locale that is not in the availableLocaleList
        assertFalse(LocaleUtils.isAvailableLocale(Locale.JAPAN));
        
        // Additional test case with a locale that is in the availableLocaleList
        assertTrue(LocaleUtils.isAvailableLocale(Locale.US));
    }
    @Test
    public void test14() {
        assertLanguageByCountry("", new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        assertLanguageByCountry("ES", new String[]{"es"});
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("FR", new String[]{"fr"});
    }
    @Test
    public void test15() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        // Additional regression test cases
        assertCountriesByLanguage("en", new String[]{"US", "GB", "AU", "CA"});
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "CH", "CA"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO"});
        assertCountriesByLanguage("pt", new String[]{"PT", "BR", "AO", "MZ"});
    }
}
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
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        assertValidToLocale("us_tr", "us", "TR");
        assertValidToLocale("us_ES", "us", "ES");
    }
    @Test
    public void test1() {
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        assertValidToLocale("fr_GG", "fr", "GG");
        assertValidToLocale("fr_IT_C", "fr", "IT", "C");
    }
    @Test
    public void test2() {
        assertValidToLocale("_IT_C", "", "IT", "C");
        assertValidToLocale("_ZA", "", "ZA");
        assertValidToLocale("_us", "", "US");
        assertValidToLocale("_fr", "", "FR");
    }
    @Test
    public void test3() {
        // Test case 1: Passing a null value as the locale
        assertLocaleLookupList(null, null, new Locale[0]);
        // Test case 2: Passing a different locale
        assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
        // Test case 3: Passing an empty string as the locale
        assertLocaleLookupList(new Locale(""), null, new Locale[]{});
    }        
    @Test
    public void test4() {
        // Test case 1: Passing a null value as both locales
        assertLocaleLookupList(null, null, new Locale[0]);
        // Test case 2: Passing a different locale as the second locale
        assertLocaleLookupList(LOCALE_EN, LOCALE_FR, new Locale[]{LOCALE_EN});
        // Test case 3: Passing an empty string as the second locale
        assertLocaleLookupList(LOCALE_EN, new Locale(""), new Locale[]{LOCALE_EN});
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
        
        // Regression test cases
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {LOCALE_EN_US_ZZZZ});
        assertLocaleLookupList(LOCALE_FR, null,
            new Locale[] {LOCALE_FR});
        assertLocaleLookupList(LOCALE_FR_FR, null,
            new Locale[] {LOCALE_FR_FR});
        assertLocaleLookupList(LOCALE_EN_GB, null,
            new Locale[] {LOCALE_EN_GB});
        assertLocaleLookupList(LOCALE_EN_GB_ZZZZ, null,
            new Locale[] {LOCALE_EN_GB_ZZZZ});
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
        
        // Regression test cases
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR_CA,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_GB,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_EN_GB});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN_GB,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN_GB});
    }
@Test
public void test7() {
    assertValidToLocale("_GB_", "", "GB", "");
    assertValidToLocale("_GB__P", "", "GB", "P");
    assertValidToLocale("_GB__POSIX", "", "GB", "POSIX");

    ...

    try {
        LocaleUtils.toLocale("_GB_A");
        fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
    } catch (final IllegalArgumentException iae) {
    }
}
@Test
public void test8() {
    assertValidToLocale("us_");
    assertValidToLocale("fr_");
    assertValidToLocale("de_");
    assertValidToLocale("zh_");

    ...

    try {
        LocaleUtils.toLocale("u_");
        fail("Must be 2 chars if less than 5");
    } catch (IllegalArgumentException iae) {}
   
    try {
        LocaleUtils.toLocale("uuu_");
        fail("Must be 2 chars if less than 5");
    } catch (IllegalArgumentException iae) {}

    try {
        LocaleUtils.toLocale("uu_U_");
        fail("Must be 2 chars if less than 5");
    } catch (IllegalArgumentException iae) {}
}
@Test
public void test9() {
    assertValidToLocale("us_EN_", "us", "EN");
    assertValidToLocale("us_ZH_", "us", "ZH");

    ...

    try {
        LocaleUtils.toLocale("us-EN_");
        fail("Should fail as not underscore");
    } catch (IllegalArgumentException iae) {}

    try {
        LocaleUtils.toLocale("us_En_");
        fail("Should fail second part not uppercase");
    } catch (IllegalArgumentException iae) {}

    try {
        LocaleUtils.toLocale("us_en_");
        fail("Should fail second part not uppercase");
    } catch (IllegalArgumentException iae) {}

    try {
        LocaleUtils.toLocale("us_eN_");
        fail("Should fail second part not uppercase");
    } catch (IllegalArgumentException iae) {}

    try {
        LocaleUtils.toLocale("uS_EN_");
        fail("Should fail first part not lowercase");
    } catch (IllegalArgumentException iae) {}

    try {
        LocaleUtils.toLocale("us_E3_");
        fail("Should fail second part not uppercase");
    } catch (IllegalArgumentException iae) {}
}
@Test
public void test10() {
    assertValidToLocale("us_EN_A_", "us", "EN", "A");

    ...

    try {
        LocaleUtils.toLocale("us_EN-a_");
        fail("Should fail as not underscore");
    } catch (IllegalArgumentException iae) {}

    try {
        LocaleUtils.toLocale("uu_UU__");
        fail("Must be 3, 5 or 7+ in length");
    } catch (IllegalArgumentException iae) {}
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
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Regression test cases
        assertFalse(set.contains(LOCALE_DE)); // LOCALE_DE is not available in the set
        assertFalse(set.contains(LOCALE_EN_GB)); // LOCALE_EN_GB is not available in the set
    }
@Test
public void test13() {
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
    try{
        LocaleUtils.toLocale("_GB_"); // Regression test #1
        fail("Must have underscore after country if starts with underscore and is at least 5 chars");
    } catch (final IllegalArgumentException iae) {
    }
    try{
        LocaleUtils.toLocale("_GB_N"); // Regression test #2
        fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
    } catch (final IllegalArgumentException iae) {
    }
}
    @Test
    public void test14() {
        // Original test case
        assertCountriesByLanguage(null, new String[0]);
        
        // New test cases
        assertCountriesByLanguage("", new String[0]);
        assertCountriesByLanguage("fr", new String[0]);
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CL"});
    }
}
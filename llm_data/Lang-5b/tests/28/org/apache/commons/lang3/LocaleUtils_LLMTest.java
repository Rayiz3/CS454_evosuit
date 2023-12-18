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
    assertEquals(null, LocaleUtils.toLocale((String) null));

    // Original test cases
    assertValidToLocale("us");
    assertValidToLocale("fr");
    assertValidToLocale("de");
    assertValidToLocale("zh");
    assertValidToLocale("qq");

    // New test cases
    assertValidToLocale("uk");
    assertValidToLocale("es");

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
    // Original test cases
    assertValidToLocale("_GB", "", "GB", "");
    assertValidToLocale("_GB_P", "", "GB", "P");
    assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");

    // New test cases
    assertValidToLocale("_GB_E", "", "GB", "E");
    assertValidToLocale("_GB_F", "", "GB", "F");

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
    // Original test cases
    assertValidToLocale("fr__P", "fr", "", "P");
    assertValidToLocale("fr__POSIX", "fr", "", "POSIX");

    // New test cases
    assertValidToLocale("fr__Q", "fr", "", "Q");
    assertValidToLocale("fr__R", "fr", "", "R");
}
@Test
public void test3() {
    // Original test cases
    assertValidToLocale("us_EN", "us", "EN");
    assertValidToLocale("us_ZH", "us", "ZH");

    // New test cases
    assertValidToLocale("us_SP", "us", "SP");
    assertValidToLocale("us_TR", "us", "TR");

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
public void test4() {
    // Original test cases
    assertValidToLocale("us_EN_A", "us", "EN", "A");
    assertValidToLocale("us_EN_a", "us", "EN", "a");
    assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFsafdFDsdfF");
    assertValidToLocale("us_EN_a", "us", "EN", "a");
    assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFSAFDFDSDFF");

    // New test cases
    assertValidToLocale("us_EN_B", "us", "EN", "B");
    assertValidToLocale("us_EN_c", "us", "EN", "c");
    assertValidToLocale("us_EN_DDFDdD", "us", "EN", "DDFDdD");

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
    public void test5() {
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
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
        assertLocaleLookupList(NEW_LOCALE, null, new Locale[]{NEW_LOCALE}); // Regression test case with a new locale
    }        
    @Test
    public void test6() {
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
        assertLocaleLookupList(NEW_LOCALE, LOCALE_EN,
            new Locale[]{NEW_LOCALE, LOCALE_EN}); // Regression test case with a new locale
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
    
    // Regression tests
    assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_FR});
    assertLocaleLookupList(LOCALE_EN_US, null,
        new Locale[] {
            LOCALE_EN_US,
            LOCALE_EN,
            LOCALE_FR});
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
    
    // Regression tests
    assertLocaleLookupList(LOCALE_EN_US, null,
        new Locale[] {
            LOCALE_EN_US,
            LOCALE_EN,
            LOCALE_FR});
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US_ZZZZ,
        new Locale[] {
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN_US,
            LOCALE_EN});
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
public void test9() {
    List<Locale> list = LocaleUtils.availableLocaleList();
    List<Locale> list2 = LocaleUtils.availableLocaleList();
    assertNotNull(list);
    assertNotSame(list, list2);
    assertUnmodifiableCollection(list);
    
    Locale[] jdkLocaleArray = Locale.getAvailableLocales();
    List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
    assertEquals(jdkLocaleList, list);
}
@Test
public void test10() {
    List<Locale> list = LocaleUtils.availableLocaleList();
    assertNotNull(list);
    assertEquals(1, list.size());
    assertSame(LocaleUtils.availableLocaleList(), list);
}
@Test
public void test11() {
    List<Locale> list = LocaleUtils.availableLocaleList();
    assertNotNull(list);
    assertTrue(list.isEmpty());
}
    @Test
    public void test12() {
        assertNotNull(new LocaleUtils());
        Constructor<?>[] cons = LocaleUtils.class.getDeclaredConstructors();
        assertEquals(0, cons.length);
        assertTrue(Modifier.isPublic(LocaleUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(LocaleUtils.class.getModifiers()));
    }
    @Test
    public void test13() {
        assertNotNull(new LocaleUtils());
        Constructor<?>[] cons = LocaleUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPrivate(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(LocaleUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(LocaleUtils.class.getModifiers()));
    }
    @Test
    public void test14() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Regression tests
        assertEquals(set.contains(new Locale("en", "US", "ZZZZ")), LocaleUtils.isAvailableLocale(new Locale("en", "US", "ZZZZ")));
        assertEquals(set.contains(new Locale("en")), LocaleUtils.isAvailableLocale(new Locale("en")));
        assertEquals(set.contains(new Locale("fr", "CA", "AAAA")), LocaleUtils.isAvailableLocale(new Locale("fr", "CA", "AAAA")));
        assertEquals(set.contains(new Locale("fr", "CA", "ZZZZ")), LocaleUtils.isAvailableLocale(new Locale("fr", "CA", "ZZZZ")));
        assertEquals(set.contains(new Locale("qq", "ZZ")), LocaleUtils.isAvailableLocale(new Locale("qq", "ZZ")));
        assertEquals(set.contains(new Locale("qq")), LocaleUtils.isAvailableLocale(new Locale("qq")));
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
        
        // Regression tests
        assertEquals(set, LocaleUtils.availableLocaleSet()); // Test same set returned for subsequent invocations
        assertUnmodifiableCollection(set); // Test set is unmodifiable
        assertFalse(set.isEmpty()); // Test set is not empty
        for (Locale locale : set) {
            assertTrue(set.contains(locale)); // Test set contains each locale
        }
    }
    @Test
    public void test16() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("en", "CA")));
    }
    @Test
    public void test17() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("es", "MX")));
    }
    @Test
    public void test18() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("fr", "BE")));
    }
    @Test
    public void test19() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("zh", "TW")));
    }
    @Test
    public void test20() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("", new String[]{"en"});
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("AU", new String[]{"en"});
        assertLanguageByCountry("ES", new String[]{"es"});
        assertLanguageByCountry("JP", new String[]{"ja"});
        assertLanguageByCountry("KR", new String[]{"ko"});
        assertLanguageByCountry("CN", new String[]{"zh"});
    }
    @Test
    public void test21() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{});
        assertCountriesByLanguage("zzz", new String[]{});
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "CA", "CH", "LU"});
    }
}
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
        assertInvalidToLocale("___"); // Should throw IllegalArgumentException
        assertInvalidToLocale("aA_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("a1_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("A_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("b_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("aA"); // Should throw IllegalArgumentException
        assertInvalidToLocale("a1"); // Should throw IllegalArgumentException
        assertInvalidToLocale("A"); // Should throw IllegalArgumentException
        assertInvalidToLocale("b"); // Should throw IllegalArgumentException
        assertInvalidToLocale("A2_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("A2"); // Should throw IllegalArgumentException
        assertInvalidToLocale("$_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("$_2"); // Should throw IllegalArgumentException
        assertInvalidToLocale("a_A"); // Should throw IllegalArgumentException
        assertInvalidToLocale("a_1"); // Should throw IllegalArgumentException
        assertInvalidToLocale("a#_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("a2_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("A2_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("$_3"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_3"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_2"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_1"); // Should throw IllegalArgumentException
        assertInvalidToLocale("__"); // Should throw IllegalArgumentException        
    }
    @Test
    public void test1() {
        assertInvalidToLocale("_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("a"); // Should throw IllegalArgumentException
        assertInvalidToLocale("A"); // Should throw IllegalArgumentException
        assertInvalidToLocale("1_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_2"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_2_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_2A"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_A"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_1"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_2_"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_1A"); // Should throw IllegalArgumentException
        assertInvalidToLocale("_1A_"); // Should throw IllegalArgumentException
    }
    @Test
    public void test2() {
        assertInvalidToLocale("usEN_A"); // Should throw IllegalArgumentException
        assertInvalidToLocale("US_EN"); // Should throw IllegalArgumentException
        assertInvalidToLocale("us_EN-A"); // Should throw IllegalArgumentException
        assertInvalidToLocale("uu_UU_"); // Should throw IllegalArgumentException
    }
    @Test
    public void test3() {
        assertInvalidToLocale("Us"); // Should throw IllegalArgumentException
        assertInvalidToLocale("US"); // Should throw IllegalArgumentException
        assertInvalidToLocale("uS"); // Should throw IllegalArgumentException
        assertInvalidToLocale("u#"); // Should throw IllegalArgumentException
        assertInvalidToLocale("u"); // Should throw IllegalArgumentException
        assertInvalidToLocale("uuu"); // Should throw IllegalArgumentException
        assertInvalidToLocale("uu_U"); // Should throw IllegalArgumentException
    }
    private void assertInvalidToLocale(String input) {
        try {
            LocaleUtils.toLocale(input);
            fail("Should throw IllegalArgumentException for input: " + input);
        } catch (IllegalArgumentException e) {
            // Exception thrown as expected
        }
    }
@Test
    public void test4() {
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ});
    }        
    @Test
    public void test5() {
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ,
                LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ,
                LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN,
                LOCALE_QQ,
                LOCALE_QQ_ZZ});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_QQ,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN,
                LOCALE_QQ,
                LOCALE_QQ_ZZ});
    }
    @Test
    public void test6() {
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
    }
    @Test
    public void test7() {
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, 
                new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN});
    }        
    @Test
    public void test8() {
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
        
        // Regression Test Cases
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_FR_CA, null,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR});
        assertLocaleLookupList(LOCALE_ZH_CN, null, 
            new Locale[] {
                LOCALE_ZH_CN,
                LOCALE_ZH});
    }        
    @Test
    public void test9() {
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
        
        // Regression Test Case
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN});
    }
    @Test
    public void test10() {
        // Change the input value to an empty list
        SyncAvoid.AVAILABLE_LOCALE_LIST = Collections.emptyList();
        
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        // The assertion will fail because the set will be empty
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test11() {
        // Change the input value to an empty list
        SyncAvoid.AVAILABLE_LOCALE_LIST = Collections.emptyList();
        
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertSame(list, list2);
        assertUnmodifiableCollection(list);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        // The assertion will fail because the list will be empty
        assertEquals(jdkLocaleList, list);
    }
    @Test
    public void test12() {
        // Change the input value to a locale that is not available
        Locale locale = new Locale("es", "ES");
        assertTrue(LocaleUtils.isAvailableLocale(locale));
        
        // Change the input value to a locale that is available
        Locale locale2 = new Locale("en", "US");
        assertFalse(LocaleUtils.isAvailableLocale(locale2));
    }
    @Test
    public void test13() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        // Regression test 1
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = new ArrayList<>(Arrays.asList(jdkLocaleArray));
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        jdkLocaleSet.remove(Locale.US);
        assertFalse(jdkLocaleSet.equals(set));
        
        // Regression test 2
        set.remove(Locale.ITALY);
        assertFalse(set.equals(set2));
    }
    @Test
    public void test14() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        
        // Regression test 1
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("en", "ZZZZ")));
        
        // Regression test 2
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("fr", "ZZZZ")));
    }
    @Test
    public void test15() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertNotNull(set2);
        assertNotSame(set, set2);
        assertUnmodifiableCollection(set);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test16() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertNotNull(list2);
        assertNotSame(list, list2);
        assertUnmodifiableCollection(list);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        assertEquals(jdkLocaleList, list);
    }
    @Test
    public void test17() {
        assertLocaleLookupList(null, null, new Locale[1]);
        assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ, LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN, LOCALE_FR});
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN, LOCALE_EN_US});
        assertLocaleLookupList(LOCALE_EN_US, null, new Locale[]{LOCALE_EN_US, LOCALE_EN_FR_US});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null, new Locale[]{LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_FR_US});
    }        
    @Test
    public void test18() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
    }
    @Test
    public void test19() {
        assertNotNull(new LocaleUtils());
        Constructor<?>[] cons = LocaleUtils.class.getDeclaredConstructors();
        assertNotEquals(2, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(LocaleUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(LocaleUtils.class.getModifiers()));
    }
    @Test
    public void test20() {
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
    public void test21() {
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
    public void test22() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
    }
    @Test
    public void test23() {
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
    public void test24() {
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
    public void test25() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test26() {
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
    public void test27() {
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("CA", new String[]{"en", "fr"});
        assertLanguageByCountry("MX", new String[]{"es"});
        assertLanguageByCountry("BR", new String[]{"pt"});
        assertLanguageByCountry("ES", new String[]{"es"});
        assertLanguageByCountry("FR", new String[]{"fr"});
    }
    @Test
    public void test28() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        // Additional test cases
        assertCountriesByLanguage("en", new String[]{"US", "GB", "CA", "AU", "IN", "IE", "ZA"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CA", "BE", "LU", "CH"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO", "PE", "VE", "CL"});
    }
}
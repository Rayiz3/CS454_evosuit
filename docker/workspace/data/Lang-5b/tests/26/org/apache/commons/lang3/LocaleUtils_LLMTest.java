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

        // Regression tests for first if statement
        try {
            LocaleUtils.toLocale(null);
            fail("Should throw exception when str is null");
        } catch (IllegalArgumentException e) {}

        // Regression tests for second if statement
        try {
            LocaleUtils.toLocale("a");
            fail("Should throw exception when len < 2");
        } catch (IllegalArgumentException e) {}

        // Regression tests for third if statement
        try {
            LocaleUtils.toLocale("Fr");
            fail("Should throw exception when ch0 is not lowercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for fifth if statement
        try {
            LocaleUtils.toLocale("fr_E");
            fail("Should throw exception when ch1 is not lowercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for sixth if statement
        try {
            LocaleUtils.toLocale("uu");
            fail("Should throw exception when len < 5");
        } catch (IllegalArgumentException e) {}

        // Regression tests for seventh if statement
        try {
            LocaleUtils.toLocale("u_FF");
            fail("Should throw exception when ch2 is not underscore");
        } catch (IllegalArgumentException e) {}

        // Regression tests for eighth if statement
        try {
            LocaleUtils.toLocale("fr_0S");
            fail("Should throw exception when ch3 is not uppercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for ninth if statement
        try {
            LocaleUtils.toLocale("fr_A0");
            fail("Should throw exception when ch4 is not uppercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for tenth if statement
        try {
            LocaleUtils.toLocale("fr_EN_A");
            fail("Should throw exception when len < 7");
        } catch (IllegalArgumentException e) {}

        // Regression tests for eleventh if statement
        try {
            LocaleUtils.toLocale("fr_EN-");
            fail("Should throw exception when ch5 is not underscore");
        } catch (IllegalArgumentException e) {}
    }
    @Test
    public void test1() {
        assertValidToLocale("us_EN", "us", "EN");
        //valid though doesnt exist
        assertValidToLocale("us_ZH", "us", "ZH");

        // Regression tests for first if statement
        try {
            LocaleUtils.toLocale("us-EN");
            fail("Should throw exception when not underscore");
        } catch (IllegalArgumentException e) {}

        // Regression tests for second if statement
        try {
            LocaleUtils.toLocale("us_En");
            fail("Should throw exception when second part not uppercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for third if statement
        try {
            LocaleUtils.toLocale("us_en");
            fail("Should throw exception when second part not uppercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for fourth if statement
        try {
            LocaleUtils.toLocale("us_eN");
            fail("Should throw exception when second part not uppercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for fifth if statement
        try {
            LocaleUtils.toLocale("uS_EN");
            fail("Should throw exception when first part not lowercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for sixth if statement
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should throw exception when second part not uppercase");
        } catch (IllegalArgumentException e) {}
    }        
    @Test
    public void test2() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
        
        // Regression tests for first if statement
        try {
            LocaleUtils.toLocale("_G");
            fail("Should throw exception when at least 3 chars and starts with underscore");
        } catch (IllegalArgumentException iae) {}

        // Regression tests for second if statement
        try {
            LocaleUtils.toLocale("_Gb");
            fail("Should throw exception when uppercase if starts with underscore");
        } catch (IllegalArgumentException iae) {}

        // Regression tests for third if statement
        try {
            LocaleUtils.toLocale("_gB");
            fail("Should throw exception when uppercase if starts with underscore");
        } catch (IllegalArgumentException iae) {}

        // Regression tests for fourth if statement
        try {
            LocaleUtils.toLocale("_1B");
            fail("Should throw exception when letter if starts with underscore");
        } catch (IllegalArgumentException iae) {}

        // Regression tests for fifth if statement
        try {
            LocaleUtils.toLocale("_G1");
            fail("Should throw exception when letter if starts with underscore");
        } catch (IllegalArgumentException iae) {}

        // Regression tests for sixth if statement
        try {
            LocaleUtils.toLocale("_GB_");
            fail("Should throw exception when at least 5 chars and starts with underscore");
        } catch (IllegalArgumentException iae) {}

        // Regression tests for seventh if statement
        try {
            LocaleUtils.toLocale("_GBAP");
            fail("Should throw exception when underscore after the country if starts with underscore and is at least 5 chars");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test3() {
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        
        if (SystemUtils.isJavaVersionAtLeast(JAVA_1_4)) {
            assertValidToLocale("us_EN_a", "us", "EN", "a");
            assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFsafdFDsdfF");
        } else {
            assertValidToLocale("us_EN_a", "us", "EN", "A");
            assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFSAFDFDSDFF");
        }
        
        // Regression tests for first if statement
        try {
            LocaleUtils.toLocale("us_EN-a");
            fail("Should throw exception when not underscore");
        } catch (IllegalArgumentException e) {}

        // Regression tests for second if statement
        try {
            LocaleUtils.toLocale("uu_UU_");
            fail("Should throw exception when not 3, 5, or 7+ chars in length");
        } catch (IllegalArgumentException e) {}
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

        // Regression tests for first if statement
        try {
            LocaleUtils.toLocale("Us");
            fail("Should throw exception when not lowercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for second if statement
        try {
            LocaleUtils.toLocale("US");
            fail("Should throw exception when not lowercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for third if statement
        try {
            LocaleUtils.toLocale("uS");
            fail("Should throw exception when not lowercase");
        } catch (IllegalArgumentException e) {}

        // Regression tests for fourth if statement
        try {
            LocaleUtils.toLocale("u#");
            fail("Should throw exception when not lowercase");
        } catch (IllegalArgumentException e) {}
        
        // Regression tests for fifth if statement
        try {
            LocaleUtils.toLocale("u");
            fail("Should throw exception when less than 2 chars and less than 5 in length");
        } catch (IllegalArgumentException e) {}
       
        // Regression tests for sixth if statement
        try {
            LocaleUtils.toLocale("uuu");
            fail("Should throw exception when less than 2 chars and less than 5 in length");
        } catch (IllegalArgumentException e) {}

        // Regression tests for seventh if statement
        try {
            LocaleUtils.toLocale("uu_U");
            fail("Should throw exception when less than 2 chars and less than 5 in length");
        } catch (IllegalArgumentException e) {}
    }        
    @Test
    public void test5() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(null, null, new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(null, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(null, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(null, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(null, null,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
    }        
    @Test
    public void test6() {
        assertLocaleLookupList(LOCALE_EN, LOCALE_QQ, 
                new Locale[]{LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_QQ});
        
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_QQ});
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
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR});
    }
    @Test
    public void test7() {
        assertLocaleLookupList(null, null, new Locale[0]);
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
        // Regression test case
        assertLocaleLookupList(new Locale("tr"), null, 
            new Locale[]{new Locale("tr")});
    }        
    @Test
    public void test8() {
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
        assertLocaleLookupList(new Locale("fr", "CA"), LOCALE_EN,
            new Locale[] {
                new Locale("fr", "CA"),
                new Locale("fr"),
                LOCALE_EN});
        // Regression test case
        assertLocaleLookupList(new Locale("tr", "TR"), LOCALE_EN,
            new Locale[] {
                new Locale("tr", "TR"),
                new Locale("tr"),
                LOCALE_EN});
    }
    @Test
    public void test9() {
        // Change the input value to an empty set
        Set<Locale> set = new HashSet<>();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertEquals(set, set2);
    
        // Change the input value to a set with only one locale
        set.add(LOCALE_EN);
        set2 = LocaleUtils.availableLocaleSet();
        assertEquals(set, set2);
        
        // Change the input value to a set with multiple locales
        set.clear();
        set.add(LOCALE_EN);
        set.add(LOCALE_FR);
        set.add(LOCALE_DE);
        set2 = LocaleUtils.availableLocaleSet();
        assertEquals(set, set2);
    }
    @Test
    public void test10() {
        // Change the input value to an empty list
        List<Locale> list = new ArrayList<>();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertEquals(list, list2);
        
        // Change the input value to a list with only one locale
        list.add(LOCALE_EN);
        list2 = LocaleUtils.availableLocaleList();
        assertEquals(list, list2);
        
        // Change the input value to a list with multiple locales
        list.clear();
        list.add(LOCALE_EN);
        list.add(LOCALE_FR);
        list.add(LOCALE_DE);
        list2 = LocaleUtils.availableLocaleList();
        assertEquals(list, list2);
    }
    @Test
    public void test11() {
        // Change the input value to a null locale
        assertLocaleLookupList(null, null, new Locale[0]);
        
        // Change the input value to a locale with a language but no country
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, new Locale[]{LOCALE_EN});
        
        // Change the input value to a locale with a country but no language
        assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, new Locale[]{LOCALE_QQ});
        
        // Change the input value to a locale with both language and country
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, new Locale[] {
            LOCALE_EN_US,
            LOCALE_EN});
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
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Additional test cases
        
        // Test with a locale that is not in the available locales set
        Locale localeNotAvailable = new Locale("de_DE");
        assertFalse(set.contains(localeNotAvailable));
        assertEquals(set.contains(localeNotAvailable), LocaleUtils.isAvailableLocale(localeNotAvailable));
        
        // Test with a null locale
        Locale nullLocale = null;
        assertFalse(set.contains(nullLocale));
        assertNull(LocaleUtils.isAvailableLocale(nullLocale));
    }
    @Test
    public void test14() {
        Locale locale = new Locale("zz", "ZZ");
        assertFalse(LocaleUtils.isAvailableLocale(locale));
    }
    @Test
    public void test15() {
        assertFalse(LocaleUtils.isAvailableLocale(null));
    }
    @Test
    public void test16() {
        Locale locale = new Locale("xy", "XY");
        assertFalse(LocaleUtils.isAvailableLocale(locale));
    }
    @Test
    public void test17() {
        Set<Locale> set = new HashSet<>();
        assertUnmodifiableCollection(set);
    }
    @Test
    public void test18() {
        List<Locale> list = new ArrayList<>();
        assertUnmodifiableCollection(list);
    }
    @Test
    public void test19() {
        assertLocaleLookupList(null, null, new Locale[0]);
    }
    @Test
    public void test20() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
        
        // Regression test to kill mutants of the unmodifiableCollection() call in testAvailableLocaleSet()
        // Changing the input to an empty set
        Set<Locale> emptySet = new HashSet<>();
        assertUnmodifiableCollection(emptySet);
    }
    @Test
    public void test21() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertSame(list, list2);
        assertUnmodifiableCollection(list);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        assertEquals(jdkLocaleList, list);
        
        // Regression test to kill mutants of the unmodifiableCollection() call in testAvailableLocaleList()
        // Changing the input to an empty list
        List<Locale> emptyList = new ArrayList<>();
        assertUnmodifiableCollection(emptyList);
    }
    @Test
    public void test22() {
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
        
        // Regression test to kill mutants of the localeLookupList() method
        // Changing the input locales to be null in the localeLookupList() call
        assertLocaleLookupList(null, null, null);
    }        
    @Test
    public void test23() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        
        // Regression test to kill mutants of the assertValidToLocale() method in testLang328()
        // Changing the input to a valid format but invalid language
        assertValidToLocale("xy__P", "xy", "", "P");
    }
    @Test
    public void test24() {
        assertNotNull(new LocaleUtils());
        Constructor<?>[] cons = LocaleUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(LocaleUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(LocaleUtils.class.getModifiers()));
    }
    @Test
    public void test25() {
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
        
        // Regression test to kill mutants of the second part validation in testToLocale_2Part()
        // Changing the input second part to lowercase
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the second part validation in testToLocale_2Part()
        // Changing the input second part to lowercase
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the second part validation in testToLocale_2Part()
        // Changing the input second part to lowercase
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the second part validation in testToLocale_2Part()
        // Changing the input second part to lowercase
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the first part validation in testToLocale_2Part()
        // Changing the input first part to uppercase
        try {
            LocaleUtils.toLocale("uS_EN");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the second part validation in testToLocale_2Part()
        // Changing the input second part to contain non-letters
        try {
            LocaleUtils.toLocale("us_E3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test26() {
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
    public void test27() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
    }
    @Test
    public void test28() {
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
        
        
        // Regression test to kill mutants of the second if condition in testToLocale_3Part()
        // Changing the input locale to have less than 3 characters
        try {
            LocaleUtils.toLocale("u");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the second if condition in testToLocale_3Part()
        // Changing the input locale to have more than 3 characters
        try {
            LocaleUtils.toLocale("uuu");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}

        // Regression test to kill mutants of the second if condition in testToLocale_3Part()
        // Changing the input locale to have less than 5 characters
        try {
            LocaleUtils.toLocale("uu_U");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test29() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Regression test to kill mutants of the contains() method call in testIsAvailableLocale()
        // Changing the input to a locale that is not in the availableLocaleList()
        Locale locale = new Locale("xy", "XY");
        assertFalse(LocaleUtils.isAvailableLocale(locale));
    }
    @Test
    public void test30() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
    }
    @Test
    public void test31() {
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
        
        // Regression test to kill mutants of the first part validation in testToLocale_1Part()
        // Changing the input to uppercase
        try {
            LocaleUtils.toLocale("Us");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the first part validation in testToLocale_1Part()
        // Changing the input to uppercase
        try {
            LocaleUtils.toLocale("US");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the first part validation in testToLocale_1Part()
        // Changing the input to uppercase
        try {
            LocaleUtils.toLocale("uS");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        
        // Regression test to kill mutants of the first part validation in testToLocale_1Part()
        // Changing the input to contain non-letters
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
        
        // Regression test to kill mutants of the first part validation in testToLocale_1Part()
        // Changing the input to be more than 2 characters
        try {
            LocaleUtils.toLocale("uu");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
    }        
    @Test
    public void test32() {
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
        
        // Regression test to kill mutants of the third parameter validation in testLocaleLookupList_LocaleLocale()
        // Changing the input to null
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN, null);
    }
@Test
public void test33() {
    // Existing test cases
    assertLanguageByCountry(null, new String[0]);
    assertLanguageByCountry("GB", new String[]{"en"});
    assertLanguageByCountry("ZZ", new String[0]);
    assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});

    // Regression test cases
    assertLanguageByCountry("US", new String[0]);
    assertLanguageByCountry("JP", new String[0]);
    assertLanguageByCountry("CN", new String[0]);
}
    @Test
    public void test34() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});

        // Additional regression test cases
        assertCountriesByLanguage("", new String[0]);
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CA", "BE", "CH"});
        assertCountriesByLanguage("en", new String[]{"GB", "US", "AU", "CA"});

    }
}
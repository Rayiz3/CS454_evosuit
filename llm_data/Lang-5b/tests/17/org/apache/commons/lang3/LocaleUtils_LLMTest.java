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
        assertValidToLocale("_GB_", "", "GB", ""); // mutated line: len == 6
        assertValidToLocale("_GB_P_", "", "GB", "P"); // mutated line: len == 7
        assertValidToLocale("_GB__P", "", "GB", ""); // mutated line: len == 8
        assertValidToLocale("__GB_P", "", "GB", "P"); // mutated line: len == 7
        assertValidToLocale("__GB__", "", "GB", ""); // mutated line: len == 6
    }
    @Test
    public void test1() {
        assertValidToLocale("us_A"); // mutated line: len == 3
        assertValidToLocale("us_B"); // mutated line: len == 3
        assertValidToLocale("us_C"); // mutated line: len == 3
        assertValidToLocale("us_D"); // mutated line: len == 3
        assertValidToLocale("us_E"); // mutated line: len == 3
    }
    @Test
    public void test2() {
        assertValidToLocale("us_EN_A"); // mutated line: len == 8
        assertValidToLocale("us_EN_B"); // mutated line: len == 8
        assertValidToLocale("us_EN_C"); // mutated line: len == 8
        assertValidToLocale("us_EN_D"); // mutated line: len == 8
        assertValidToLocale("us_EN_E"); // mutated line: len == 8
    }
    @Test
    public void test3() {
        assertValidToLocale("us_EN_A_"); // mutated line: len == 9
        assertValidToLocale("us_EN_A__"); // mutated line: len == 10
        assertValidToLocale("us_EN_B_"); // mutated line: len == 9
        assertValidToLocale("us_EN_B__"); // mutated line: len == 10
        assertValidToLocale("us_EN_C_"); // mutated line: len == 9
    }
    @Test
    public void test4() {
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
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
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
    public void test5() {
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
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
                new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ, 
            new Locale[]{
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
    }
@Test
public void test6() {
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
}        
@Test
public void test7() {
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
public void test8() {
    assertLocaleLookupList(null, null, new Locale[0]);
    assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN_US, null,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_EN_US_ZZZZ});
}        
@Test
public void test9() {
    assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
            new Locale[]{LOCALE_EN});
    
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
        new Locale[]{
            LOCALE_EN,
            LOCALE_EN_US});
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_QQ_ZZ});
    
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_EN_US_ZZZZ});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_EN_US_ZZZZ});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_EN_US_ZZZZ,
            LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_EN_US_ZZZZ,
            LOCALE_QQ_ZZ});
    assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
        new Locale[] {
            LOCALE_FR,
            LOCALE_FR_CA,
            LOCALE_EN});
}
@Test
public void test10() {
    assertLocaleLookupList(null, null, new Locale[0]);
    assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN_US, null,
        new Locale[] {
            LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US});
}        
@Test
public void test11() {
    assertLocaleLookupList(LOCALE_EN, LOCALE_EN, 
            new Locale[]{LOCALE_EN});
    
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
        new Locale[]{
            LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_QQ_ZZ});
    
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
        new Locale[] {
            LOCALE_EN,
            LOCALE_EN_US,
            LOCALE_QQ_ZZ});
    assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
        new Locale[] {
            LOCALE_FR,
            LOCALE_FR_CA});
}
@Test
public void test12() {
    assertLocaleLookupList(null, null, new Locale[0]);
    assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN_US, null,
        new Locale[] {
            LOCALE_EN_US,
            LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
        new Locale[] {
            LOCALE_EN_US_ZZZZ});
}        
@Test
public void test13() {
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
            LOCALE_EN_US_ZZZZ});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
        new Locale[] {
            LOCALE_EN_US_ZZZZ});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ,
        new Locale[] {
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN,
            LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
        new Locale[] {
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN,
            LOCALE_QQ_ZZ});
    assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN,
        new Locale[] {
            LOCALE_FR_CA,
            LOCALE_EN});
}
    @Test
    public void test14() {
        // Invalid input values
        assertInvalidToLocale("_G");
        assertInvalidToLocale("_Gb");
        assertInvalidToLocale("_gB");
        assertInvalidToLocale("_1B");
        assertInvalidToLocale("_G1");
        assertInvalidToLocale("_GB_");
        assertInvalidToLocale("_GBAP");

        // Updated test cases with valid input values
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
    }
    @Test
    public void test15() {
        // Updated test cases with invalid input values
        assertInvalidToLocale("Us");
        assertInvalidToLocale("US");
        assertInvalidToLocale("uS");
        assertInvalidToLocale("u#");
        assertInvalidToLocale("u");
        assertInvalidToLocale("uuu");
        assertInvalidToLocale("uu_U");

        // Updated test cases with valid input values
        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        assertValidToLocale("qq");
    }
    @Test
    public void test16() {
        // Updated test cases with invalid input values
        assertInvalidToLocale("us-EN");
        assertInvalidToLocale("us_En");
        assertInvalidToLocale("us_en");
        assertInvalidToLocale("us_eN");
        assertInvalidToLocale("uS_EN");
        assertInvalidToLocale("us_E3");

        // Updated test cases with valid input values
        assertValidToLocale("us_EN", "us", "EN");
        assertValidToLocale("us_ZH", "us", "ZH");
    }
    @Test
    public void test17() {
        // Updated test cases with invalid input values
        assertInvalidToLocale("us_EN-a");
        assertInvalidToLocale("uu_UU_");

        // Updated test cases with valid input values
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        assertValidToLocale("us_EN_a", "us", "EN", "a");
        assertValidToLocale("us_EN_SFsafdFDsdfF", "us", "EN", "SFsafdFDsdfF");
    }
    private void assertInvalidToLocale(String input) {
        try {
            LocaleUtils.toLocale(input);
            fail("Invalid input value: " + input);
        } catch (IllegalArgumentException iae) {
            // Exception thrown as expected
        }
    }
    private void assertValidToLocale(String input) {
        try {
            LocaleUtils.toLocale(input);
        } catch (IllegalArgumentException iae) {
            fail("Valid input value should not throw exception: " + input);
        }
    }
    private void assertValidToLocale(String input, String language, String country, String variant) {
        Locale locale = LocaleUtils.toLocale(input);
        assertEquals(language, locale.getLanguage());
        assertEquals(country, locale.getCountry());
        assertEquals(variant, locale.getVariant());
    }
    @Test
    public void test18() {
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
    public void test19() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));

        // Additional test cases to kill more mutants
        assertEquals(set.contains(new Locale("de", "DE")), LocaleUtils.isAvailableLocale(new Locale("de", "DE")));
        assertEquals(set.contains(new Locale("it")), LocaleUtils.isAvailableLocale(new Locale("it")));
        assertEquals(set.contains(new Locale("es", "ES")), LocaleUtils.isAvailableLocale(new Locale("es", "ES")));
        assertEquals(set.contains(new Locale("pt", "BR")), LocaleUtils.isAvailableLocale(new Locale("pt", "BR")));
    }
    @Test
    public void test20() {
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
    public void test21() {
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
    }        
    @Test
    public void test26() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
    }
    @Test
    public void test27() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
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
    }
    @Test
    public void test29() {
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
    public void test30() {
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
    public void test31() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
    }
    @Test
    public void test32() {
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
    public void test33() {
        try {
            LocaleUtils.toLocale("");
            fail("Should fail if input is empty");
        } catch (final IllegalArgumentException iae) {
        }
        
        try {
            LocaleUtils.toLocale("_GB_");
            fail("Should fail if starts with underscore and is not at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
        
        try {
            LocaleUtils.toLocale("123");
            fail("Should fail if input contains digits");
        } catch (final IllegalArgumentException iae) {
        }
        
        try {
            LocaleUtils.toLocale("___");
            fail("Should fail if input contains only underscores");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test34() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("xx")));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("en_XX")));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("en", "XX")));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("en", "US", "XX")));
    }
    @Test
    public void test35() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        
        // Regression tests
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("CA", new String[]{"fr", "en"});
        assertLanguageByCountry("", new String[0]);
    }
    @Test
    public void test36() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        // Regression test cases for killing more mutants
        assertCountriesByLanguage("en", new String[]{"US", "GB", "CA", "AU", "NZ", "IE"});
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "CA", "CH", "LU", "MC"});
        assertCountriesByLanguage("es", new String[]{"ES", "MX", "AR", "CO", "PE", "VE"});
        assertCountriesByLanguage("ru", new String[]{"RU", "UA", "KZ", "BY", "UZ", "KG", "TJ"});
        assertCountriesByLanguage("zh", new String[]{"CN", "TW", "HK", "MO", "SG"});
    }
}
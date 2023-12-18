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
        
        // New regression test cases
        assertValidToLocale("_G3", "", "G3", "");
        assertValidToLocale("_GB1", "", "GB1", "");
        assertValidToLocale("_GB_A1", "", "GB", "A1");
        assertValidToLocale("_GB_a1111", "", "GB", "a1111");
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
        
        // New regression test cases
        try {
            LocaleUtils.toLocale("u_");
            fail("Must be 2 chars if less than 5");
        } catch (IllegalArgumentException iae) {}
        
        try {
            LocaleUtils.toLocale("999");
            fail("Must be 2 chars if less than 5");
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
        
        // New regression test cases
        try {
            LocaleUtils.toLocale("uS__");
            fail("Should fail first part not lowercase");
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
        
        // New regression test cases
        try {
            LocaleUtils.toLocale("us__A");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        
        try {
            LocaleUtils.toLocale("us_ENE");
            fail("Must be 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test4() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
        
        // New regression test cases
        try {
            LocaleUtils.toLocale("fr__p");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        
        try {
            LocaleUtils.toLocale("fr__P_");
            fail("Should fail as not 3, 5 or 7+ in length");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test5() {
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN,
                LOCALE_EN_US
            });
    }
    @Test
    public void test6() {
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
            new Locale[] {
                LOCALE_EN,
                LOCALE_EN_US,
                LOCALE_EN_US_ZZZZ
            });
    }
    @Test
    public void test7() {
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] {
                LOCALE_EN,
                LOCALE_EN_US,
                LOCALE_QQ,
                LOCALE_QQ_US
            });
    }
    @Test
    public void test8() {
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN,
                LOCALE_EN_US,
                LOCALE_EN_US_ZZZZ,
                LOCALE_QQ,
                LOCALE_QQ_US,
                LOCALE_QQ_US_ZZZZ
            });
    }
    @Test
    public void test9() {
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN_US,
            new Locale[] {
                LOCALE_EN,
                LOCALE_EN_US,
                LOCALE_FR,
                LOCALE_FR_CA,
                LOCALE_FR_CA_US
            });
    }
@Test
public void test10() {
    // existing test cases
    assertLocaleLookupList(LOCALE_QQ, LOCALE_EN, new Locale[]{LOCALE_QQ, LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN, LOCALE_EN_US, new Locale[]{LOCALE_EN, LOCALE_EN_US});
    assertLocaleLookupList(LOCALE_EN, LOCALE_FR, new Locale[]{LOCALE_EN, LOCALE_FR});
}
@Test
public void test11() {
    // existing test cases
    assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ_ZZ, new Locale[]{LOCALE_QQ, LOCALE_QQ_ZZ});
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ, new Locale[]{LOCALE_EN_US, LOCALE_EN, LOCALE_QQ});
    assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR, new Locale[]{LOCALE_EN_US, LOCALE_EN, LOCALE_FR});
}
@Test
public void test12() {
    // existing test cases
    // ...

    // regression test cases
    assertValidToLocale("es_AR_P", "", "AR", "P");
    assertValidToLocale("it_CH_POSIX", "", "CH", "POSIX");
    assertValidToLocale("fr_FR_ZZZZ", "", "FR", "ZZZZ");
}
@Test
public void test13() {
    // existing test cases
    // ...

    // regression test cases
    assertValidToLocale("es");
    assertValidToLocale("it");
    assertValidToLocale("fr");
}
@Test
public void test14() {
    // existing test cases
    // ...

    // regression test cases
    assertValidToLocale("fr_FR", "fr", "FR");
    assertValidToLocale("es_ES", "es", "ES");
    assertValidToLocale("it_IT", "it", "IT");
}
@Test
public void test15() {
    // existing test cases
    // ...

    // regression test cases
    assertValidToLocale("fr_FR_A", "fr", "FR", "A");
    assertValidToLocale("es_ES_POSIX", "es", "ES", "POSIX");
    assertValidToLocale("it_IT_ZZZZ", "it", "IT", "ZZZZ");
}
@Test
public void test16() {
    // existing test cases
    // ...

    // regression test cases
    assertLocaleLookupList(LOCALE_EN, LOCALE_FR_CA, 
            new Locale[]{
                LOCALE_EN,
                LOCALE_FR_CA});
    assertLocaleLookupList(LOCALE_FR_CA, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN_US});
}
@Test
public void test17() {
    // existing test cases
    // ...

    // regression test cases
    assertEquals(set.contains(LOCALE_ES), LocaleUtils.isAvailableLocale(LOCALE_ES));
    assertEquals(set.contains(LOCALE_IT), LocaleUtils.isAvailableLocale(LOCALE_IT));
    assertEquals(set.contains(LOCALE_FR_FR), LocaleUtils.isAvailableLocale(LOCALE_FR_FR));
}
    @Test
    public void test18() {
        // Original test case
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
        
        // New regression test case 1
        LocaleUtils.resetAvailableLocales();
        set = LocaleUtils.availableLocaleSet();
        set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        jdkLocaleArray = Locale.getAvailableLocales();
        jdkLocaleList = Arrays.asList(jdkLocaleArray);
        jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        assertEquals(jdkLocaleSet, set);
        
        // New regression test case 2
        LocaleUtils.addLocale(LOCALE_EN);
        set = LocaleUtils.availableLocaleSet();
        set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        jdkLocaleArray = Locale.getAvailableLocales();
        jdkLocaleList = Arrays.asList(jdkLocaleArray);
        jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        jdkLocaleSet.add(LOCALE_EN); // Mutant added LOCALE_EN to the available locales
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test19() {
        // Original test case
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // New regression test case 1
        LocaleUtils.resetAvailableLocales();
        set = LocaleUtils.availableLocaleSet();
        assertFalse(LocaleUtils.isAvailableLocale(LOCALE_EN));
        
        // New regression test case 2
        LocaleUtils.addLocale(LOCALE_EN);
        set = LocaleUtils.availableLocaleSet();
        assertTrue(LocaleUtils.isAvailableLocale(LOCALE_EN));
    }
    @Test
    public void test20() {
        // Original test case
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));

        // Additional test cases
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("xx")));
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("zz", "ZZ")));
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("en_US_ZZZ")));
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("fr_CA_A")));
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("qq", "ZZ")));
        assertEquals(false, LocaleUtils.isAvailableLocale(new Locale("qq", "zz")));
    }
    @Test
    public void test21() {
        assertLanguageByCountry("US", new String[]{"en", "es"});
        assertLanguageByCountry("FR", new String[]{"fr"});
        assertLanguageByCountry("CA", new String[]{"en", "fr"});
        assertLanguageByCountry("DEU", new String[]{"de"});
    }
    @Test
    public void test22() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        
        // Regression test cases
        assertCountriesByLanguage("en", new String[]{"US", "GB", "AU", "CA"});
        assertCountriesByLanguage("ja", new String[]{"JP"});
        assertCountriesByLanguage("fr", new String[]{"FR", "BE", "CH", "LU", "CA"});
    }
}
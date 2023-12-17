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
        assertInvalidToLocale("_G"); // invalid locale format, less than 2 chars
        assertInvalidToLocale("_gb"); // invalid locale format, not uppercase
        assertInvalidToLocale("_GBa"); // invalid locale format, not uppercase
        assertInvalidToLocale("_1B"); // invalid locale format, not letter
        assertInvalidToLocale("_GB_"); // invalid locale format, less than 5 chars
        assertInvalidToLocale("_GBAP"); // invalid locale format, no underscore after the country
    }
    @Test
    public void test1() {
        assertInvalidToLocale("Us"); // invalid locale format, not lowercase
        assertInvalidToLocale("US"); // invalid locale format, not lowercase
        assertInvalidToLocale("uS"); // invalid locale format, not lowercase
        assertInvalidToLocale("u#"); // invalid locale format, not lowercase
        assertInvalidToLocale("u"); // invalid locale format, less than 2 chars
        assertInvalidToLocale("uuu"); // invalid locale format, less than 2 chars
        assertInvalidToLocale("uu_U"); // invalid locale format, less than 2 chars
    }
    @Test
    public void test2() {
        assertInvalidToLocale("us-EN"); // invalid locale format, not underscore
        assertInvalidToLocale("us_En"); // invalid locale format, second part not uppercase
        assertInvalidToLocale("us_en"); // invalid locale format, second part not uppercase
        assertInvalidToLocale("us_eN"); // invalid locale format, second part not uppercase
        assertInvalidToLocale("uS_EN"); // invalid locale format, first part not lowercase
        assertInvalidToLocale("us_E3"); // invalid locale format, second part not uppercase
    }
    @Test
    public void test3() {
        assertInvalidToLocale("us_EN-a"); // invalid locale format, not underscore
        assertInvalidToLocale("uu_UU_"); // invalid locale format, not 3, 5 or 7+ in length
    }
    private void assertInvalidToLocale(String str) {
        try {
            LocaleUtils.toLocale(str);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected to throw IllegalArgumentException
        }
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
        
        // regression tests
        assertLocaleLookupList(LOCALE_EN, LOCALE_EN_US, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_FR_CA, 
            new Locale[]{
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
        
        // regression tests
        assertLocaleLookupList(LOCALE_FR_CA, LOCALE_QQ,
            new Locale[] {
                LOCALE_FR_CA,
                LOCALE_FR,
                LOCALE_EN,
                LOCALE_QQ});
        assertLocaleLookupList(LOCALE_EN, LOCALE_FR_CA, new Locale[]{
            LOCALE_EN,
            LOCALE_FR_CA,
            LOCALE_FR});
    }
    @Test
    public void test6() {
        assertLocaleLookupList(null, null, new Locale[0]);
        assertLocaleLookupList(LOCALE_EN, null, new Locale[]{LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN,
                LOCALE_EN_US
            });
    }        
    @Test
    public void test7() {
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_EN_US, 
            new Locale[]{
                LOCALE_EN,
                LOCALE_EN_US
        });
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ,
            new Locale[] { 
                LOCALE_QQ,
                LOCALE_EN_US,
                LOCALE_EN
            });
        assertLocaleLookupList(LOCALE_EN_US, LOCALE_QQ_ZZ,
            new Locale[] {
                LOCALE_EN,
                LOCALE_QQ_ZZ,
                LOCALE_EN_US
            });
    }
    @Test
    public void test8() {
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
        
        // Additional regression test cases
        assertValidToLocale("_US", "", "US", "");
        assertValidToLocale("_US_E", "", "US", "E");
        assertValidToLocale("_US_POSIX", "", "US", "POSIX");
        try {
            LocaleUtils.toLocale("_1");
            fail("Must have at least 2 characters if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_aB");
            fail("Must be uppercase if starts with underscore");
        } catch (final IllegalArgumentException iae) {
        }
        try {
            LocaleUtils.toLocale("_ABc");
            fail("Must have underscore after the country if starts with underscore and is at least 5 chars");
        } catch (final IllegalArgumentException iae) {
        }
    }
    @Test
    public void test9() {
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

        // Additional regression test cases
        assertValidToLocale("ch");
        assertValidToLocale("en");
        assertValidToLocale("1s");
        try {
            LocaleUtils.toLocale("U");
            fail("Must have at least 2 characters");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("aB");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("abE3");
            fail("Should fail if not lowercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test10() {
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

        // Additional regression test cases
        assertValidToLocale("us_FR", "us", "FR");
        assertValidToLocale("us_CH", "us", "CH");
        try {
            LocaleUtils.toLocale("us-fR");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_FRa");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_IM");
            fail("Should fail as not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS_G3");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_12");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test11() {
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

        // Additional regression test cases
        assertValidToLocale("us_zH_A", "us", "ZH", "A");
        try {
            LocaleUtils.toLocale("us-EN-A");
            fail("Should fail as not underscore");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_En_B");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_eN_C3");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("uS__D");
            fail("Should fail first part not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_E3_F");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("us_ZH_eF");
            fail("Should fail second part not uppercase");
        } catch (IllegalArgumentException iae) {}
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

        // Additional regression test cases
        LocaleUtils.restoreAvailableLocales();
        set = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
    }
    @Test
    public void test13() {
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

        // Additional regression test cases
        assertLocaleLookupList(LOCALE_US, null,
                new Locale[]{LOCALE_US});
        assertLocaleLookupList(LOCALE_US, LOCALE_US,
                new Locale[]{LOCALE_US});
        assertLocaleLookupList(LOCALE_ZH, LOCALE_EN_US,
                new Locale[]{LOCALE_ZH, LOCALE_EN_US, LOCALE_EN});
        assertLocaleLookupList(LOCALE_ZH, LOCALE_EN_US_ZZZZ,
                new Locale[]{LOCALE_ZH, LOCALE_EN_US_ZZZZ, LOCALE_EN_US, LOCALE_EN});
    }
    @Test
    public void test14() {
        assertNotNull(new LocaleUtils());
        Constructor<?>[] cons = LocaleUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(LocaleUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(LocaleUtils.class.getModifiers()));

        // Additional regression test cases
        boolean isPrivateConstructor = true;
        for (Constructor<?> con : cons) {
            if (Modifier.isPrivate(con.getModifiers())) {
                isPrivateConstructor = true;
                break;
            }
        }
        assertTrue(isPrivateConstructor);
    }
    @Test
    public void test15() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});

        // Additional regression test cases
        assertCountriesByLanguage("fr", new String[]{"FR", "CA", "CH"});
        assertCountriesByLanguage("us", new String[]{"US"});
        assertCountriesByLanguage("cn", new String[]{"CN"});
    }
    @Test
    public void test16() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});

        // Additional regression test cases
        assertLanguageByCountry("FR", new String[]{"fr"});
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("CN", new String[]{"zh"});
    }
    @Test
    public void test17() {
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

        // Additional regression test cases
        assertLocaleLookupList(LOCALE_US, LOCALE_ZH,
                new Locale[]{LOCALE_US, LOCALE_ZH});
        assertLocaleLookupList(LOCALE_US, LOCALE_FR_CA,
                new Locale[]{LOCALE_US, LOCALE_FR_CA, LOCALE_FR});
        assertLocaleLookupList(LOCALE_US_ZZZZ, LOCALE_QQ,
                new Locale[]{LOCALE_US_ZZZZ, LOCALE_US, LOCALE_QQ});
    }
    @Test
    public void test18() {
        List<Locale> list = LocaleUtils.availableLocaleList();
        List<Locale> list2 = LocaleUtils.availableLocaleList();
        assertNotNull(list);
        assertSame(list, list2);
        assertUnmodifiableCollection(list);

        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        assertEquals(jdkLocaleList, list);

        // Additional regression test cases
        List<Locale> emptyList = Collections.emptyList();
        assertUnmodifiableCollection(emptyList);
    }
    @Test
    public void test19() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");

        // Additional regression test cases
        assertValidToLocale("fr__A", "fr", "", "A");
        try {
            LocaleUtils.toLocale("fr__-");
            fail("Should fail as not valid");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("FR__B");
            fail("Should fail as not lowercase");
        } catch (IllegalArgumentException iae) {}
        try {
            LocaleUtils.toLocale("fr__c3");
            fail("Should fail as not uppercase");
        } catch (IllegalArgumentException iae) {}
    }
    @Test
    public void test20() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));

        // Additional regression test cases
        assertEquals(set.contains(LOCALE_US), LocaleUtils.isAvailableLocale(LOCALE_US));
        assertEquals(set.contains(LOCALE_ZH), LocaleUtils.isAvailableLocale(LOCALE_ZH));
        assertEquals(set.contains(LOCALE_FR_CH), LocaleUtils.isAvailableLocale(LOCALE_FR_CH));
    }
    @Test
    public void test21() {
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
    public void test22() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        assertEquals(set.contains(LOCALE_FR), LocaleUtils.isAvailableLocale(LOCALE_FR));
        assertEquals(set.contains(LOCALE_FR_CA), LocaleUtils.isAvailableLocale(LOCALE_FR_CA));
        assertEquals(set.contains(LOCALE_QQ), LocaleUtils.isAvailableLocale(LOCALE_QQ));
        assertEquals(set.contains(LOCALE_QQ_ZZ), LocaleUtils.isAvailableLocale(LOCALE_QQ_ZZ));
        
        // Regression test cases
        assertEquals(set.contains(new Locale("en", "US", "variant")), LocaleUtils.isAvailableLocale(new Locale("en", "US", "variant")));
        assertEquals(set.contains(new Locale("fr", "CA", "variant")), LocaleUtils.isAvailableLocale(new Locale("fr", "CA", "variant")));
        assertEquals(set.contains(new Locale("es", "MX")), LocaleUtils.isAvailableLocale(new Locale("es", "MX")));
        assertEquals(set.contains(new Locale("zh", "HK")), LocaleUtils.isAvailableLocale(new Locale("zh", "HK")));
    }
    @Test
    public void test23() {
        Locale invalidLocale = new Locale("invalid");
        assertFalse(LocaleUtils.isAvailableLocale(invalidLocale));
    }
    @Test
    public void test24() {
        assertFalse(LocaleUtils.isAvailableLocale(null));
    }
    @Test
    public void test25() {
        Locale validLocale = new Locale("en", "US");
        assertTrue(LocaleUtils.isAvailableLocale(validLocale));
    }
    @Test
    public void test26() {
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        
        // Regression test case 1
        // Testing with a country code that is not in the availableLocaleList()
        assertLanguageByCountry("US", new String[0]);
        
        // Regression test case 2
        // Testing with a country code that is an empty string
        assertLanguageByCountry("", new String[0]);
        
        // Regression test case 3
        // Testing with a country code that is a valid code but does not have any languages associated with it
        assertLanguageByCountry("IN", new String[0]);
    }
    @Test
    public void test27() {
        assertCountriesByLanguage(null, new String[0]);
        assertCountriesByLanguage("de", new String[]{"DE", "CH", "AT", "LU"});
        assertCountriesByLanguage("zz", new String[0]);
        assertCountriesByLanguage("it", new String[]{"IT", "CH"});
        assertCountriesByLanguage("fr", new String[]{"FR", "CA", "BE"});
        assertCountriesByLanguage("en", new String[]{"US", "GB", "AU"});
    }
}
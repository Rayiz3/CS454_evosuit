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
        assertValidToLocale("_G", "", "G", "");
    }
    @Test
    public void test1() {
        assertValidToLocale("_GB_P", "", "GB", "P");
    }
    @Test
    public void test2() {
        assertValidToLocale("uU");
    }
    @Test
    public void test3() {
        assertValidToLocale("u_2");
    }
    @Test
    public void test4() {
        assertValidToLocale("us_eN");
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
        assertLocaleLookupList(LOCALE_EN_US, null,
            new Locale[] {
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN,
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
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US_ZZZZ,
            new Locale[] {
                LOCALE_EN_US_ZZZZ,
                LOCALE_EN_US,
                LOCALE_EN});
        assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN,
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
    assertLocaleLookupList(LOCALE_QQ, null, new Locale[]{LOCALE_QQ});
    assertLocaleLookupList(LOCALE_FR, null, new Locale[]{LOCALE_FR});
    assertLocaleLookupList(LOCALE_FR_CA, null, new Locale[]{LOCALE_FR_CA});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, null,
        new Locale[]{
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN_US,
            LOCALE_EN});
    assertLocaleLookupList(LOCALE_EN, null,
        new Locale[]{
            LOCALE_EN});
}
@Test
public void test8() {
    assertLocaleLookupList(LOCALE_QQ, LOCALE_QQ, new Locale[]{LOCALE_QQ});
    assertLocaleLookupList(LOCALE_FR, LOCALE_EN, new Locale[]{LOCALE_FR, LOCALE_EN});
    assertLocaleLookupList(LOCALE_FR_CA, LOCALE_FR_CA, new Locale[]{LOCALE_FR_CA});
    assertLocaleLookupList(LOCALE_FR_JP, LOCALE_FR, new Locale[]{LOCALE_FR_JP, LOCALE_FR});
    assertLocaleLookupList(LOCALE_EN_US_ZZZZ, LOCALE_EN_US, 
        new Locale[]{
            LOCALE_EN_US_ZZZZ,
            LOCALE_EN_US,
            LOCALE_EN});
}
    @Test
    public void test9() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
    }
    @Test
    public void test10() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        assertValidToLocale("qq");
    }        
    @Test
    public void test11() {
        assertValidToLocale("us_EN", "us", "EN");
        assertValidToLocale("us_ZH", "us", "ZH");
    }        
    @Test
    public void test12() {
        assertValidToLocale("us_EN_A", "us", "EN", "A");
    }
    @Test
    public void test13() {
        assertValidToLocale("fr__P", "fr", "", "P");
        assertValidToLocale("fr__POSIX", "fr", "", "POSIX");
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
    }
    @Test
    public void test15() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
    }
    @Test
    public void test16() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        assertValidToLocale("qq");
        
        assertInvalidToLocale("Us");
        assertInvalidToLocale("US");
        assertInvalidToLocale("uS");
        assertInvalidToLocale("u#");
        assertInvalidToLocale("u");
        assertInvalidToLocale("uuu");
        assertInvalidToLocale("uu_U");
    }        
    @Test
    public void test17() {
        assertValidToLocale("us_EN", "us", "EN");
        assertValidToLocale("us_ZH", "us", "ZH");
        
        assertInvalidToLocale("us-EN");
        assertInvalidToLocale("us_En");
        assertInvalidToLocale("us_en");
        assertInvalidToLocale("us_eN");
        assertInvalidToLocale("uS_EN");
        assertInvalidToLocale("us_E3");
    }        
    @Test
    public void test18() {
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        
        assertInvalidToLocale("us_EN-a");
        assertInvalidToLocale("uu_UU_");
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
        
        assertEquals(false, LocaleUtils.isAvailableLocale(LOCALE_A));
        assertEquals(false, LocaleUtils.isAvailableLocale(LOCALE_US_EN));
        assertEquals(false, LocaleUtils.isAvailableLocale(LOCALE_JA_JP));
    }
    @Test
    public void test20() {
        assertValidToLocale("_GB", "", "GB", "");
        assertValidToLocale("_GB_P", "", "GB", "P");
        assertValidToLocale("_GB_POSIX", "", "GB", "POSIX");
        assertValidToLocale("_GE", "", "GE", "");
        assertInvalidToLocale("_Ge");
        assertInvalidToLocale("_gE");
        assertInvalidToLocale("_1E");
        assertInvalidToLocale("_GE_");
        assertInvalidToLocale("_GEAP");
    }
    @Test
    public void test21() {
        assertEquals(null, LocaleUtils.toLocale((String) null));
        
        assertValidToLocale("us");
        assertValidToLocale("fr");
        assertValidToLocale("de");
        assertValidToLocale("zh");
        assertValidToLocale("qq");
        assertInvalidToLocale("Us");
        assertInvalidToLocale("US");
        assertInvalidToLocale("uS");
        assertInvalidToLocale("u#");
        assertInvalidToLocale("u");
        assertInvalidToLocale("uuu");
        assertInvalidToLocale("uu_U");
        assertValidToLocale("hs");
        assertInvalidToLocale("Hs");
    }        
    @Test
    public void test22() {
        assertValidToLocale("us_EN", "us", "EN");
        assertValidToLocale("us_ZH", "us", "ZH");
        assertInvalidToLocale("us-EN");
        assertInvalidToLocale("us_En");
        assertInvalidToLocale("us_en");
        assertInvalidToLocale("us_eN");
        assertInvalidToLocale("uS_EN");
        assertInvalidToLocale("us_E3");
    }        
    @Test
    public void test23() {
        assertValidToLocale("us_EN_A", "us", "EN", "A");
        assertInvalidToLocale("us_EN-a");
        assertInvalidToLocale("uu_UU_");
        assertValidToLocale("us_GE_12", "us", "GE", "12");
        assertInvalidToLocale("us_GE-a_12");
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
        assertEquals(false, LocaleUtils.isAvailableLocale(LOCALE_A));
        assertEquals(false, LocaleUtils.isAvailableLocale(LOCALE_US_EN));
        assertEquals(false, LocaleUtils.isAvailableLocale(LOCALE_JA_JP));
        assertEquals(true, LocaleUtils.isAvailableLocale(LOCALE_GE));
        assertEquals(true, LocaleUtils.isAvailableLocale(LOCALE_TYPICK));
    }
    @Test
    public void test25() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        Set<Locale> set2 = LocaleUtils.availableLocaleSet();
        assertNotNull(set);
        assertSame(set, set2);
        assertUnmodifiableCollection(set);
        
        // Change the input value
        Locale[] jdkLocaleArray = Locale.getAvailableLocales();
        List<Locale> jdkLocaleList = Arrays.asList(jdkLocaleArray);
        Set<Locale> jdkLocaleSet = new HashSet<Locale>(jdkLocaleList);
        jdkLocaleSet.remove(LOCALE_EN_US);
        assertEquals(jdkLocaleSet, set);
    }
    @Test
    public void test26() {
        Set<Locale> set = LocaleUtils.availableLocaleSet();
        
        // Change the input value
        assertEquals(set.contains(LOCALE_EN), LocaleUtils.isAvailableLocale(LOCALE_EN));
        assertEquals(set.contains(LOCALE_EN_US), LocaleUtils.isAvailableLocale(LOCALE_EN_US));
        assertEquals(set.contains(LOCALE_EN_US_ZZZZ), LocaleUtils.isAvailableLocale(LOCALE_EN_US_ZZZZ));
        
        // Add new regression test cases
        assertEquals(set.contains(LOCALE_EN_UK), LocaleUtils.isAvailableLocale(LOCALE_EN_UK));
        assertEquals(set.contains(LOCALE_EN_CA), LocaleUtils.isAvailableLocale(LOCALE_EN_CA));
        assertEquals(set.contains(LOCALE_JA_JP), LocaleUtils.isAvailableLocale(LOCALE_JA_JP));
    }
    @Test
    public void test27() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("xx")));
    }
    @Test
    public void test28() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("yy")));
    }
    @Test
    public void test29() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("zz")));
    }
    @Test
    public void test30() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("aa")));
    }
    @Test
    public void test31() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("bb")));
    }
    @Test
    public void test32() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("cc")));
    }
    @Test
    public void test33() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("dd")));
    }
    @Test
    public void test34() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("ee")));
    }
    @Test
    public void test35() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("ff")));
    }
    @Test
    public void test36() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("gg")));
    }
    @Test
    public void test37() {
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("hh")));
    }
    @Test
    public void test38() {
        // original test cases
        assertLanguageByCountry(null, new String[0]);
        assertLanguageByCountry("GB", new String[]{"en"});
        assertLanguageByCountry("ZZ", new String[0]);
        assertLanguageByCountry("CH", new String[]{"fr", "de", "it"});
        
        // regression test cases
        assertLanguageByCountry("", new String[0]);
        assertLanguageByCountry("US", new String[]{"en"});
        assertLanguageByCountry("CN", new String[0]);
        assertLanguageByCountry("DE", new String[]{"de"});
        assertLanguageByCountry("RU", new String[]{"ru"});
    }
}
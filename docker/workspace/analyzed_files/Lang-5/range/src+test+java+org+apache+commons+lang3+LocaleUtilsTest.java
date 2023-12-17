{
  "filepath": "/tmp/Lang-5b/src/test/java/org/apache/commons/lang3/LocaleUtilsTest.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "LocaleUtilsTest",
      "is_interface": false,
      "parent_types": [],
      "begin_line": 45,
      "end_line": 545,
      "comment": "\n * Unit tests for {@link LocaleUtils}.\n *\n * @version $Id$\n "
    },
    {
      "type": "field",
      "varNames": [
        "LOCALE_EN"
      ],
      "begin_line": 47,
      "end_line": 47,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "LOCALE_EN_US"
      ],
      "begin_line": 48,
      "end_line": 48,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "LOCALE_EN_US_ZZZZ"
      ],
      "begin_line": 49,
      "end_line": 49,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "LOCALE_FR"
      ],
      "begin_line": 50,
      "end_line": 50,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "LOCALE_FR_CA"
      ],
      "begin_line": 51,
      "end_line": 51,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "LOCALE_QQ"
      ],
      "begin_line": 52,
      "end_line": 52,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "LOCALE_QQ_ZZ"
      ],
      "begin_line": 53,
      "end_line": 53,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.setUp()",
      "begin_line": 57,
      "end_line": 61,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testConstructor()",
      "begin_line": 67,
      "end_line": 75,
      "comment": "\n     * Test that constructors are public, and work, etc.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.assertValidToLocale(java.lang.String)",
      "begin_line": 83,
      "end_line": 90,
      "comment": "\n     * Pass in a valid language, test toLocale.\n     *\n     * @param language  the language string\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.assertValidToLocale(java.lang.String, java.lang.String, java.lang.String)",
      "begin_line": 99,
      "end_line": 106,
      "comment": "\n     * Pass in a valid language, test toLocale.\n     *\n     * @param localeString to pass to toLocale()\n     * @param language of the resulting Locale\n     * @param country of the resulting Locale\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.assertValidToLocale(java.lang.String, java.lang.String, java.lang.String, java.lang.String)",
      "begin_line": 116,
      "end_line": 125,
      "comment": "\n     * Pass in a valid language, test toLocale.\n     *\n     * @param localeString to pass to toLocale()\n     * @param language of the resulting Locale\n     * @param country of the resulting Locale\n     * @param variant of the resulting Locale\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testToLocale_1Part()",
      "begin_line": 130,
      "end_line": 172,
      "comment": "\n     * Test toLocale() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testToLocale_2Part()",
      "begin_line": 177,
      "end_line": 207,
      "comment": "\n     * Test toLocale() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testToLocale_3Part()",
      "begin_line": 212,
      "end_line": 233,
      "comment": "\n     * Test toLocale() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.assertLocaleLookupList(java.util.Locale, java.util.Locale, java.util.Locale[])",
      "begin_line": 243,
      "end_line": 251,
      "comment": "\n     * Helper method for local lookups.\n     *\n     * @param locale  the input locale\n     * @param defaultLocale  the input default locale\n     * @param expected  expected results\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testLocaleLookupList_Locale()",
      "begin_line": 257,
      "end_line": 272,
      "comment": "\n     * Test localeLookupList() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testLocaleLookupList_LocaleLocale()",
      "begin_line": 277,
      "end_line": 326,
      "comment": "\n     * Test localeLookupList() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testAvailableLocaleList()",
      "begin_line": 332,
      "end_line": 343,
      "comment": "\n     * Test availableLocaleList() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testAvailableLocaleSet()",
      "begin_line": 349,
      "end_line": 361,
      "comment": "\n     * Test availableLocaleSet() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testIsAvailableLocale()",
      "begin_line": 367,
      "end_line": 378,
      "comment": "\n     * Test availableLocaleSet() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.assertLanguageByCountry(java.lang.String, java.lang.String[])",
      "begin_line": 390,
      "end_line": 417,
      "comment": "\n     * Make sure the language by country is correct. It checks that \n     * the LocaleUtils.languagesByCountry(country) call contains the \n     * array of languages passed in. It may contain more due to JVM \n     * variations.\n     *\n     * @param country\n     * @param languages array of languages that should be returned\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testLanguagesByCountry()",
      "begin_line": 422,
      "end_line": 428,
      "comment": "\n     * Test languagesByCountry() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.assertCountriesByLanguage(java.lang.String, java.lang.String[])",
      "begin_line": 441,
      "end_line": 468,
      "comment": "\n     * Make sure the country by language is correct. It checks that \n     * the LocaleUtils.countryByLanguage(language) call contains the \n     * array of countries passed in. It may contain more due to JVM \n     * variations.\n     *\n     *\n     * @param language\n     * @param countries array of countries that should be returned\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testCountriesByLanguage()",
      "begin_line": 473,
      "end_line": 479,
      "comment": "\n     * Test countriesByLanguage() method.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.assertUnmodifiableCollection(java.util.Collection\u003c?\u003e)",
      "begin_line": 484,
      "end_line": 489,
      "comment": "\n     * @param coll  the collection to check\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testLang328()",
      "begin_line": 494,
      "end_line": 498,
      "comment": "\n     * Tests #LANG-328 - only language+variant\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtilsTest.testLang865()",
      "begin_line": 503,
      "end_line": 543,
      "comment": "\n     * Tests #LANG-865, strings starting with an underscore.\n     "
    }
  ]
}
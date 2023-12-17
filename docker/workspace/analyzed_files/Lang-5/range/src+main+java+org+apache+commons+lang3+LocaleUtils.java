{
  "filepath": "/tmp/Lang-5b/src/main/java/org/apache/commons/lang3/LocaleUtils.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "LocaleUtils",
      "is_interface": false,
      "parent_types": [],
      "begin_line": 39,
      "end_line": 301,
      "comment": "\n * \u003cp\u003eOperations to assist when working with a {@link Locale}.\u003c/p\u003e\n *\n * \u003cp\u003eThis class tries to handle {@code null} input gracefully.\n * An exception will not be thrown for a {@code null} input.\n * Each method documents its behaviour in more detail.\u003c/p\u003e\n *\n * @since 2.2\n * @version $Id$\n "
    },
    {
      "type": "field",
      "varNames": [
        "cLanguagesByCountry"
      ],
      "begin_line": 42,
      "end_line": 43,
      "comment": " Concurrent map of language locales by country. "
    },
    {
      "type": "field",
      "varNames": [
        "cCountriesByLanguage"
      ],
      "begin_line": 46,
      "end_line": 47,
      "comment": " Concurrent map of country locales by language. "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.lang3.LocaleUtils.LocaleUtils()",
      "begin_line": 56,
      "end_line": 58,
      "comment": "\n     * \u003cp\u003e{@code LocaleUtils} instances should NOT be constructed in standard programming.\n     * Instead, the class should be used as {@code LocaleUtils.toLocale(\"en_GB\");}.\u003c/p\u003e\n     *\n     * \u003cp\u003eThis constructor is public to permit tools that require a JavaBean instance\n     * to operate.\u003c/p\u003e\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtils.toLocale(java.lang.String)",
      "begin_line": 88,
      "end_line": 128,
      "comment": "\n     * \u003cp\u003eConverts a String to a Locale.\u003c/p\u003e\n     *\n     * \u003cp\u003eThis method takes the string format of a locale and creates the\n     * locale object from it.\u003c/p\u003e\n     *\n     * \u003cpre\u003e\n     *   LocaleUtils.toLocale(\"en\")         \u003d new Locale(\"en\", \"\")\n     *   LocaleUtils.toLocale(\"en_GB\")      \u003d new Locale(\"en\", \"GB\")\n     *   LocaleUtils.toLocale(\"en_GB_xxx\")  \u003d new Locale(\"en\", \"GB\", \"xxx\")   (#)\n     * \u003c/pre\u003e\n     *\n     * \u003cp\u003e(#) The behaviour of the JDK variant constructor changed between JDK1.3 and JDK1.4.\n     * In JDK1.3, the constructor upper cases the variant, in JDK1.4, it doesn\u0027t.\n     * Thus, the result from getVariant() may vary depending on your JDK.\u003c/p\u003e\n     *\n     * \u003cp\u003eThis method validates the input strictly.\n     * The language code must be lowercase.\n     * The country code must be uppercase.\n     * The separator must be an underscore.\n     * The length must be correct.\n     * \u003c/p\u003e\n     *\n     * @param str  the locale String to convert, null returns null\n     * @return a Locale, null if null input\n     * @throws IllegalArgumentException if the string is an invalid format\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtils.localeLookupList(java.util.Locale)",
      "begin_line": 143,
      "end_line": 145,
      "comment": "\n     * \u003cp\u003eObtains the list of locales to search through when performing\n     * a locale search.\u003c/p\u003e\n     *\n     * \u003cpre\u003e\n     * localeLookupList(Locale(\"fr\",\"CA\",\"xxx\"))\n     *   \u003d [Locale(\"fr\",\"CA\",\"xxx\"), Locale(\"fr\",\"CA\"), Locale(\"fr\")]\n     * \u003c/pre\u003e\n     *\n     * @param locale  the locale to start from\n     * @return the unmodifiable list of Locale objects, 0 being locale, not null\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtils.localeLookupList(java.util.Locale, java.util.Locale)",
      "begin_line": 165,
      "end_line": 180,
      "comment": "\n     * \u003cp\u003eObtains the list of locales to search through when performing\n     * a locale search.\u003c/p\u003e\n     *\n     * \u003cpre\u003e\n     * localeLookupList(Locale(\"fr\", \"CA\", \"xxx\"), Locale(\"en\"))\n     *   \u003d [Locale(\"fr\",\"CA\",\"xxx\"), Locale(\"fr\",\"CA\"), Locale(\"fr\"), Locale(\"en\"]\n     * \u003c/pre\u003e\n     *\n     * \u003cp\u003eThe result list begins with the most specific locale, then the\n     * next more general and so on, finishing with the default locale.\n     * The list will never contain the same locale twice.\u003c/p\u003e\n     *\n     * @param locale  the locale to start from, null returns empty list\n     * @param defaultLocale  the default locale to use if no other is found\n     * @return the unmodifiable list of Locale objects, 0 being locale, not null\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtils.availableLocaleList()",
      "begin_line": 192,
      "end_line": 194,
      "comment": "\n     * \u003cp\u003eObtains an unmodifiable list of installed locales.\u003c/p\u003e\n     * \n     * \u003cp\u003eThis method is a wrapper around {@link Locale#getAvailableLocales()}.\n     * It is more efficient, as the JDK method must create a new array each\n     * time it is called.\u003c/p\u003e\n     *\n     * @return the unmodifiable list of available locales\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtils.availableLocaleSet()",
      "begin_line": 206,
      "end_line": 208,
      "comment": "\n     * \u003cp\u003eObtains an unmodifiable set of installed locales.\u003c/p\u003e\n     * \n     * \u003cp\u003eThis method is a wrapper around {@link Locale#getAvailableLocales()}.\n     * It is more efficient, as the JDK method must create a new array each\n     * time it is called.\u003c/p\u003e\n     *\n     * @return the unmodifiable set of available locales\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtils.isAvailableLocale(java.util.Locale)",
      "begin_line": 217,
      "end_line": 219,
      "comment": "\n     * \u003cp\u003eChecks if the locale specified is in the list of available locales.\u003c/p\u003e\n     *\n     * @param locale the Locale object to check if it is available\n     * @return true if the locale is a known locale\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtils.languagesByCountry(java.lang.String)",
      "begin_line": 231,
      "end_line": 251,
      "comment": "\n     * \u003cp\u003eObtains the list of languages supported for a given country.\u003c/p\u003e\n     *\n     * \u003cp\u003eThis method takes a country code and searches to find the\n     * languages available for that country. Variant locales are removed.\u003c/p\u003e\n     *\n     * @param countryCode  the 2 letter country code, null returns empty\n     * @return an unmodifiable List of Locale objects, not null\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.lang3.LocaleUtils.countriesByLanguage(java.lang.String)",
      "begin_line": 263,
      "end_line": 284,
      "comment": "\n     * \u003cp\u003eObtains the list of countries supported for a given language.\u003c/p\u003e\n     * \n     * \u003cp\u003eThis method takes a language code and searches to find the\n     * countries available for that language. Variant locales are removed.\u003c/p\u003e\n     *\n     * @param languageCode  the 2 letter language code, null returns empty\n     * @return an unmodifiable List of Locale objects, not null\n     "
    },
    {
      "type": "class_interface",
      "name": "SyncAvoid",
      "is_interface": false,
      "parent_types": [],
      "begin_line": 288,
      "end_line": 299,
      "comment": " class to avoid synchronization (Init on demand)"
    },
    {
      "type": "field",
      "varNames": [
        "AVAILABLE_LOCALE_LIST"
      ],
      "begin_line": 290,
      "end_line": 290,
      "comment": " Unmodifiable list of available locales. "
    },
    {
      "type": "field",
      "varNames": [
        "AVAILABLE_LOCALE_SET"
      ],
      "begin_line": 292,
      "end_line": 292,
      "comment": " Unmodifiable set of available locales. "
    }
  ]
}
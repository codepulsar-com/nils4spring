package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

public class NilsConfigValueTestData {

  public static final String NULL_ESCAPE_PATTERN = null;
  public static final String NULL_INCLUDE_TAG = null;
  public static final Boolean NULL_SUPPRESS_ERRORS = null;
  public static final String NULL_CLASS_PREFIX_RESOLVER = null;
  public static final String NULL_TRANSLATION_FORMATTER = null;
  public static final String NULL_DATE_FORMAT_STYLE = null;
  public static final String NULL_BASE_FILE_NAME = null;
  public static final Boolean NULL_FALLBACK_ACTIVE = null;
  public static final String NULL_URL = null;
  public static final String NULL_USERNAME = null;
  public static final String NULL_PASSWORD = null;
  public static final String NULL_DRIVER_CLASS = null;
  public static final String NULL_ROOT_LOCALE = null;
  public static final String NULL_SCHEMA = null;
  public static final String NULL_TABLE_NAME = null;
  public static final String NULL_LOCALE_FIELD = null;
  public static final String NULL_KEY_FIELD = null;
  public static final String NULL_VALUE_FIELD = null;

  public static NilsConfigValues empty() {
    return new NilsConfigValues(
        NULL_ESCAPE_PATTERN,
        NULL_INCLUDE_TAG,
        NULL_SUPPRESS_ERRORS,
        NULL_CLASS_PREFIX_RESOLVER,
        NULL_TRANSLATION_FORMATTER,
        NULL_DATE_FORMAT_STYLE,
        NULL_BASE_FILE_NAME,
        NULL_FALLBACK_ACTIVE,
        NULL_URL,
        NULL_USERNAME,
        NULL_PASSWORD,
        NULL_DRIVER_CLASS,
        NULL_ROOT_LOCALE,
        NULL_SCHEMA,
        NULL_TABLE_NAME,
        NULL_LOCALE_FIELD,
        NULL_KEY_FIELD,
        NULL_VALUE_FIELD);
  }

  public static NilsConfigValues nonDefault() {
    return new NilsConfigValues(
        "MISSING - {0}",
        "INCLUDE",
        true,
        "fqn",
        "string",
        "long",
        "test",
        false,
        "url",
        "username",
        "password",
        "org.driverClass",
        "de_DE",
        "test_schema",
        "test_table_name",
        "test_locale_field",
        "test_key_field",
        "test_value_field");
  }
}

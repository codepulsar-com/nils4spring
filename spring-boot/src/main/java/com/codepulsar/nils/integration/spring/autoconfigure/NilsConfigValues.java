package com.codepulsar.nils.integration.spring.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration values for Nils. The prefix for all configuration is {@code nils}.
 *
 * <p>The class contains all possible configuration values for Adapters, but not all Adapters will
 * use all values.
 *
 * <p>See the documentation for a brief description.
 *
 * @param escapePattern Value of the escape pattern of missing translations.
 * @param includeTag Tag to include values from other translation keys.
 * @param suppressErrors Enable suppressing of errors.
 * @param classPrefixResolver Value of the {@code ClassPrefixResolver}.
 * @param translationFormatter Value of the {@code TranslationFormatter}.
 * @param dateFormatStyle Value of the {@code FormatStyle}.
 * @param baseFileName Value of the base file name.
 * @param fallbackActive Enable fallback to other translation resources.
 * @param url Value of the database URL.
 * @param username Value of the database user.
 * @param password Value of the database password.
 * @param driverClass Value of the database driver class.
 * @param rootLocale Value of the root locale.
 * @param schema Value of the database schema name.
 * @param tableName Value of the database table name.
 * @param localeField Value of the locale field in the database table.
 * @param keyField Value of the key field in the database table.
 * @param valueField Value of the value field in the database table.
 */
@ConfigurationProperties("nils")
public record NilsConfigValues(
    String escapePattern,
    String includeTag,
    Boolean suppressErrors,
    String classPrefixResolver,
    String translationFormatter,
    String dateFormatStyle,
    String baseFileName,
    Boolean fallbackActive,
    String url,
    String username,
    String password,
    String driverClass,
    String rootLocale,
    String schema,
    String tableName,
    String localeField,
    String keyField,
    String valueField) {}

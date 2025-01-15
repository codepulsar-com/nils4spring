package com.codepulsar.nils.integration.spring.autoconfigure.values;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.format.FormatStyle;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.codepulsar.nils.adapter.jdbc.JdbcAdapterConfig;
import com.codepulsar.nils.core.handler.ClassPrefixResolver;
import com.codepulsar.nils.core.handler.TranslationFormatter;

public class JdbcAdapterConfigUpdaterTest {
  private JdbcAdapterConfigUpdater underTest = new JdbcAdapterConfigUpdater();

  @Test
  void defaultValues() {
    // Arrange
    var config = JdbcAdapterConfig.init("x_url", "x_username", "x_password");
    var expected = JdbcAdapterConfig.init("x_url", "x_username", "x_password");
    var values = NilsConfigValueTestData.empty();

    // Act
    underTest.update(config, values);

    // Assert
    assertConfig(config, expected);
  }

  @Test
  void nonDefaultValues() {
    // Arrange
    var config = JdbcAdapterConfig.init("x_url", "x_username", "x_password");
    var expected =
        JdbcAdapterConfig.init("x_url", "x_username", "x_password")
            .classPrefixResolver(ClassPrefixResolver.FQN_CLASSNAME)
            .dateFormatStyle(FormatStyle.LONG)
            .escapePattern("MISSING - {0}")
            .includeTag("INCLUDE")
            .translationFormatter(TranslationFormatter.STRING_FORMAT)
            .fallbackActive(false)
            .suppressErrors(true)
            .driverClass("org.driverClass")
            .schema("test_schema")
            .tableName("test_table_name")
            .keyField("test_key_field")
            .valueField("test_value_field")
            .localeField("test_locale_field")
            .rootLocale(Locale.GERMANY)
            .cacheTimeout(987L);

    var values = NilsConfigValueTestData.nonDefault();

    // Act
    underTest.update(config, values);

    // Assert
    assertConfig(config, expected);
  }

  private void assertConfig(JdbcAdapterConfig actual, JdbcAdapterConfig expected) {
    assertThat(actual.getClassPrefixResolver()).isEqualTo(expected.getClassPrefixResolver());
    assertThat(actual.getDateFormatStyle()).isEqualTo(expected.getDateFormatStyle());
    assertThat(actual.getEscapePattern()).isEqualTo(expected.getEscapePattern());
    assertThat(actual.getIncludeTag()).isEqualTo(expected.getIncludeTag());
    assertThat(actual.getTranslationFormatter()).isEqualTo(expected.getTranslationFormatter());
    assertThat(actual.isFallbackActive()).isEqualTo(expected.isFallbackActive());
    assertThat(actual.isSuppressErrors()).isEqualTo(expected.isSuppressErrors());
    assertThat(actual.getUrl()).isEqualTo(expected.getUrl());
    assertThat(actual.getUsername()).isEqualTo(expected.getUsername());
    assertThat(actual.getPassword()).isEqualTo(expected.getPassword());
    assertThat(actual.getDriverClass()).isEqualTo(expected.getDriverClass());
    assertThat(actual.getSchema()).isEqualTo(expected.getSchema());
    assertThat(actual.getTableName()).isEqualTo(expected.getTableName());
    assertThat(actual.getKeyField()).isEqualTo(expected.getKeyField());
    assertThat(actual.getValueField()).isEqualTo(expected.getValueField());
    assertThat(actual.getLocaleField()).isEqualTo(expected.getLocaleField());
    assertThat(actual.getRootLocale()).isEqualTo(expected.getRootLocale());
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource("/allValues.properties")
public class NilsConfigValuesAllValuesSetTest {

  @Autowired private NilsConfigValues values;

  @Test
  void checkIfAllValuesAreSet() {
    assertThat(values.baseFileName()).isEqualTo("i18n.yaml");
    assertThat(values.classPrefixResolver()).isEqualTo("simple");
    assertThat(values.dateFormatStyle()).isEqualTo("medium");
    assertThat(values.escapePattern()).isEqualTo("[{0}]");
    assertThat(values.fallbackActive()).isEqualTo(true);
    assertThat(values.includeTag()).isEqualTo("_include");
    assertThat(values.suppressErrors()).isEqualTo(true);
    assertThat(values.translationFormatter()).isEqualTo("message");
    assertThat(values.url()).isEqualTo("jdbc:://db");
    assertThat(values.username()).isEqualTo("username");
    assertThat(values.password()).isEqualTo("password");
    assertThat(values.rootLocale()).isEqualTo("DE");
    assertThat(values.driverClass()).isEqualTo("org.driver");
    assertThat(values.schema()).isEqualTo("schema");
    assertThat(values.tableName()).isEqualTo("table");
    assertThat(values.localeField()).isEqualTo("locale_field");
    assertThat(values.keyField()).isEqualTo("key_field");
    assertThat(values.valueField()).isEqualTo("value_field");
  }

  @TestConfiguration
  @EnableConfigurationProperties(NilsConfigValues.class)
  static class TestConfig {}
}

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
@TestPropertySource("/empty.properties")
public class NilsConfigValuesNothingConfiguredTest {

  @Autowired private NilsConfigValues values;

  @Test
  void checkForDefaultValues() {
    assertThat(values.baseFileName()).isNull();
    assertThat(values.classPrefixResolver()).isNull();
    assertThat(values.dateFormatStyle()).isNull();
    assertThat(values.escapePattern()).isNull();
    assertThat(values.fallbackActive()).isNull();
    assertThat(values.includeTag()).isNull();
    assertThat(values.suppressErrors()).isNull();
    assertThat(values.translationFormatter()).isNull();
    assertThat(values.url()).isNull();
    assertThat(values.username()).isNull();
    assertThat(values.password()).isNull();
    assertThat(values.rootLocale()).isNull();
    assertThat(values.driverClass()).isNull();
    assertThat(values.schema()).isNull();
    assertThat(values.tableName()).isNull();
    assertThat(values.localeField()).isNull();
    assertThat(values.keyField()).isNull();
    assertThat(values.valueField()).isNull();
  }

  @TestConfiguration
  @EnableConfigurationProperties({NilsConfigValues.class})
  static class TestConfig {}
}

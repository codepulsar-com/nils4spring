package com.codepulsar.nils.integration.spring.autoconfigure.values;

import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_BASE_FILE_NAME;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_CLASS_PREFIX_RESOLVER;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_DATE_FORMAT_STYLE;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_DRIVER_CLASS;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_ESCAPE_PATTERN;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_FALLBACK_ACTIVE;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_KEY_FIELD;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_LOCALE_FIELD;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_PASSWORD;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_ROOT_LOCALE;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_SCHEMA;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_SUPPRESS_ERRORS;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_TABLE_NAME;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_TRANSLATION_FORMATTER;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_URL;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_USERNAME;
import static com.codepulsar.nils.integration.spring.autoconfigure.values.NilsConfigValueTestData.NULL_VALUE_FIELD;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.codepulsar.nils.adapter.snakeyaml.SnakeYamlAdapterConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

public class IncludeTagValueUpdaterTest {

  private IncludeTagValueUpdater underTest = new IncludeTagValueUpdater();

  @Test
  void nullValue() {
    // Arrange
    var config = SnakeYamlAdapterConfig.init(getClass());
    var values = NilsConfigValueTestData.empty();

    // Act
    underTest.update(config, values);

    // Assert
    assertThat(config.getIncludeTag()).isEqualTo("_include");
  }

  @Test
  void blankValue() {
    // Arrange
    var config = SnakeYamlAdapterConfig.init(getClass());
    var values =
        new NilsConfigValues(
            NULL_ESCAPE_PATTERN,
            " ",
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

    // Act
    underTest.update(config, values);

    // Assert
    assertThat(config.getIncludeTag()).isEqualTo("_include");
  }

  @Test
  void withValue() {
    // Arrange
    var config = SnakeYamlAdapterConfig.init(getClass());
    var values =
        new NilsConfigValues(
            NULL_ESCAPE_PATTERN,
            "INCLUDE",
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

    // Act
    underTest.update(config, values);

    // Assert
    assertThat(config.getIncludeTag()).isEqualTo("INCLUDE");
  }
}

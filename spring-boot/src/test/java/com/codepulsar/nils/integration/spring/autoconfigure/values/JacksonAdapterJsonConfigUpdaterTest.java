package com.codepulsar.nils.integration.spring.autoconfigure.values;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.format.FormatStyle;

import org.junit.jupiter.api.Test;

import com.codepulsar.nils.adapter.jackson.JacksonAdapterJsonConfig;
import com.codepulsar.nils.core.handler.ClassPrefixResolver;
import com.codepulsar.nils.core.handler.TranslationFormatter;

public class JacksonAdapterJsonConfigUpdaterTest {
  private JacksonAdapterJsonConfigUpdater underTest = new JacksonAdapterJsonConfigUpdater();

  @Test
  void defaultValues() {
    // Arrange
    var config = JacksonAdapterJsonConfig.init(getClass());
    var expected = JacksonAdapterJsonConfig.init(getClass());
    var values = NilsConfigValueTestData.empty();

    // Act
    underTest.update(config, values);

    // Assert
    assertConfig(config, expected);
  }

  @Test
  void nonDefaultValues() {
    // Arrange
    var config = JacksonAdapterJsonConfig.init(getClass());
    var expected =
        JacksonAdapterJsonConfig.init(getClass())
            .baseFileName("test.json")
            .classPrefixResolver(ClassPrefixResolver.FQN_CLASSNAME)
            .dateFormatStyle(FormatStyle.LONG)
            .escapePattern("MISSING - {0}")
            .includeTag("INCLUDE")
            .translationFormatter(TranslationFormatter.STRING_FORMAT)
            .fallbackActive(false)
            .suppressErrors(true);
    var values = NilsConfigValueTestData.nonDefault();

    // Act
    underTest.update(config, values);

    // Assert
    assertConfig(config, expected);
  }

  private void assertConfig(JacksonAdapterJsonConfig actual, JacksonAdapterJsonConfig expected) {
    assertThat(actual.getBaseFileName()).isEqualTo(expected.getBaseFileName());
    assertThat(actual.getClassPrefixResolver()).isEqualTo(expected.getClassPrefixResolver());
    assertThat(actual.getDateFormatStyle()).isEqualTo(expected.getDateFormatStyle());
    assertThat(actual.getEscapePattern()).isEqualTo(expected.getEscapePattern());
    assertThat(actual.getIncludeTag()).isEqualTo(expected.getIncludeTag());
    assertThat(actual.getTranslationFormatter()).isEqualTo(expected.getTranslationFormatter());
    assertThat(actual.isFallbackActive()).isEqualTo(expected.isFallbackActive());
    assertThat(actual.isSuppressErrors()).isEqualTo(expected.isSuppressErrors());
  }
}

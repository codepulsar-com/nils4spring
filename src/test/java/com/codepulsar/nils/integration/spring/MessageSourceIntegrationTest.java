package com.codepulsar.nils.integration.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codepulsar.nils.adapter.rb.ResourceBundleAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.components.NilsFactoryImplTest;
import com.codepulsar.nils.integration.spring.configuration.NilsSpringIntegrationConfiguration;

@ExtendWith(SpringExtension.class)
public class MessageSourceIntegrationTest {

  private Locale current;

  @Autowired private MessageSource underTest;

  @BeforeEach
  void defineDefault() {
    current = Locale.getDefault();
    Locale.setDefault(Locale.ENGLISH);
  }

  @AfterEach
  void resetLocale() {
    Locale.setDefault(current);
  }

  @MethodSource("source_getMessage_codeArgsLocale")
  @ParameterizedTest
  void getMessage_codeArgsLocale(String code, Object[] args, Locale locale, String expected) {
    // Act
    var translation = underTest.getMessage(code, args, locale);

    // Assert
    assertThat(translation).isEqualTo(expected);
  }

  @MethodSource("source_getMessage_codeArgsDefaultMessageLocale")
  @ParameterizedTest
  void getMessage_codeArgsDefaultMessageLocale(
      String code, Object[] args, Locale locale, String expected) {
    // Act
    var translation = underTest.getMessage(code, args, "Default message", locale);

    // Assert
    assertThat(translation).isEqualTo(expected);
  }

  @MethodSource("source_getMessage_messageSourceResolvableLocale")
  @ParameterizedTest
  void getMessage_messageSourceResolvableLocale(
      MessageSourceResolvable msr, Locale locale, String expected) {
    // Act
    var translation = underTest.getMessage(msr, locale);

    // Assert
    assertThat(translation).isEqualTo(expected);
  }

  private static Stream<Arguments> source_getMessage_codeArgsLocale() {
    return Stream.of(
        arguments("test.key", null, null, "Default NLS"),
        arguments(
            "test.keyWithArgs",
            new Object[] {"Test", 24},
            null,
            "Default NLS: Text Test and Number 24"),
        arguments("test.key", null, Locale.GERMAN, "NLS DE"),
        arguments(
            "test.keyWithArgs",
            new Object[] {"Test", 24},
            Locale.GERMAN,
            "NLS DE: Text Test and Number 24"));
  }

  private static Stream<Arguments> source_getMessage_codeArgsDefaultMessageLocale() {
    return Stream.of(
        arguments("test.key", null, null, "Default NLS"),
        arguments("test.keyNotFound", null, null, "Default message"),
        arguments(
            "test.keyWithArgs",
            new Object[] {"Test", 24},
            null,
            "Default NLS: Text Test and Number 24"),
        arguments("test.key", null, Locale.GERMAN, "NLS DE"),
        arguments("test.keyNotFound", null, Locale.GERMAN, "Default message"),
        arguments(
            "test.keyWithArgs",
            new Object[] {"Test", 24},
            Locale.GERMAN,
            "NLS DE: Text Test and Number 24"),
        arguments("test.key", null, Locale.ITALIAN, "Default NLS"));
  }

  private static Stream<Arguments> source_getMessage_messageSourceResolvableLocale() {
    return Stream.of(
        arguments(createMsr("test.key", null), null, "Default NLS"),
        arguments(createMsr("test.keyNotFound", null), null, "Default message"),
        arguments(
            createMsr("test.keyWithArgs", new Object[] {"Test", 24}),
            null,
            "Default NLS: Text Test and Number 24"),
        arguments(createMsr("test.key", null), Locale.GERMAN, "NLS DE"),
        arguments(createMsr("test.keyNotFound", null), Locale.GERMAN, "Default message"),
        arguments(
            createMsr("test.keyWithArgs", new Object[] {"Test", 24}),
            Locale.GERMAN,
            "NLS DE: Text Test and Number 24"),
        arguments(createMsr("test.key", null), Locale.ITALIAN, "Default NLS"),
        arguments(createMsr("test.keyNotFound", "test.key", null), null, "Default NLS"),
        arguments(
            createMsr("test.keyNotFound", "test.keyNotFound2", null), null, "Default message"));
  }

  private static MessageSourceResolvable createMsr(String code, Object[] args) {
    return new DefaultMessageSourceResolvable(new String[] {code}, args, "Default message");
  }

  private static MessageSourceResolvable createMsr(String code1, String code2, Object[] args) {
    return new DefaultMessageSourceResolvable(new String[] {code1, code2}, args, "Default message");
  }

  @TestConfiguration
  @Import(NilsSpringIntegrationConfiguration.class)
  static class Nils4SpringIntegrationTestConfiguration {
    @Bean
    public NilsConfig<?> nilsConfig() {
      return ResourceBundleAdapterConfig.init(NilsFactoryImplTest.class).suppressErrors(true);
    }
  }
}

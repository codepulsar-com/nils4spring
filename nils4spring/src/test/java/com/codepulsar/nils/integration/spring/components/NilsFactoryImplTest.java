package com.codepulsar.nils.integration.spring.components;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codepulsar.nils.adapter.rb.ResourceBundleAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;

public class NilsFactoryImplTest {

  private NilsConfig<?> config;
  private NilsFactoryImpl underTest;
  private Locale current;

  @BeforeEach
  void defineDefault() {
    current = Locale.getDefault();
    Locale.setDefault(Locale.ENGLISH);
  }

  @AfterEach
  void resetLocale() {
    Locale.setDefault(current);
  }

  @BeforeEach
  void init() {
    config = ResourceBundleAdapterConfig.init(this);
    underTest = new NilsFactoryImpl(config);
  }

  @Test
  void nls() {
    // Act
    var result = underTest.nls();

    // Assert
    assertThat(result).isNotNull();

    // Act
    var translation = result.get("test.key");
    // Assert
    assertThat(translation).isEqualTo("Default NLS");
  }

  @Test
  void nls_locale() {
    // Act
    var result = underTest.nls(Locale.GERMAN);

    // Assert
    assertThat(result).isNotNull();

    // Act
    var translation = result.get("test.key");
    // Assert
    assertThat(translation).isEqualTo("NLS DE");
  }

  @Test
  void nls_context1() {
    // Act
    var result = underTest.nlsForContext("test");

    // Assert
    assertThat(result).isNotNull();

    // Act
    var translation = result.get("key");
    // Assert
    assertThat(translation).isEqualTo("Default NLS");
  }

  @Test
  void nls_localeContext1() {
    // Act
    var result = underTest.nlsForContext(Locale.GERMAN, "test");

    // Assert
    assertThat(result).isNotNull();

    // Act
    var translation = result.get("key");
    // Assert
    assertThat(translation).isEqualTo("NLS DE");
  }

  @Test
  void nls_context2() {
    // Act
    var result = underTest.nlsForContext(this.getClass());

    // Assert
    assertThat(result).isNotNull();

    // Act
    var translation = result.get("key");
    // Assert
    assertThat(translation).isEqualTo("Default this class");
  }

  @Test
  void nls_localeContext2() {
    // Act
    var result = underTest.nlsForContext(Locale.GERMAN, this.getClass());

    // Assert
    assertThat(result).isNotNull();

    // Act
    var translation = result.get("key");
    // Assert
    assertThat(translation).isEqualTo("NLS DE this class");
  }
}

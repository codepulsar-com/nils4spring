package com.codepulsar.nils.integration.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codepulsar.nils.adapter.rb.ResourceBundleAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.components.NilsFactoryImplTest;
import com.codepulsar.nils.integration.spring.configuration.NilsSpringIntegrationConfiguration;

@ExtendWith(SpringExtension.class)
public class NilsFactoryIntegrationTest {
  private Locale current;
  @Autowired private NilsFactory underTest;

  @BeforeEach
  void defineDefault() {
    current = Locale.getDefault();
    Locale.setDefault(Locale.ENGLISH);
  }

  @AfterEach
  void resetLocale() {
    Locale.setDefault(current);
  }

  @Test
  void nls() {
    // Act
    var nls = underTest.nls();

    // Assert
    assertThat(nls).isNotNull();

    // Act
    var translation = nls.get("test.key");
    // Assert
    assertThat(translation).isEqualTo("Default NLS");

    // Act
    var translationWithArgs = nls.get("test.keyWithArgs", "Test", 24);
    // Assert
    assertThat(translationWithArgs).isEqualTo("Default NLS: Text Test and Number 24");
  }

  @Test
  void nls_locale() {
    // Act
    var nls = underTest.nls(Locale.GERMAN);

    // Assert
    assertThat(nls).isNotNull();

    // Act
    var translation = nls.get("test.key");
    // Assert
    assertThat(translation).isEqualTo("NLS DE");

    // Act
    var translationWithArgs = nls.get("test.keyWithArgs", "Test", 24);
    // Assert
    assertThat(translationWithArgs).isEqualTo("NLS DE: Text Test and Number 24");
  }

  @TestConfiguration
  @Import(NilsSpringIntegrationConfiguration.class)
  static class Nils4SpringIntegrationTestConfiguration {
    @Bean
    public NilsConfig<?> nilsConfig() {
      return ResourceBundleAdapterConfig.init(NilsFactoryImplTest.class);
    }
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codepulsar.nils.adapter.rb.ResourceBundleAdapterConfig;
import com.codepulsar.nils.adapter.snakeyaml.SnakeYamlAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.NilsFactory;
import com.codepulsar.nils.integration.spring.components.NilsMessageSource;

public class NilsSpringIntegrationAutoConfigurationTest {
  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(NilsSpringIntegrationAutoConfiguration.class));

  @Test
  void defaultServiceBacksOff() {
    this.contextRunner.run(
        (context) -> {
          assertThat(context).hasSingleBean(NilsConfig.class);
          assertThat(context).getBean("nilsConfig").isSameAs(context.getBean(NilsConfig.class));
          assertThat(context.getBean(NilsConfig.class)).isInstanceOf(SnakeYamlAdapterConfig.class);

          SnakeYamlAdapterConfig config =
              (SnakeYamlAdapterConfig) context.getBean(NilsConfig.class);
          assertThat(config.getBaseFileName()).isEqualTo("translation.yml");
          assertThat(context).hasSingleBean(NilsFactory.class);
          assertThat(context).getBean("nilsFactory").isSameAs(context.getBean(NilsFactory.class));

          assertThat(context).hasSingleBean(MessageSource.class);
          assertThat(context)
              .getBean("messageSource")
              .isSameAs(context.getBean(MessageSource.class));
          assertThat(context.getBean(MessageSource.class)).isInstanceOf(NilsMessageSource.class);
        });
  }

  @Test
  void overwriteNilsConfig() {
    this.contextRunner
        .withUserConfiguration(CustomConfiguration.class)
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(NilsConfig.class);
              assertThat(context).getBean("nilsConfig").isSameAs(context.getBean(NilsConfig.class));
              assertThat(context.getBean(NilsConfig.class))
                  .isInstanceOf(ResourceBundleAdapterConfig.class);

              ResourceBundleAdapterConfig config =
                  (ResourceBundleAdapterConfig) context.getBean(NilsConfig.class);
              assertThat(config.getBaseFileName()).isEqualTo("custom.properties");
              assertThat(context).hasSingleBean(NilsFactory.class);
              assertThat(context)
                  .getBean("nilsFactory")
                  .isSameAs(context.getBean(NilsFactory.class));

              assertThat(context).hasSingleBean(MessageSource.class);
              assertThat(context)
                  .getBean("messageSource")
                  .isSameAs(context.getBean(MessageSource.class));
              assertThat(context.getBean(MessageSource.class))
                  .isInstanceOf(NilsMessageSource.class);
            });
  }

  @Configuration(proxyBeanMethods = false)
  static class CustomConfiguration {

    @Bean
    NilsConfig<?> nilsConfig() {
      return ResourceBundleAdapterConfig.init(getClass()).baseFileName("custom.properties");
    }
  }
}

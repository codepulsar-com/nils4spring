package com.codepulsar.nils.integration.spring.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.codepulsar.nils.adapter.snakeyaml.SnakeYamlAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.values.SnakeYamlAdapterConfigUpdater;
import com.codepulsar.nils.integration.spring.configuration.NilsSpringIntegrationConfiguration;

/** Auto-configuration of the Nils4Spring integration. */
@AutoConfiguration
@Import({NilsSpringIntegrationConfiguration.class})
@EnableConfigurationProperties({NilsConfigValues.class})
public class NilsSpringIntegrationAutoConfiguration {

  private NilsConfigValues configValues;

  public NilsSpringIntegrationAutoConfiguration(NilsConfigValues configValues) {
    this.configValues = configValues;
  }

  @Bean("nilsConfig")
  @ConditionalOnMissingBean
  @ConditionalOnClass(SnakeYamlAdapterConfig.class)
  public NilsConfig<?> snakeYamlNilsConfig() {
    var adapterConfig = SnakeYamlAdapterConfig.init(getClass()).baseFileName("translation.yml");
    new SnakeYamlAdapterConfigUpdater().update(adapterConfig, configValues);
    return adapterConfig;
  }
}

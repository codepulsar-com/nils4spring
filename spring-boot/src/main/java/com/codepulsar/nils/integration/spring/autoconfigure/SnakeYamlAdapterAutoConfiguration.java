package com.codepulsar.nils.integration.spring.autoconfigure;

import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.BASE_FILE_NAME_YAML;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.BEAN_NILS_CONFIG;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.DEFAULT_RESOURCE_YAML;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.PROP_BASE_FILE_NAME;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.PROP_PREFIX;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.SPEL_BASEFILENAME_IS_YAML;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.SPEL_BASEFILENAME_IS_YML;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.codepulsar.nils.adapter.snakeyaml.SnakeYamlAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.values.SnakeYamlAdapterConfigUpdater;
import com.codepulsar.nils.integration.spring.configuration.NilsSpringIntegrationConfiguration;

/** Auto-configuration for the NILS SnakeYamlAdapter. */
@AutoConfiguration(before = ResourceBundleAdapterAutoConfiguration.class)
@Import({NilsSpringIntegrationConfiguration.class})
@EnableConfigurationProperties({NilsConfigValues.class})
@ConditionalOnClass(SnakeYamlAdapterConfig.class)
public class SnakeYamlAdapterAutoConfiguration {

  private NilsConfigValues configValues;

  public SnakeYamlAdapterAutoConfiguration(NilsConfigValues configValues) {
    this.configValues = configValues;
  }

  @Bean(BEAN_NILS_CONFIG)
  @ConditionalOnMissingBean
  @ConditionalOnResource(resources = DEFAULT_RESOURCE_YAML)
  public NilsConfig<?> snakeYamlNilsConfigFromResource() {
    var adapterConfig = SnakeYamlAdapterConfig.init(getClass());
    new SnakeYamlAdapterConfigUpdater().update(adapterConfig, configValues);
    adapterConfig.baseFileName(BASE_FILE_NAME_YAML);
    return adapterConfig;
  }

  @Bean(BEAN_NILS_CONFIG)
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = PROP_PREFIX, name = PROP_BASE_FILE_NAME)
  @ConditionalOnExpression(SPEL_BASEFILENAME_IS_YML + " || " + SPEL_BASEFILENAME_IS_YAML)
  public NilsConfig<?> snakeYamlNilsConfigFromProperties() {
    var adapterConfig = SnakeYamlAdapterConfig.init(getClass());
    new SnakeYamlAdapterConfigUpdater().update(adapterConfig, configValues);
    return adapterConfig;
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure;

import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.BASE_FILE_NAME_JSON;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.BASE_FILE_NAME_YAML;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.BEAN_NILS_CONFIG;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.DEFAULT_RESOURCE_JSON;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.DEFAULT_RESOURCE_YAML;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.PROP_BASE_FILE_NAME;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.PROP_PREFIX;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.SPEL_BASEFILENAME_IS_JSON;
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

import com.codepulsar.nils.adapter.jackson.JacksonAdapterConfig;
import com.codepulsar.nils.adapter.jackson.JacksonAdapterJsonConfig;
import com.codepulsar.nils.adapter.jackson.JacksonAdapterYamlConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.values.JacksonAdapterJsonConfigUpdater;
import com.codepulsar.nils.integration.spring.autoconfigure.values.JacksonAdapterYamlConfigUpdater;
import com.codepulsar.nils.integration.spring.configuration.NilsSpringIntegrationConfiguration;

/** Auto-configuration for the NILS JacksonAdapter. */
@AutoConfiguration(before = ResourceBundleAdapterAutoConfiguration.class)
@Import({NilsSpringIntegrationConfiguration.class})
@EnableConfigurationProperties({NilsConfigValues.class})
@ConditionalOnClass({JacksonAdapterYamlConfig.class, JacksonAdapterJsonConfig.class})
public class JacksonAdapterAutoConfiguration {

  private NilsConfigValues configValues;

  public JacksonAdapterAutoConfiguration(NilsConfigValues configValues) {
    this.configValues = configValues;
  }

  @Bean(BEAN_NILS_CONFIG)
  @ConditionalOnMissingBean
  @ConditionalOnResource(resources = DEFAULT_RESOURCE_YAML)
  public NilsConfig<?> jacksonYamlNilsConfigFromResource() {
    var adapterConfig = JacksonAdapterYamlConfig.init(getClass());
    new JacksonAdapterYamlConfigUpdater().update(adapterConfig, configValues);
    adapterConfig.baseFileName(BASE_FILE_NAME_YAML);
    return adapterConfig;
  }

  @Bean(BEAN_NILS_CONFIG)
  @ConditionalOnMissingBean
  @ConditionalOnResource(resources = DEFAULT_RESOURCE_JSON)
  public NilsConfig<?> jacksonJsonNilsConfigFromResource() {
    var adapterConfig = JacksonAdapterJsonConfig.init(getClass());
    new JacksonAdapterJsonConfigUpdater().update(adapterConfig, configValues);
    adapterConfig.baseFileName(BASE_FILE_NAME_JSON);
    return adapterConfig;
  }

  @Bean(BEAN_NILS_CONFIG)
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = PROP_PREFIX, name = PROP_BASE_FILE_NAME)
  @ConditionalOnExpression(
      SPEL_BASEFILENAME_IS_JSON
          + " || "
          + SPEL_BASEFILENAME_IS_YML
          + " || "
          + SPEL_BASEFILENAME_IS_YAML)
  public NilsConfig<?> jacksonNilsConfigFromProperties() {
    var baseFileName = configValues.baseFileName();
    JacksonAdapterConfig<?> adapterConfig = null;
    if (baseFileName.toLowerCase().endsWith(".json")) {
      adapterConfig = JacksonAdapterJsonConfig.init(getClass());
      new JacksonAdapterJsonConfigUpdater()
          .update((JacksonAdapterJsonConfig) adapterConfig, configValues);
    } else {
      adapterConfig = JacksonAdapterYamlConfig.init(getClass());
      new JacksonAdapterYamlConfigUpdater()
          .update((JacksonAdapterYamlConfig) adapterConfig, configValues);
    }
    return adapterConfig;
  }
}

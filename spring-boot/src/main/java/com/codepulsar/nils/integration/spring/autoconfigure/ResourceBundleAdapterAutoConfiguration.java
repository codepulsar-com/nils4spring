package com.codepulsar.nils.integration.spring.autoconfigure;

import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.BASE_FILE_NAME_PROPERTIES;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.BEAN_NILS_CONFIG;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.DEFAULT_RESOURCE_PROPERTIES;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.PROP_BASE_FILE_NAME;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.PROP_PREFIX;
import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.SPEL_BASEFILENAME_IS_PROPERTIES;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.codepulsar.nils.adapter.rb.ResourceBundleAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.values.ResourceBundleAdapterConfigUpdater;
import com.codepulsar.nils.integration.spring.configuration.NilsSpringIntegrationConfiguration;

/** Auto-configuration for the NILS ResourceBundleAdapter. */
@AutoConfiguration
@Import({NilsSpringIntegrationConfiguration.class})
@EnableConfigurationProperties({NilsConfigValues.class})
@ConditionalOnClass(ResourceBundleAdapterConfig.class)
public class ResourceBundleAdapterAutoConfiguration {

  private NilsConfigValues configValues;

  public ResourceBundleAdapterAutoConfiguration(NilsConfigValues configValues) {
    this.configValues = configValues;
  }

  @Bean(BEAN_NILS_CONFIG)
  @ConditionalOnMissingBean
  @ConditionalOnClass(ResourceBundleAdapterConfig.class)
  @ConditionalOnResource(resources = DEFAULT_RESOURCE_PROPERTIES)
  public NilsConfig<?> resourceBundleNilsConfigFromResource() {
    var adapterConfig = ResourceBundleAdapterConfig.init(getClass());
    new ResourceBundleAdapterConfigUpdater().update(adapterConfig, configValues);
    adapterConfig.baseFileName(BASE_FILE_NAME_PROPERTIES);
    return adapterConfig;
  }

  @Bean(BEAN_NILS_CONFIG)
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = PROP_PREFIX, name = PROP_BASE_FILE_NAME)
  @ConditionalOnExpression(SPEL_BASEFILENAME_IS_PROPERTIES)
  public NilsConfig<?> resourceBundleNilsConfigFromProperties() {
    var adapterConfig = ResourceBundleAdapterConfig.init(getClass());
    new ResourceBundleAdapterConfigUpdater().update(adapterConfig, configValues);
    return adapterConfig;
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure;

import static com.codepulsar.nils.integration.spring.autoconfigure.Consts.BEAN_NILS_CONFIG;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.codepulsar.nils.adapter.jdbc.JdbcAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.values.JdbcAdapterConfigUpdater;
import com.codepulsar.nils.integration.spring.configuration.NilsSpringIntegrationConfiguration;

/** Auto-configuration for the NILS JdbcAdapter. */
@AutoConfiguration(before = ResourceBundleAdapterAutoConfiguration.class)
@Import({NilsSpringIntegrationConfiguration.class})
@EnableConfigurationProperties({NilsConfigValues.class})
@ConditionalOnClass(JdbcAdapterConfig.class)
public class JdbcAdapterAutoConfiguration {

  private NilsConfigValues configValues;

  public JdbcAdapterAutoConfiguration(NilsConfigValues configValues) {
    this.configValues = configValues;
  }

  @Bean(BEAN_NILS_CONFIG)
  @ConditionalOnMissingBean
  public NilsConfig<?> jdbcNilsConfig() {
    var adapterConfig =
        JdbcAdapterConfig.init(
            configValues.url(), configValues.username(), configValues.password());
    new JdbcAdapterConfigUpdater().update(adapterConfig, configValues);
    return adapterConfig;
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.adapter.jackson.JacksonAdapterYamlConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for the {@code JacksonAdapterYamlConfig}. */
public class JacksonAdapterYamlConfigUpdater
    implements ConfigValueUpdater<JacksonAdapterYamlConfig> {
  @Override
  public void update(JacksonAdapterYamlConfig config, NilsConfigValues configValues) {
    new BaseFileNameValueUpdater().update(config, configValues);
    new BaseNilsConfigUpdater<JacksonAdapterYamlConfig>().update(config, configValues);
    new FallbackActiveValueUpdater().update(config, configValues);
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.adapter.jackson.JacksonAdapterJsonConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for the {@code JacksonAdapterJsonConfig}. */
public class JacksonAdapterJsonConfigUpdater
    implements ConfigValueUpdater<JacksonAdapterJsonConfig> {
  @Override
  public void update(JacksonAdapterJsonConfig config, NilsConfigValues configValues) {
    new BaseFileNameValueUpdater().update(config, configValues);
    new BaseNilsConfigUpdater<JacksonAdapterJsonConfig>().update(config, configValues);
    new FallbackActiveValueUpdater().update(config, configValues);
  }
}

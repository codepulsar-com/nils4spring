package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.adapter.gson.GsonAdapterConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for the {@code GsonAdapterConfig}. */
public class GsonAdapterConfigUpdater implements ConfigValueUpdater<GsonAdapterConfig> {
  @Override
  public void update(GsonAdapterConfig config, NilsConfigValues configValues) {
    new BaseFileNameValueUpdater().update(config, configValues);
    new BaseNilsConfigUpdater<GsonAdapterConfig>().update(config, configValues);
    new FallbackActiveValueUpdater().update(config, configValues);
  }
}

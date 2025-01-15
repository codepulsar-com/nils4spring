package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.adapter.snakeyaml.SnakeYamlAdapterConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for the {@code SnakeYamlAdapterConfig}. */
public class SnakeYamlAdapterConfigUpdater implements ConfigValueUpdater<SnakeYamlAdapterConfig> {

  @Override
  public void update(SnakeYamlAdapterConfig config, NilsConfigValues configValues) {
    new BaseFileNameValueUpdater().update(config, configValues);
    new BaseNilsConfigUpdater<SnakeYamlAdapterConfig>().update(config, configValues);
    new FallbackActiveValueUpdater().update(config, configValues);
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.adapter.rb.ResourceBundleAdapterConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for the {@code ResourceBundleAdapterConfig}. */
public class ResourceBundleAdapterConfigUpdater
    implements ConfigValueUpdater<ResourceBundleAdapterConfig> {
  @Override
  public void update(ResourceBundleAdapterConfig config, NilsConfigValues configValues) {
    new BaseFileNameValueUpdater().update(config, configValues);
    new BaseNilsConfigUpdater<ResourceBundleAdapterConfig>().update(config, configValues);
    new FallbackActiveValueUpdater().update(config, configValues);
  }
}

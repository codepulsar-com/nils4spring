package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.core.adapter.config.BaseLocalizedResourceNilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for 'baserFileName'. */
public class BaseFileNameValueUpdater
    implements ConfigValueUpdater<BaseLocalizedResourceNilsConfig<?>> {

  @Override
  public void update(BaseLocalizedResourceNilsConfig<?> config, NilsConfigValues configValues) {
    var value = configValues.baseFileName();
    if (value == null || value.isBlank()) {
      return;
    }
    config.baseFileName(value);
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.core.adapter.config.BaseLocalizedResourceNilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for 'fallbackActive'. */
public class FallbackActiveValueUpdater
    implements ConfigValueUpdater<BaseLocalizedResourceNilsConfig<?>> {

  @Override
  public void update(BaseLocalizedResourceNilsConfig<?> config, NilsConfigValues configValues) {
    var value = configValues.fallbackActive();
    if (value == null) {
      return;
    }

    config.fallbackActive(value);
  }
}

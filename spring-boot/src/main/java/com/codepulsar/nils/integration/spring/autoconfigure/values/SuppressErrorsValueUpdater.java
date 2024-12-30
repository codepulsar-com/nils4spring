package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.core.adapter.config.BaseNilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for 'suppressErrors'. */
public class SuppressErrorsValueUpdater implements ConfigValueUpdater<BaseNilsConfig<?>> {

  @Override
  public void update(BaseNilsConfig<?> config, NilsConfigValues configValues) {
    var value = configValues.suppressErrors();
    if (value == null) {
      return;
    }

    config.suppressErrors(value);
  }
}

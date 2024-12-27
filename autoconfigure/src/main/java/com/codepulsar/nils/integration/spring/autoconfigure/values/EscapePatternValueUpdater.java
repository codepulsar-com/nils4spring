package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.core.adapter.config.BaseNilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for 'escapePattern'. */
public class EscapePatternValueUpdater implements ConfigValueUpdater<BaseNilsConfig<?>> {

  @Override
  public void update(BaseNilsConfig<?> config, NilsConfigValues configValues) {
    var value = configValues.escapePattern();
    if (value == null || value.isBlank()) {
      return;
    }

    config.escapePattern(value);
  }
}

package com.codepulsar.nils.integration.spring.autoconfigure.values;

import java.time.format.FormatStyle;

import com.codepulsar.nils.core.adapter.config.BaseNilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;
import com.codepulsar.nils.integration.spring.autoconfigure.error.ErrorTypes;

/** {@link ConfigValueUpdater} implementation for 'dateFormatStyle'. */
public class DateFormatStyleValueUpdater implements ConfigValueUpdater<BaseNilsConfig<?>> {

  @Override
  public void update(BaseNilsConfig<?> config, NilsConfigValues configValues) {
    var value = configValues.dateFormatStyle();
    if (value == null || value.isBlank()) {
      return;
    }
    try {
      FormatStyle style = FormatStyle.valueOf(value.toUpperCase());
      config.dateFormatStyle(style);
    } catch (IllegalArgumentException e) {
      throw ErrorTypes.INVALID_DATE_FORMAT_STYLE.asException().args(value).cause(e).go();
    }
  }
}

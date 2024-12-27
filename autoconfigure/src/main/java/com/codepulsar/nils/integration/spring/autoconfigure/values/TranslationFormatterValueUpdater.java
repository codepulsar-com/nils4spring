package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.core.adapter.config.BaseNilsConfig;
import com.codepulsar.nils.core.handler.TranslationFormatter;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;
import com.codepulsar.nils.integration.spring.autoconfigure.error.ErrorTypes;

/** {@link ConfigValueUpdater} implementation for 'translationFormatter'. */
public class TranslationFormatterValueUpdater implements ConfigValueUpdater<BaseNilsConfig<?>> {

  @Override
  public void update(BaseNilsConfig<?> config, NilsConfigValues configValues) {
    var value = configValues.translationFormatter();
    if (value == null || value.isBlank()) {
      return;
    }
    TranslationFormatter formatter = null;
    if ("MESSAGE".equalsIgnoreCase(value)) {
      formatter = TranslationFormatter.MESSAGE_FORMAT;
    } else if ("STRING".equalsIgnoreCase(value)) {
      formatter = TranslationFormatter.STRING_FORMAT;
    } else {
      try {
        formatter = (TranslationFormatter) Class.forName(value).getConstructor().newInstance();
      } catch (ReflectiveOperationException | IllegalArgumentException | SecurityException e) {
        throw ErrorTypes.INVALID_TRANSLATION_FORMATTER.asException().args(value).cause(e).go();
      }
    }

    config.translationFormatter(formatter);
  }
}

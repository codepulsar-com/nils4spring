package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.core.adapter.config.BaseNilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

public class BaseNilsConfigUpdater<C extends BaseNilsConfig<?>> implements ConfigValueUpdater<C> {

  @Override
  public void update(C config, NilsConfigValues configValues) {
    new ClassPrefixResolverValueUpdater().update(config, configValues);
    new DateFormatStyleValueUpdater().update(config, configValues);
    new EscapePatternValueUpdater().update(config, configValues);
    new IncludeTagValueUpdater().update(config, configValues);
    new TranslationFormatterValueUpdater().update(config, configValues);
    new SuppressErrorsValueUpdater().update(config, configValues);
  }
}

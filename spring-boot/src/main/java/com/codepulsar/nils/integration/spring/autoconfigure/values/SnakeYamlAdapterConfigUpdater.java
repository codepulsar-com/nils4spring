package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.adapter.snakeyaml.SnakeYamlAdapterConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for the {@code SnakeYamlAdapterConfig}. */
public class SnakeYamlAdapterConfigUpdater {

  public void update(SnakeYamlAdapterConfig config, NilsConfigValues configValues) {
    new BaseFileNameValueUpdater().update(config, configValues);
    new ClassPrefixResolverValueUpdater().update(config, configValues);
    new DateFormatStyleValueUpdater().update(config, configValues);
    new EscapePatternValueUpdater().update(config, configValues);
    new IncludeTagValueUpdater().update(config, configValues);
    new TranslationFormatterValueUpdater().update(config, configValues);
    new FallbackActiveValueUpdater().update(config, configValues);
    new SuppressErrorsValueUpdater().update(config, configValues);
  }
}

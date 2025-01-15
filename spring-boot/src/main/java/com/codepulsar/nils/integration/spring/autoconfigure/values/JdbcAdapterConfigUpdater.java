package com.codepulsar.nils.integration.spring.autoconfigure.values;

import java.util.Locale;

import com.codepulsar.nils.adapter.jdbc.JdbcAdapterConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/** {@link ConfigValueUpdater} implementation for the {@code JdbcAdapterConfig}. */
public class JdbcAdapterConfigUpdater implements ConfigValueUpdater<JdbcAdapterConfig> {
  @Override
  public void update(JdbcAdapterConfig config, NilsConfigValues configValues) {
    new BaseNilsConfigUpdater<JdbcAdapterConfig>().update(config, configValues);

    updateConnection(config, configValues);
    updateTableDefinition(config, configValues);
    updateFallbackActive(config, configValues);
    updateRootLocale(config, configValues);
    updateCacheTimeout(config, configValues);
  }

  private void updateConnection(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.driverClass();
    if (value == null || value.isBlank()) {
      return;
    }
    config.driverClass(value);
  }

  private void updateTableDefinition(JdbcAdapterConfig config, NilsConfigValues configValues) {
    updateSchema(config, configValues);
    updateTableName(config, configValues);
    updateLocaleField(config, configValues);
    updateKeyField(config, configValues);
    updateValueField(config, configValues);
  }

  private void updateSchema(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.schema();
    if (value == null || value.isBlank()) {
      return;
    }
    config.schema(value);
  }

  private void updateTableName(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.tableName();
    if (value == null || value.isBlank()) {
      return;
    }
    config.tableName(value);
  }

  private void updateLocaleField(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.localeField();
    if (value == null || value.isBlank()) {
      return;
    }
    config.localeField(value);
  }

  private void updateKeyField(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.keyField();
    if (value == null || value.isBlank()) {
      return;
    }
    config.keyField(value);
  }

  private void updateValueField(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.valueField();
    if (value == null || value.isBlank()) {
      return;
    }
    config.valueField(value);
  }

  private void updateFallbackActive(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.fallbackActive();
    if (value == null) {
      return;
    }
    config.fallbackActive(value);
  }

  private void updateRootLocale(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.rootLocale();
    if (value == null) {
      return;
    }

    Locale locale = Locale.forLanguageTag(value);
    config.rootLocale(locale);
  }

  private void updateCacheTimeout(JdbcAdapterConfig config, NilsConfigValues configValues) {
    var value = configValues.cacheTimeout();
    if (value == null) {
      return;
    }

    config.cacheTimeout(value);
  }
}

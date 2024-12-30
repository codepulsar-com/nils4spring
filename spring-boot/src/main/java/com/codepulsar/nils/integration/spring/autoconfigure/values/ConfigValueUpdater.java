package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;

/**
 * A {@link ConfigValueUpdater} updates a specific value in a configuration depending on the values
 * in {@link NilsConfigValues}.
 *
 * @param <C>
 */
@FunctionalInterface
public interface ConfigValueUpdater<C extends NilsConfig<?>> {

  void update(C config, NilsConfigValues configValues);
}

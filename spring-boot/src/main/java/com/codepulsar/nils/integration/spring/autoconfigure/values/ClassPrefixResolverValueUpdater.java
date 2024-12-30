package com.codepulsar.nils.integration.spring.autoconfigure.values;

import java.lang.reflect.InvocationTargetException;

import com.codepulsar.nils.core.adapter.config.BaseNilsConfig;
import com.codepulsar.nils.core.handler.ClassPrefixResolver;
import com.codepulsar.nils.integration.spring.autoconfigure.NilsConfigValues;
import com.codepulsar.nils.integration.spring.autoconfigure.error.ErrorTypes;

/** {@link ConfigValueUpdater} implementation for 'classPrefixResolver'. */
public class ClassPrefixResolverValueUpdater implements ConfigValueUpdater<BaseNilsConfig<?>> {

  @Override
  public void update(BaseNilsConfig<?> config, NilsConfigValues configValues) {
    var value = configValues.classPrefixResolver();
    if (value == null || value.isBlank()) {
      return;
    }
    ClassPrefixResolver resolver = null;
    if ("SIMPLE".equalsIgnoreCase(value)) {
      resolver = ClassPrefixResolver.SIMPLE_CLASSNAME;
    } else if ("FQN".equalsIgnoreCase(value)) {
      resolver = ClassPrefixResolver.FQN_CLASSNAME;
    } else {
      try {
        resolver = (ClassPrefixResolver) Class.forName(value).getConstructor().newInstance();
      } catch (InstantiationException
          | IllegalAccessException
          | IllegalArgumentException
          | InvocationTargetException
          | NoSuchMethodException
          | SecurityException
          | ClassNotFoundException e) {
        throw ErrorTypes.INVALID_CLASS_PREFIX_RESOLVER.asException().args(value).cause(e).go();
      }
    }

    config.classPrefixResolver(resolver);
  }
}

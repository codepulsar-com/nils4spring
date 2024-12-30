package com.codepulsar.nils.integration.spring.autoconfigure.values;

import com.codepulsar.nils.core.handler.ClassPrefixResolver;

public class DummyClassPrefixResolver implements ClassPrefixResolver {

  @Override
  public String resolve(Class<?> clazz) {
    return null;
  }
}

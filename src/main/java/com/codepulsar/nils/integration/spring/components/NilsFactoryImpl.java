package com.codepulsar.nils.integration.spring.components;

import java.util.Locale;

import org.springframework.stereotype.Component;

import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.NLS;
import com.codepulsar.nils.integration.spring.NilsFactory;

/** Default implementation for the {@link NilsFactory} bean. */
@Component(value = "nilsFactory")
public class NilsFactoryImpl implements NilsFactory {

  private com.codepulsar.nils.api.NilsFactory innerFactory;

  /**
   * Create a new instance.
   *
   * @param nilsConfig A {@code NilsConfig} object
   */
  public NilsFactoryImpl(NilsConfig<?> nilsConfig) {
    this.innerFactory = com.codepulsar.nils.api.NilsFactory.init(nilsConfig);
  }

  @Override
  public NLS nls() {
    return new NLSImpl(innerFactory.nls());
  }

  @Override
  public NLS nls(Locale locale) {
    return new NLSImpl(innerFactory.nls(locale));
  }

  @Override
  public NLS nlsForContext(String context) {
    return new NLSImpl(innerFactory.nlsWithContext(context));
  }

  @Override
  public NLS nlsForContext(Locale locale, String context) {
    return new NLSImpl(innerFactory.nlsWithContext(locale, context));
  }

  @Override
  public NLS nlsForContext(Class<?> context) {
    return new NLSImpl(innerFactory.nlsWithContext(context));
  }

  @Override
  public NLS nlsForContext(Locale locale, Class<?> context) {
    return new NLSImpl(innerFactory.nlsWithContext(locale, context));
  }

  @Override
  public void reset() {
    innerFactory.reset();
  }
}

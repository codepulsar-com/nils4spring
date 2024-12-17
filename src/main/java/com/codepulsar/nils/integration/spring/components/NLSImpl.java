package com.codepulsar.nils.integration.spring.components;

import java.util.Locale;

import com.codepulsar.nils.api.Formats;
import com.codepulsar.nils.integration.spring.NLS;

/**
 * Implementation of the {@link NLS} interface.
 *
 * <p>Acts as a proxy to the NILS {@code NLS} object.
 */
public class NLSImpl implements NLS {

  private com.codepulsar.nils.api.NLS innerNls;

  NLSImpl(com.codepulsar.nils.api.NLS nls) {
    this.innerNls = nls;
  }

  @Override
  public String get(String key) {
    return innerNls.get(key);
  }

  @Override
  public String get(String key, Object... args) {
    return innerNls.get(key, args);
  }

  @Override
  public String get(Class<?> key, String subKey) {
    return innerNls.get(key, subKey);
  }

  @Override
  public String get(Class<?> key, String subKey, Object... args) {
    return innerNls.get(key, subKey, args);
  }

  @Override
  public Locale getLocale() {
    return innerNls.getLocale();
  }

  @Override
  public Formats getFormats() {
    return innerNls.getFormats();
  }

  @Override
  public NLS context(String context) {
    return new NLSImpl(innerNls.context(context));
  }

  @Override
  public NLS context(Class<?> context) {
    return new NLSImpl(innerNls.context(context));
  }
}

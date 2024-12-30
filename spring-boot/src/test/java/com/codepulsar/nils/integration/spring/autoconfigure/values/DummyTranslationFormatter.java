package com.codepulsar.nils.integration.spring.autoconfigure.values;

import java.util.Locale;

import com.codepulsar.nils.core.handler.TranslationFormatter;

public class DummyTranslationFormatter implements TranslationFormatter {

  @Override
  public String format(Locale locale, String value, Object... args) {
    return null;
  }
}

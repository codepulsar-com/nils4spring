package com.codepulsar.nils.integration.spring.components;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.integration.spring.NilsFactory;

/** Implementation of the "messageSource" bean from Spring. */
@Component(value = "messageSource")
public class NilsMessageSource implements MessageSource {

  private NilsConfig<?> nilsConfig;
  private NilsFactory nilsFactory;

  /**
   * Creates a new instance.
   *
   * @param nilsFactory A {@link NilsFactory} object
   * @param nilsConfig A {@code NilsConfig} object
   */
  public NilsMessageSource(NilsFactory nilsFactory, NilsConfig<?> nilsConfig) {
    this.nilsFactory = nilsFactory;
    this.nilsConfig = nilsConfig;
  }

  @Override
  public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
    if (code == null || code.isBlank()) {
      return defaultMessage;
    }
    var msg = getMessage(code, args, locale);
    if (isNotTranslated(msg, code)) {
      return defaultMessage;
    }
    return msg;
  }

  @Override
  public String getMessage(String code, Object[] args, Locale locale)
      throws NoSuchMessageException {
    if (code == null || code.isBlank()) {
      throw new NoSuchMessageException(code, locale);
    }
    var nls = locale == null ? nilsFactory.nls() : nilsFactory.nls(locale);
    if (args == null || args.length == 0) {
      return nls.get(code);
    }
    return nls.get(code, args);
  }

  @Override
  public String getMessage(MessageSourceResolvable resolvable, Locale locale)
      throws NoSuchMessageException {

    var codes = resolvable.getCodes();
    if (codes == null || codes.length == 0) {
      return resolvable.getDefaultMessage();
    }

    var args = resolvable.getArguments();
    for (String code : codes) {
      var msg = getMessage(code, args, locale);
      if (!isNotTranslated(msg, code)) {
        return msg;
      }
    }

    return resolvable.getDefaultMessage();
  }

  private boolean isNotTranslated(String msg, String key) {
    var escMsg = MessageFormat.format(nilsConfig.getEscapePattern(), key);
    return escMsg.equals(msg);
  }
}

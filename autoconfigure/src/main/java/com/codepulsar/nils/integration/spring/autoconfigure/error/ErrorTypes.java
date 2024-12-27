package com.codepulsar.nils.integration.spring.autoconfigure.error;

import com.codepulsar.nils.api.error.ErrorType;

/** Error Types of the Nils4Spring integration. */
public class ErrorTypes {
  /** The value for the 'classPrefixResolver' is invalid. */
  public static final ErrorType INVALID_CLASS_PREFIX_RESOLVER =
      new ErrorType(
          "NILS4SPRING-001",
          "Invalid value '%s' as ClassPrefixResolver. The value must be 'simple', 'fqn' or a fullqualified class name.");

  /** The value for the 'dateFormatStyle' is invalid. */
  public static final ErrorType INVALID_DATE_FORMAT_STYLE =
      new ErrorType(
          "NILS4SPRING-002",
          "Invalid value '%s' for date format style. The value must one of java.time.format.FormatStyle.");

  /** The value for the 'translationFormatter' is invalid. */
  public static final ErrorType INVALID_TRANSLATION_FORMATTER =
      new ErrorType(
          "NILS4SPRING-003",
          "Invalid value '%s' as TranslationFormatter. The value must be 'message', 'string' or a fullqualified class name.");
}

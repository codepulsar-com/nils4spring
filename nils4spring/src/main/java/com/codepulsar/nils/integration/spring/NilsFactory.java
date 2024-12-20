package com.codepulsar.nils.integration.spring;

import java.util.Locale;

/**
 * A facade for the {@code NilsFactory} interface from NILS.
 *
 * <p>For more information see {@code https://docs.codepulsar.com/nils/latest/index.html}.
 */
public interface NilsFactory {
  /**
   * Get the NLS object for the default <code>Locale</code>.
   *
   * @return The NLS object for the default <code>Locale</code>.
   */
  NLS nls();

  /**
   * Get the NLS object for a specific <code>Locale</code>.
   *
   * @param locale The <code>Locale</code>.
   * @return The NLS object for the <code>Locale</code>.
   */
  NLS nls(Locale locale);

  /**
   * Get the NLS object for the default <code>Locale</code> and a specific context.
   *
   * <p>Only the keys beneath the context will be examined.
   *
   * @param context The context key.
   * @return The NLS object for the default <code>Locale</code>.
   */
  NLS nlsForContext(String context);

  /**
   * Get the NLS object for a specific <code>Locale</code> and a specific context.
   *
   * <p>Only the keys beneath the context will be examined.
   *
   * @param locale The <code>Locale</code>.
   * @param context The context key.
   * @return The NLS object for the <code>Locale</code>.
   */
  NLS nlsForContext(Locale locale, String context);

  /**
   * Get the NLS object for the default <code>Locale</code> and a specific context based on a <code>
   * Class</code>.
   *
   * <p>Only the keys beneath the context will be examined.
   *
   * @param context The <code>Class</code> for the context key.
   * @return The NLS object for the default <code>Locale</code>.
   */
  NLS nlsForContext(Class<?> context);

  /**
   * Get the NLS object for a language tag and a specific context based on a <code>
   * Class</code>.
   *
   * <p>Only the keys beneath the context will be examined.
   *
   * @param locale The <code>Locale</code>.
   * @param context The <code>Class</code> for the context key.
   * @return The NLS object.
   */
  NLS nlsForContext(Locale locale, Class<?> context);

  /** Reset the {@code NilsFactory} and all its cached elements. */
  void reset();
}

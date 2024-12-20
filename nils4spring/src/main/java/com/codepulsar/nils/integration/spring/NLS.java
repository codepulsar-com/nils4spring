package com.codepulsar.nils.integration.spring;

import java.util.Locale;

import com.codepulsar.nils.api.Formats;

/**
 * A facade for the {@code NLS} interface from NILS.
 *
 * <p>For more information see {@code https://docs.codepulsar.com/nils/latest/index.html}.
 */
public interface NLS {
  /**
   * Get the translation for the requested key.
   *
   * @param key The key for the translation
   * @return The translation
   */
  String get(String key);

  /**
   * Get the translation for the requested key with arguments that should be replaced in the
   * translation.
   *
   * @param key The key for the translation
   * @param args Arguments for the translation
   * @return The translation
   */
  String get(String key, Object... args);

  /**
   * Get the translation for the requested key.
   *
   * <p>The <code>Class</code> is used a base key. The subKey as a further key. Both, the key and
   * subKey, will be concatenated like 'MyClass.attrbute'.
   *
   * @param key A <code>Class</code> as base key for the translation
   * @param subKey The sub key, like an attribute of the class
   * @return The translation
   */
  String get(Class<?> key, String subKey);

  /**
   * Get the translation for the requested key with arguments that should be replaced in the
   * translation.
   *
   * <p>The <code>Class</code> is used a base key. The subKey as a further key. Both, the key and
   * subKey, will be concatenated like 'MyClass.attrbute'.
   *
   * @param key A <code>Class</code> as base key for the translation
   * @param subKey The sub key, like an attribute of the class
   * @param args Arguments for the translation
   * @return The translation
   */
  String get(Class<?> key, String subKey, Object... args);

  /**
   * Get the <code>Locale</code> the NLS is responsible for.
   *
   * @return A <code>Locale</code> object.
   */
  Locale getLocale();

  /**
   * Get the {@link Formats} for the <code>Locale</code> of this <strong>NLS</strong>.
   *
   * @return A {@link Formats} object.
   */
  Formats getFormats();

  /**
   * Get a {@link NLS} object for a specific context.
   *
   * <p>Only the keys beneath the context will be examined.
   *
   * @param context The context key.
   * @return A {@link NLS} object.
   */
  NLS context(String context);

  /**
   * Get a {@link NLS} object for a specific context based on a <code>Class</code>.
   *
   * <p>Only the keys beneath the context will be examined.
   *
   * @param context The <code>Class</code> for the context key.
   * @return A {@link NLS} object.
   */
  NLS context(Class<?> context);
}

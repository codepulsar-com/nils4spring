package com.codepulsar.nils.integration.spring.autoconfigure;

/** Constants used in the configuration. */
class Consts {

  /** Bean name {@value}. */
  static final String BEAN_NILS_CONFIG = "nilsConfig";

  /** Property name for "base-file-name". */
  static final String PROP_BASE_FILE_NAME = "base-file-name";

  /** Property prefix. */
  static final String PROP_PREFIX = "nils";

  /** SpEL checking if the property 'base-file-name' ends with {@code .json}. */
  static final String SPEL_BASEFILENAME_IS_JSON =
      "'${nils.base-file-name}'.toLowerCase().endsWith('.json')";

  /** SpEL checking if the property 'base-file-name' ends with {@code .yml}. */
  static final String SPEL_BASEFILENAME_IS_YML =
      "'${nils.base-file-name}'.toLowerCase().endsWith('.yml')";

  /** SpEL checking if the property 'base-file-name' ends with {@code .yaml}. */
  static final String SPEL_BASEFILENAME_IS_YAML =
      "'${nils.base-file-name}'.toLowerCase().endsWith('.yaml')";

  /** SpEL checking if the property 'base-file-name' ends with {@code .properties}. */
  static final String SPEL_BASEFILENAME_IS_PROPERTIES =
      "'${nils.base-file-name}'.toLowerCase().endsWith('.properties')";

  /** Base file name {@value}. */
  static final String BASE_FILE_NAME_JSON = "translation.json";

  /** Base file name {@value}. */
  static final String BASE_FILE_NAME_YAML = "translation.yml";

  /** Base file name {@value}. */
  static final String BASE_FILE_NAME_PROPERTIES = "translation.properties";

  /** Default json resource location. */
  static final String DEFAULT_RESOURCE_JSON = "classpath:" + BASE_FILE_NAME_JSON;

  /** Default yaml resource location. */
  static final String DEFAULT_RESOURCE_YAML = "classpath:" + BASE_FILE_NAME_YAML;

  /** Default properties resource location. */
  static final String DEFAULT_RESOURCE_PROPERTIES = "classpath:" + BASE_FILE_NAME_PROPERTIES;

  private Consts() {}
}

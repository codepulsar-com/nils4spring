package com.codepulsar.nils.integration.spring.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.assertj.AssertableApplicationContext;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codepulsar.nils.adapter.gson.GsonAdapterConfig;
import com.codepulsar.nils.adapter.jackson.JacksonAdapterJsonConfig;
import com.codepulsar.nils.adapter.jackson.JacksonAdapterYamlConfig;
import com.codepulsar.nils.adapter.jdbc.JdbcAdapterConfig;
import com.codepulsar.nils.adapter.rb.ResourceBundleAdapterConfig;
import com.codepulsar.nils.adapter.snakeyaml.SnakeYamlAdapterConfig;
import com.codepulsar.nils.api.NilsConfig;
import com.codepulsar.nils.core.adapter.config.BaseLocalizedResourceNilsConfig;
import com.codepulsar.nils.integration.spring.NilsFactory;
import com.codepulsar.nils.integration.spring.components.NilsMessageSource;

public class NilsSpringIntegrationAutoConfigurationTest {

  private static final String NONE = " ";
  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(
              AutoConfigurations.of(
                  SnakeYamlAdapterAutoConfiguration.class,
                  JacksonAdapterAutoConfiguration.class,
                  GsonAdapterAutoConfiguration.class,
                  JdbcAdapterAutoConfiguration.class,
                  ResourceBundleAdapterAutoConfiguration.class));

  @Test
  void snakeYamlAndResource() {
    this.contextRunner
        .withClassLoader(prepareClassLoader("translation.yml", SnakeYamlAdapterConfig.class))
        .run(
            (context) -> {
              assertFileBaseAdapter(context, SnakeYamlAdapterConfig.class, "translation.yml");
              assertDefaultBeans(context);
            });
  }

  @Test
  void snakeYamlAndPropertiesYml() {
    this.contextRunner
        .withClassLoader(prepareClassLoader(NONE, SnakeYamlAdapterConfig.class))
        .withPropertyValues("nils.baseFileName=i18n.yml")
        .run(
            (context) -> {
              assertFileBaseAdapter(context, SnakeYamlAdapterConfig.class, "i18n.yml");
              assertDefaultBeans(context);
            });
  }

  @Test
  void snakeYamlAndPropertiesYaml() {
    this.contextRunner
        .withClassLoader(prepareClassLoader(NONE, SnakeYamlAdapterConfig.class))
        .withPropertyValues("nils.baseFileName=i18n.yaml")
        .run(
            (context) -> {
              assertFileBaseAdapter(context, SnakeYamlAdapterConfig.class, "i18n.yaml");
              assertDefaultBeans(context);
            });
  }

  @Test
  void jacksonYamlAndResource() {
    this.contextRunner
        .withClassLoader(
            prepareClassLoader(
                "translation.yml", JacksonAdapterJsonConfig.class, JacksonAdapterYamlConfig.class))
        .run(
            (context) -> {
              assertFileBaseAdapter(context, JacksonAdapterYamlConfig.class, "translation.yml");
              assertDefaultBeans(context);
            });
  }

  @Test
  void jacksonYamlAndPropertiesYml() {
    this.contextRunner
        .withClassLoader(
            prepareClassLoader(
                NONE, JacksonAdapterJsonConfig.class, JacksonAdapterYamlConfig.class))
        .withPropertyValues("nils.baseFileName=i18n.yml")
        .run(
            (context) -> {
              assertFileBaseAdapter(context, JacksonAdapterYamlConfig.class, "i18n.yml");
              assertDefaultBeans(context);
            });
  }

  @Test
  void jacksonYamlAndPropertiesYaml() {
    this.contextRunner
        .withClassLoader(
            prepareClassLoader(
                NONE, JacksonAdapterJsonConfig.class, JacksonAdapterYamlConfig.class))
        .withPropertyValues("nils.baseFileName=i18n.yaml")
        .run(
            (context) -> {
              assertFileBaseAdapter(context, JacksonAdapterYamlConfig.class, "i18n.yaml");
              assertDefaultBeans(context);
            });
  }

  @Test
  void jacksonJsonAndResource() {
    this.contextRunner
        .withClassLoader(
            prepareClassLoader(
                "translation.json", JacksonAdapterJsonConfig.class, JacksonAdapterYamlConfig.class))
        .run(
            (context) -> {
              assertFileBaseAdapter(context, JacksonAdapterJsonConfig.class, "translation.json");
              assertDefaultBeans(context);
            });
  }

  @Test
  void jacksonJsonAndProperties() {
    this.contextRunner
        .withClassLoader(
            prepareClassLoader(
                NONE, JacksonAdapterJsonConfig.class, JacksonAdapterYamlConfig.class))
        .withPropertyValues("nils.baseFileName=i18n.json")
        .run(
            (context) -> {
              assertFileBaseAdapter(context, JacksonAdapterJsonConfig.class, "i18n.json");
              assertDefaultBeans(context);
            });
  }

  @Test
  void gsonAndResource() {
    this.contextRunner
        .withClassLoader(prepareClassLoader("translation.json", GsonAdapterConfig.class))
        .run(
            (context) -> {
              assertFileBaseAdapter(context, GsonAdapterConfig.class, "translation.json");
              assertDefaultBeans(context);
            });
  }

  @Test
  void gsonAndProperties() {
    this.contextRunner
        .withClassLoader(prepareClassLoader(NONE, GsonAdapterConfig.class))
        .withPropertyValues("nils.baseFileName=i18n.json")
        .run(
            (context) -> {
              assertFileBaseAdapter(context, GsonAdapterConfig.class, "i18n.json");
              assertDefaultBeans(context);
            });
  }

  @Test
  void jdbcAndProperties() {
    this.contextRunner
        .withClassLoader(prepareClassLoader(NONE, JdbcAdapterConfig.class))
        .withPropertyValues("nils.url=jdbc://xyz", "nils.username=user", "nils.password=pwd")
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(NilsConfig.class);
              assertThat(context).getBean("nilsConfig").isSameAs(context.getBean(NilsConfig.class));
              assertThat(context.getBean(NilsConfig.class)).isInstanceOf(JdbcAdapterConfig.class);

              JdbcAdapterConfig config = (JdbcAdapterConfig) context.getBean(NilsConfig.class);
              assertThat(config.getUrl()).isEqualTo("jdbc://xyz");
              assertDefaultBeans(context);
            });
  }

  @Test
  void resouceBundleAndResource() {
    this.contextRunner
        .withClassLoader(
            prepareClassLoader("translation.properties", ResourceBundleAdapterConfig.class))
        .run(
            (context) -> {
              assertFileBaseAdapter(
                  context, ResourceBundleAdapterConfig.class, "translation.properties");
              assertDefaultBeans(context);
            });
  }

  @Test
  void resouceBundleAndProperties() {
    this.contextRunner
        .withClassLoader(prepareClassLoader(NONE, ResourceBundleAdapterConfig.class))
        .withPropertyValues("nils.baseFileName=i18n.properties")
        .run(
            (context) -> {
              assertFileBaseAdapter(context, ResourceBundleAdapterConfig.class, "i18n.properties");
              assertDefaultBeans(context);
            });
  }

  @Test
  void overwriteNilsConfig() {
    this.contextRunner
        .withUserConfiguration(CustomConfiguration.class)
        .run(
            (context) -> {
              assertFileBaseAdapter(
                  context, ResourceBundleAdapterConfig.class, "custom.properties");
              assertDefaultBeans(context);
            });
  }

  @Configuration(proxyBeanMethods = false)
  static class CustomConfiguration {

    @Bean
    NilsConfig<?> nilsConfig() {
      return ResourceBundleAdapterConfig.init(getClass()).baseFileName("custom.properties");
    }
  }

  private void assertFileBaseAdapter(
      AssertableApplicationContext context,
      Class<? extends BaseLocalizedResourceNilsConfig<?>> configClass,
      String expectedBaseFileName) {

    assertThat(context).hasSingleBean(NilsConfig.class);
    assertThat(context).getBean("nilsConfig").isSameAs(context.getBean(NilsConfig.class));
    assertThat(context.getBean(NilsConfig.class)).isInstanceOf(configClass);

    var config = (BaseLocalizedResourceNilsConfig<?>) context.getBean(NilsConfig.class);
    assertThat(config.getBaseFileName()).isEqualTo(expectedBaseFileName);
  }

  private void assertDefaultBeans(AssertableApplicationContext context) {
    assertThat(context).hasSingleBean(NilsFactory.class);
    assertThat(context).getBean("nilsFactory").isSameAs(context.getBean(NilsFactory.class));

    assertThat(context).hasSingleBean(MessageSource.class);
    assertThat(context).getBean("messageSource").isSameAs(context.getBean(MessageSource.class));
    assertThat(context.getBean(MessageSource.class)).isInstanceOf(NilsMessageSource.class);
  }

  @SuppressWarnings("unchecked")
  private static FilteredClassLoader prepareClassLoader(String resource, Class<?>... clazzes) {
    var adapterClasses =
        List.of(
            SnakeYamlAdapterConfig.class,
            JacksonAdapterYamlConfig.class,
            JacksonAdapterJsonConfig.class,
            GsonAdapterConfig.class,
            JdbcAdapterConfig.class);
    var resoucenames = List.of("translation.yml", "translation.json", "translation.properties");
    var classList = List.of(clazzes);
    var exclude =
        adapterClasses.stream()
            .filter(c -> !classList.contains(c))
            .map(c -> not(c))
            .collect(Collectors.toList());
    var ex2 =
        resoucenames.stream()
            .filter(s -> !s.equals(resource))
            .map(s -> not(s))
            .collect(Collectors.toList());
    exclude.addAll(ex2);

    return new FilteredClassLoader(exclude.toArray(new Predicate[] {}));
  }

  private static Predicate<String> not(Class<?> clazz) {
    return o -> o.equals(clazz.getName());
  }

  private static Predicate<String> not(String resource) {
    return o -> o.equals(resource);
  }
}

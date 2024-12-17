package com.codepulsar.nils.integration.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.codepulsar.nils.integration.spring.components.NilsFactoryImpl;
import com.codepulsar.nils.integration.spring.components.NilsMessageSource;

/**
 * This class configure the "messageSource" bean and NILS specific beans.
 *
 * <p><strong>Note:</strong>Your application must provide a bean or component with the name
 * "nilsConfig" of type {@code NilsConfig}.
 */
@Configuration
@Import(value = {NilsMessageSource.class, NilsFactoryImpl.class})
public class NilsSpringIntegrationConfiguration {}

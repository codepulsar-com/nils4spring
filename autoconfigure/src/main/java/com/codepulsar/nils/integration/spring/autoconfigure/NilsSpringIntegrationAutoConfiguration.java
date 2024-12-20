package com.codepulsar.nils.integration.spring.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

import com.codepulsar.nils.integration.spring.configuration.NilsSpringIntegrationConfiguration;

@AutoConfiguration
@Import(NilsSpringIntegrationConfiguration.class)
public class NilsSpringIntegrationAutoConfiguration {}

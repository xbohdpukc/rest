package com.pstech.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ws.config.annotation.EnableWs;

@Configuration
@EnableAutoConfiguration
@EnableWs
@PropertySource("classpath:application.properties")
public class AppConfig {

    private final Environment environment;

    private final ApplicationContext applicationContext;

    @Autowired
    public AppConfig(Environment environment, ApplicationContext applicationContext) {
        this.environment = environment;
        this.applicationContext = applicationContext;
    }

    @Bean
    Utils setUtils(Utils utils) {
        return utils;
    }


}

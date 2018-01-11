package com.pstech.rest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ws.config.annotation.EnableWs;

@Configuration
@EnableAutoConfiguration
@EnableWs
@PropertySource("classpath:application.properties")
public class AppConfig {

}

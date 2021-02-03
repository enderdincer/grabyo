package com.poker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Beans are annotation based thus no beans defined here.
 *
 * @author enderdincer
 */
@Configuration
@ComponentScan("com.poker.*")
public class AppConfig {
}

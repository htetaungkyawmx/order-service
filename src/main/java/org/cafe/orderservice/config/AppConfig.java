package org.cafe.orderservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.cafe")
@EnableJpaRepositories(basePackages = "org.cafe")
@ComponentScan(basePackages = "org.cafe")
public class AppConfig {

}

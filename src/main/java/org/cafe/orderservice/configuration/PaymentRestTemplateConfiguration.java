package org.cafe.orderservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class PaymentRestTemplateConfiguration {
    @Value("${payment-service.readTimeout}")
    private int readTimeout;
    @Value("${payment-service.connectTimeout}")
    private int connectTimeout;

    @Bean(name = "paymentRestTemplate")
    public RestTemplate paymentRestTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(Duration.ofMinutes(readTimeout))
                .setConnectTimeout(Duration.ofMinutes(connectTimeout))
                .build();
    }
}

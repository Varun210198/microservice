package com.example.orderservice.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // Enables Eureka-based service name resolution
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

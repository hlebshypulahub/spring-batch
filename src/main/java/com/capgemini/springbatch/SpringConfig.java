package com.capgemini.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBatchProcessing
public class SpringConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

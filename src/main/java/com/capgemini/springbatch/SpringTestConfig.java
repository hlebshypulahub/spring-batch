package com.capgemini.springbatch;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Profile("test")
@Configuration
public class SpringTestConfig {

    @Bean(name = "restTemplateMock")
    public RestTemplate restTemplate() {
        return Mockito.mock(RestTemplate.class);
    }
}

package com.capgemini.springbatch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CustomPartWriter implements ItemWriter<Part> {

    private final String apiUrl;
    private final RestTemplate restTemplate;

    CustomPartWriter(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public void write(List<? extends Part> parts) {

        restTemplate.postForObject(
                apiUrl,
                parts,
                Part[].class);
    }
}

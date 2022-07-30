package com.capgemini.springbatch;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class SpringBatchApplicationTests {

    @Autowired
    @InjectMocks
    private BatchService batchService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testBatchJob() {
        boolean isSuccess = batchService.doBatchJob();

        Mockito.when(restTemplate.postForObject(
                Mockito.eq(SpringBatchConfig.API_URL),
                Mockito.any(Part[].class),
                Mockito.eq(Part[].class)
        )).thenReturn(Mockito.any(Part[].class));

        assertTrue(isSuccess);
    }
}

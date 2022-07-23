package com.capgemini.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    private final String[] FIELD_NAMES = new String[]{"id", "name", "producerCode"};

    private final JobBuilderFactory jobs;
    private final StepBuilderFactory steps;
    private final RestTemplate restTemplate;
    private String apiUrl = "http://localhost:8090/api/v1/parts";

    public SpringBatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps, RestTemplate restTemplate) {
        this.jobs = jobs;
        this.steps = steps;
        this.restTemplate = restTemplate;
    }

    @Bean
    public ItemReader<PartTransaction> itemReader() throws UnexpectedInputException, ParseException {

        return new FlatFileItemReaderBuilder<PartTransaction>()
                .name("PartItemReader")
                .resource(new ClassPathResource("springbatch-parts.out"))
                .linesToSkip(1)
                .delimited().delimiter(";")
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(PartTransaction.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<PartTransaction, Part> itemProcessor() {
        return new CustomPartTransactionProcessor();
    }

    @Bean
    public ItemWriter<Part> itemWriter() {
        return new CustomPartWriter(apiUrl, restTemplate);
    }

    @Bean
    protected Step step1(ItemReader<PartTransaction> itemReader,
                         ItemProcessor<PartTransaction, Part> itemProcessor,
                         ItemWriter<Part> itemWriter) {
        return steps.get("step1").<PartTransaction, Part>chunk(100)
                    .reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
    }

    @Bean(name = "firstBatchJob")
    public Job job(@Qualifier("step1") Step step1) {
        return jobs.get("firstBatchJob").start(step1).build();
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}

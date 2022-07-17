package com.capgemini.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBatchProcessing
public class SpringConfig {

//    @Value("org/springframework/batch/core/schema-drop-sqlite.sql")
//    private Resource dropRepositoryTables;
//
//    @Value("org/springframework/batch/core/schema-sqlite.sql")
//    private Resource dataRepositorySchema;
//
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.sqlite.JDBC");
//        dataSource.setUrl("jdbc:sqlite:repository.sqlite");
//        return dataSource;
//    }
//
//    @Bean
//    public DataSourceInitializer dataSourceInitializer(DataSource dataSource)
//            throws MalformedURLException {
//        ResourceDatabasePopulator databasePopulator =
//                new ResourceDatabasePopulator();
//
//        databasePopulator.addScript(dropRepositoryTables);
//        databasePopulator.addScript(dataRepositorySchema);
//        databasePopulator.setIgnoreFailedDrops(true);
//
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//        initializer.setDatabasePopulator(databasePopulator);
//
//        return initializer;
//    }
//
//    private JobRepository getJobRepository() throws Exception {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(dataSource());
//        factory.setTransactionManager(getTransactionManager());
//        factory.afterPropertiesSet();
//        return (JobRepository) factory.getObject();
//    }
//
//    private PlatformTransactionManager getTransactionManager() {
//        return new ResourcelessTransactionManager();
//    }
//
//    public JobLauncher getJobLauncher() throws Exception {
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(getJobRepository());
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }
}
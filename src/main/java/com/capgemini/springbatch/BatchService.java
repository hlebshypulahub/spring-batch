package com.capgemini.springbatch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BatchService {

    private final JobLauncher jobLauncher;
    private final Job job;

    public BatchService(JobLauncher jobLauncher, @Qualifier("firstBatchJob") Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @PostConstruct
    public void invokePostConstruct() {
        doBatchJob();
    }

    public boolean doBatchJob() {
        try {
            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Job Status : " + execution.getStatus());
            return execution.getStatus() == BatchStatus.COMPLETED;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Job failed");
            return false;
        }
    }
}

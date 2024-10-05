package com.batch.spring_boot_batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.context.ApplicationContext;

public interface JobService {
    Job getJob();

    JobParameters getJobParameters();

    void runJob(ApplicationContext context) throws JobExecutionException;
}

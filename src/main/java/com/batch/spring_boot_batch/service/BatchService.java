package com.batch.spring_boot_batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BatchService implements JobService {

    @Autowired
    @Qualifier("pers")
    Job job;

    @Autowired
    @Qualifier("pers")
    JobParameters params;


    @Override
    public Job getJob() {
        return job;
    }

    @Override
    public JobParameters getJobParameters() {
        return params;
    }

    @Override
    public void runJob(ApplicationContext context) throws JobExecutionException {
        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        jobLauncher.run(getJob(), getJobParameters());
    }
}

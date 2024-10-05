package com.batch.spring_boot_batch.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListeners implements JobExecutionListener {
    static final Logger logger = LogManager.getLogger(JobListeners.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("insdie beforeJob()");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("insdie afterJob()");
    }
}

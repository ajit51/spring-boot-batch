package com.batch.spring_boot_batch.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class PersionStepListener implements StepExecutionListener {
    static final Logger logger = LogManager.getLogger(PersionStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("inside beforeStep() StepExecutionListener");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("inside afterStep() StepExecutionListener");
        long readCount = stepExecution.getReadCount();
        long writeCount = stepExecution.getWriteCount();
        logger.info("read count: " + readCount);
        logger.info("write count: " + readCount);

        return null;
    }
}

package com.batch.spring_boot_batch.listener;

import com.batch.spring_boot_batch.service.DBOperartionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PersionStepListener implements StepExecutionListener {
    static final Logger logger = LogManager.getLogger(PersionStepListener.class);


    @Autowired
    private DBOperartionService dbOperartionService;

    @Autowired
    private DataSource dataSource;

    String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("inside beforeStep() StepExecutionListener");

        if (null != getFileName()) {
            try {
                dbOperartionService.truncateTable(getFileName(), dataSource);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
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

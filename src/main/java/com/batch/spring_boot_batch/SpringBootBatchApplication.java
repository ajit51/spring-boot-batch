package com.batch.spring_boot_batch;

import com.batch.spring_boot_batch.config.BatchConfigProps;
import com.batch.spring_boot_batch.enums.CommonEnum;
import com.batch.spring_boot_batch.service.BatchService;
import com.batch.spring_boot_batch.service.JobService;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringBootBatchApplication {

    public static void main(String[] args) throws JobExecutionException {
        ApplicationContext context = SpringApplication.run(SpringBootBatchApplication.class, args);

        BatchConfigProps beanBatchConfigProps = context.getBean(BatchConfigProps.class);
        beanBatchConfigProps.addConfigProp(CommonEnum.EXECUTION_FLOW_DECIDER.getKey(), args[0]);

        JobService jobService = context.getBean(BatchService.class);
        jobService.runJob(context);
    }

}

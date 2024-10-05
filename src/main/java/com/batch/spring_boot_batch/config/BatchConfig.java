package com.batch.spring_boot_batch.config;

import com.batch.spring_boot_batch.enums.CommonEnum;
import com.batch.spring_boot_batch.listener.JobListeners;
import com.batch.spring_boot_batch.listener.PersionStepListener;
import com.batch.spring_boot_batch.mapper.PersonMapper;
import com.batch.spring_boot_batch.model.PersonModel;
import com.batch.spring_boot_batch.tasklet.InitialTasklet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    static final Logger logger = LogManager.getLogger(BatchConfig.class);
    static final String CLASS_ID = BatchConfig.class.getName();

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    InitialTasklet initialTasklet;

    @Bean
    @Qualifier("pers")
    public Job personJob() {
        return new JobBuilder("Person Job", jobRepository)
                .start(initialStep())
                .next(personStep())
                .build();
    }

    @Bean
    @Qualifier("pers")
    public JobParameters jobParameters() {
        return new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .addString("jobFUllName", "Person Job")
                .toJobParameters();
    }

    @Bean
    @Qualifier("pers")
    public Step initialStep() {
        return new StepBuilder("doNothingTasklet", jobRepository)
                .tasklet(initialTasklet, platformTransactionManager)
                .build();
    }

    @Bean
    @Qualifier("personListener")
    @JobScope
    public JobListeners jobListeners() {
        JobListeners jobListeners = new JobListeners();
        return jobListeners;
    }

    @Bean
    @Qualifier("dmtknz")
    @StepScope
    public DelimitedLineTokenizer delimitedLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
        return lineTokenizer;
    }

    @Bean
    @Qualifier("pers")
    public Step personStep() {
        return new StepBuilder("personStep", jobRepository)
                .listener(persionStepListener())
                .chunk(100, platformTransactionManager)
                .reader(personModelFlatFileItemReader())
                .writer(jdbcBatchItemWriterPerson())
                .build();

    }

    @Bean
    @Qualifier("pers")
    @StepScope
    public FlatFileItemReader<PersonModel> personModelFlatFileItemReader() {
        PersonMapper personMapper = new PersonMapper();
        DefaultLineMapper lineMapper = new DefaultLineMapper();
        lineMapper.setFieldSetMapper(personMapper);
        lineMapper.setLineTokenizer(delimitedLineTokenizer());

        FlatFileItemReader<PersonModel> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper);
        logger.info("inputFilePath "+  env.getProperty("test.inputFilePath"));
        logger.info("Test_FILE_NAME "+  CommonEnum.Test_FILE_NAME.getKey());
        logger.info("Test_FILE_NAME path "+  env.getProperty("test.inputFilePath") + CommonEnum.Test_FILE_NAME.getKey());
        reader.setResource(new FileSystemResource(
                env.getProperty("test.inputFilePath") + CommonEnum.Test_FILE_NAME.getKey()
        ));

        return reader;
    }

    @Bean
    @Qualifier("pers")
    @StepScope
    public JdbcBatchItemWriter jdbcBatchItemWriterPerson() {
        JdbcBatchItemWriter<PersonModel> writer = new JdbcBatchItemWriter<>();
        try {
            writer.setDataSource(dataSource);
            writer.setSql(CommonEnum.INSERT_PERSON_QUERY.getKey());
            writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        } catch (Exception e) {
            logger.error(CLASS_ID, e);
        }
        return writer;
    }

    @Bean
    @Qualifier("pers")
    @StepScope
    public PersionStepListener persionStepListener() {
        PersionStepListener stepListener = new PersionStepListener();
        return stepListener;
    }

}

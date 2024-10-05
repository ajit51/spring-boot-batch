package com.batch.spring_boot_batch.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    static final Logger logger = LogManager.getLogger(DataSourceConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("spring.datasource.url"));
        config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        config.setUsername(env.getProperty("spring.datasource.password"));
        config.setPassword(env.getProperty("spring.datasource.password"));

        logger.info("database connection initialization started...");
        updateConfig(config);
        logger.info("Hikari datafield set finished");
        HikariDataSource hikariDataSource = new HikariDataSource(config);
        logger.info("database connection finished");
        return hikariDataSource;
    }

    private void updateConfig(HikariConfig config) {
        // Hikari specific settings
        config.setMaximumPoolSize(Integer.parseInt(env.getProperty("spring.datasource.hikari.maximum-pool-size")));
        config.setMinimumIdle(Integer.parseInt(env.getProperty("spring.datasource.hikari.minimum-idle")));
        config.setConnectionTimeout(Long.parseLong(env.getProperty("spring.datasource.hikari.connection-timeout")));
        config.setIdleTimeout(Long.parseLong(env.getProperty("spring.datasource.hikari.idle-timeout")));
        config.setMaxLifetime(Long.parseLong(env.getProperty("spring.datasource.hikari.max-lifetime")));
    }
}

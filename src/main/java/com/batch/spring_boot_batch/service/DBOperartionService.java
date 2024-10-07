package com.batch.spring_boot_batch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DBOperartionService {
    private static final Logger logger = LoggerFactory.getLogger(DBOperartionService.class);

    public void truncateTable(String tableName, DataSource dataSource) throws SQLException {
        logger.info("inside truncateTable()");
        String truncateTableQuery = "truncate table " + tableName;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(truncateTableQuery);
        logger.info(tableName + " truncated successfully");
    }

    public Long rowCount(String tableName, DataSource dataSource) throws SQLException {
        logger.info("inside rowCount()");
        String sqlStatement = "select count(*) from " + tableName;
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        Long rowCount = jdbcTemplate.queryForObject(sqlStatement, Long.class);
        logger.info(tableName + " Row count: " + rowCount);
        return rowCount;
    }
}

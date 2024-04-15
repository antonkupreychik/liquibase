package com.kupreychik.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public abstract class AbstractContainerDatabaseTest {



    protected SessionFactory getSessionFactory(PostgreSQLContainer database) {
        return new DbConfigTest(database).getSessionFactory();
    }

    protected String getSqlFromFile(String filename) throws IOException {
        return FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
    }

    protected  static ResultSet performQuery(GenericContainer<?> container, String sql) throws SQLException {
        DataSource ds = getDataSource(container);
        Statement statement = ds.getConnection().createStatement(TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();

        if (resultSet == null) {
            return null;
        }

        resultSet.next();
        return resultSet;
    }

    protected static DataSource getDataSource(GenericContainer<?> container) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(((JdbcDatabaseContainer) container).getJdbcUrl());
        hikariConfig.setUsername(((JdbcDatabaseContainer) container).getUsername());
        hikariConfig.setPassword(((JdbcDatabaseContainer) container).getPassword());
        hikariConfig.setDriverClassName(((JdbcDatabaseContainer) container).getDriverClassName());
        return new HikariDataSource(hikariConfig);
    }
}
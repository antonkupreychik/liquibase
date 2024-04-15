package com.kupreychik.config;

import com.kupreychik.model.entity.Tag;
import com.kupreychik.model.entity.Task;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

@Slf4j
@Getter
public class DBConfig {
    private SessionFactory sessionFactory;

    public DBConfig() {
        configureSessionFactory();
    }

    private void configureSessionFactory() {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(Tag.class)
                .setProperties(setProperties())
                .buildSessionFactory();
    }

    private Properties setProperties() {
        var properties = new Properties() {{
            setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            setProperty(Environment.DRIVER, "org.postgresql.Driver");
            setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/todo");
            setProperty(Environment.USER, "anton");
            setProperty(Environment.PASS, "anton");
            setProperty(Environment.SHOW_SQL, "false");
            setProperty(Environment.FORMAT_SQL, "false");
            setProperty(Environment.HBM2DDL_AUTO, "none");
        }};

        return properties;
    }
}
package com.kupreychik.config;

import com.kupreychik.model.entity.Tag;
import com.kupreychik.model.entity.Task;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Properties;

public class DbConfigTest {
    private SessionFactory sessionFactory;

    public DbConfigTest(PostgreSQLContainer database) {
        configureSessionFactory(database);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private void configureSessionFactory(PostgreSQLContainer container) {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(Tag.class)
                .setProperties(setProperties(container))
                .buildSessionFactory();
    }

    private Properties setProperties(PostgreSQLContainer container) {
        var properties = new Properties() {{
            setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            setProperty(Environment.DRIVER, container.getDriverClassName());
            setProperty(Environment.URL, "jdbc:postgresql://localhost:" + container.getFirstMappedPort().toString() + "/todo");
            setProperty(Environment.USER, container.getUsername());
            setProperty(Environment.PASS, container.getPassword());
            setProperty(Environment.SHOW_SQL, "true");
            setProperty(Environment.FORMAT_SQL, "true");
            setProperty(Environment.HBM2DDL_AUTO, "create");
        }};

        return properties;
    }
}

package com.kupreychik.repository.abstracts;

import org.hibernate.SessionFactory;

public abstract class AbstractRepository {

    private final SessionFactory sessionFactory;

    protected AbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

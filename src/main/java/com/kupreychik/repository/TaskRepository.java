package com.kupreychik.repository;

import com.kupreychik.model.entity.Task;
import com.kupreychik.repository.abstracts.AbstractRepository;
import com.kupreychik.repository.abstracts.Repository;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class TaskRepository extends AbstractRepository implements Repository<Task, Long> {

    private final SessionFactory sessionFactory;

    protected TaskRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Task> findById(Long serializable) {
        return null;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public void save(Task o) {

    }

    @Override
    public void delete(Task o) {

    }

    public Task findTaskByTitle(String title) {
        return null;
    }
}
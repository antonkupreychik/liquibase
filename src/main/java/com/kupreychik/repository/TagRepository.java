package com.kupreychik.repository;

import com.kupreychik.model.entity.Tag;
import com.kupreychik.repository.abstracts.AbstractRepository;
import com.kupreychik.repository.abstracts.Repository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@Slf4j
public class TagRepository extends AbstractRepository implements Repository<Tag, Long> {

    private final SessionFactory sessionFactory;

    public TagRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Tag> findById(Long aLong) {
        try (Session session = sessionFactory.openSession()) {
            Tag tag = session.get(Tag.class, aLong);
            if (tag != null) {
                return Optional.of(tag);
            }
            return Optional.empty();
        }
    }

    @Override
    public List<Tag> findAll() {
        return List.of();
    }

    @Override
    public void save(Tag tag) {
        log.info("Saving tag: {}", tag);
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(tag);
            session.getTransaction().commit();
            log.info("Tag saved: {}", tag);
        } catch (Exception e) {
            log.error("Error saving tag: {}", tag, e);
        }
    }

    @Override
    public void delete(Tag tag) {

    }
}

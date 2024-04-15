package com.kupreychik.service.abstracts;

import com.kupreychik.mapper.Mapper;
import com.kupreychik.repository.abstracts.Repository;

public abstract class AbstractService {

    private final Mapper mapper;
    private final Repository repository;

    public AbstractService(Mapper mapper, Repository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
}

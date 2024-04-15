package com.kupreychik.mapper;

//E - Entity
//D - DTO
//C - Command
public interface Mapper<E, D, C> {
    E mapToEntity(D entity);

    D mapToDTO(E entity);

    C mapToCommand(E entity);
}

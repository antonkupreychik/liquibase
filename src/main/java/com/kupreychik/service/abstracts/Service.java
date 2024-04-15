package com.kupreychik.service.abstracts;

import com.kupreychik.model.command.Command;
import com.kupreychik.model.dto.DTO;

//E - Entity
//C - Comamnd classes
//D - DTO classes
public interface Service<C extends Command, D extends DTO> {

    D save(C c);
}

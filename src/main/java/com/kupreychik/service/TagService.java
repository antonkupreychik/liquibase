package com.kupreychik.service;

import com.kupreychik.model.command.TagCommand;
import com.kupreychik.model.dto.TagDTO;
import com.kupreychik.service.abstracts.Service;

import java.util.List;

public interface TagService extends Service<TagCommand, TagDTO> {

    List<TagDTO> findAllTags();

}

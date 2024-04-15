package com.kupreychik.service.impl;

import com.kupreychik.mapper.TagMapper;
import com.kupreychik.model.command.TagCommand;
import com.kupreychik.model.dto.TagDTO;
import com.kupreychik.repository.TagRepository;
import com.kupreychik.service.TagService;
import com.kupreychik.service.abstracts.AbstractService;

import java.util.List;

public class TagServiceImpl extends AbstractService implements TagService {

    private final TagMapper tagMapper;
    private final TagRepository tagRepository;

    public TagServiceImpl(TagMapper mapper, TagRepository repository) {
        super(mapper, repository);
        this.tagMapper = mapper;
        this.tagRepository = repository;

    }

    @Override
    public List<TagDTO> findAllTags() {
        return List.of();
    }

    @Override
    public TagDTO save(TagCommand tagCommand) {
        var tag = tagMapper.mapToEntity(tagCommand);
        tagRepository.save(tag);
        return tagMapper.mapToDTO(tag);
    }
}

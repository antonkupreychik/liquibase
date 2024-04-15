package com.kupreychik.mapper;

import com.kupreychik.model.command.TagCommand;
import com.kupreychik.model.dto.TagDTO;
import com.kupreychik.model.entity.Tag;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface TagMapper extends Mapper<Tag, TagDTO, TagCommand> {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Override
    Tag mapToEntity(TagDTO entity);

    @Override
    TagDTO mapToDTO(Tag entity);

    @Override
    TagCommand mapToCommand(Tag entity);

    Tag mapToEntity(TagCommand entity);
}

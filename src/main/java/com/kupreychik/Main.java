package com.kupreychik;

import com.kupreychik.config.DBConfig;
import com.kupreychik.mapper.TagMapper;
import com.kupreychik.model.command.TagCommand;
import com.kupreychik.repository.TagRepository;
import com.kupreychik.service.TagService;
import com.kupreychik.service.impl.TagServiceImpl;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        TagMapper tagMapper = TagMapper.INSTANCE;
        DBConfig dbConfig = new DBConfig();
        SessionFactory sessionFactory = dbConfig.getSessionFactory();
        TagRepository tagRepository = new TagRepository(sessionFactory);

        TagService tagService = new TagServiceImpl(tagMapper, tagRepository);

        TagCommand tagCommand = TagCommand.builder()
                .title("new tag")
                .color("new color")
                .build();

        tagService.save(tagCommand);
    }
}
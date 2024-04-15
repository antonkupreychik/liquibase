package com.kupreychik.init;


import com.kupreychik.config.DBConfig;
import com.kupreychik.mapper.TagMapper;
import com.kupreychik.repository.TagRepository;
import com.kupreychik.service.TagService;
import com.kupreychik.service.impl.TagServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@Slf4j
@WebListener
public class AppContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        log.debug("'Servlet context' initialization begins...");

        DBConfig dbConfig = new DBConfig();
        SessionFactory sessionFactory = dbConfig.getSessionFactory();

        TagRepository tagRepository = new TagRepository(sessionFactory);
        TagMapper tagMapper = TagMapper.INSTANCE;
        TagService tagService = new TagServiceImpl(tagMapper, tagRepository);

        var servletContext = sce.getServletContext();
        servletContext.setAttribute("tagService", tagService);

        log.debug("'Servlet context' initialized successfully");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

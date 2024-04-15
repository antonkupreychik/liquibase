package com.kupreychik.repository;

import com.kupreychik.config.AbstractContainerDatabaseTest;
import com.kupreychik.model.command.TagCommand;
import com.kupreychik.model.dto.TagDTO;
import com.kupreychik.model.entity.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@Testcontainers
class TagRepositoryTest extends AbstractContainerDatabaseTest {

    @Container
    private PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
            .withDatabaseName("todo")
            .withUsername("anton")
            .withPassword("anton");

    private TagRepository tagRepository;

    @Test
    void should_not_find_tag_by_id_when_not_found() {
        postgresqlContainer.start();
        tagRepository = new TagRepository(getSessionFactory(postgresqlContainer));

        var result = tagRepository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void should_save_tag() {
        postgresqlContainer.start();
        tagRepository = new TagRepository(getSessionFactory(postgresqlContainer));

        Tag tag = new Tag();
        tag.setColor("asdasd");
        tag.setTitle("asadsad");

        tagRepository.save(tag);

        String result = tagRepository.findById(1L).get().getColor();

        assertThat(result).isEqualTo("asdasd");
    }


    @Test
    void test() {
        assertThat(postgresqlContainer.isRunning()).isTrue();
    }

    @Test
    void testSimple() throws SQLException {
        postgresqlContainer.start();

        ResultSet resultSet = performQuery(postgresqlContainer, "SELECT 1");
        int resultSetInt = resultSet.getInt(1);
        assertThat(resultSetInt).as("A basic SELECT query succeeds").isEqualTo(1);
    }

    @Test
    void should_create_tag_and_find_it() throws SQLException, IOException {
        prepareTagsTable();

        String insertToTags = "INSERT INTO tags (title, color, is_default) VALUES ('new tag', 'new color', null)";
        performQuery(postgresqlContainer, insertToTags);

        String selectFromTags = "SELECT * FROM tags";
        ResultSet resultSet = performQuery(postgresqlContainer, selectFromTags);

        int resultSetInt = resultSet.getInt("id");
        assertThat(resultSetInt).as("A basic SELECT query succeeds").isEqualTo(1);
    }

    @Test
    void should_find_tag_by_id() throws SQLException, IOException {
        prepareTagsTable();

        String insertToTags = "INSERT INTO tags (title, color, is_default) VALUES ('tag 1', 'new color', null)";
        performQuery(postgresqlContainer, insertToTags);

        String insertToTags2 = "INSERT INTO tags (title, color, is_default) VALUES ('tag 2', 'new color', null)";
        performQuery(postgresqlContainer, insertToTags2);

        String selectById = "SELECT * FROM tags WHERE id = 2";
        ResultSet resultSet = performQuery(postgresqlContainer, selectById);
        int resultSetInt = resultSet.getInt("id");

        assertThat(resultSetInt).as("A basic SELECT query succeeds").isEqualTo(2);
    }

    @Test
    void should_not_find_tag_by_id() throws SQLException, IOException {
        prepareTagsTable();

        String insertToTags = "INSERT INTO tags (title, color, is_default) VALUES ('tag 1', 'new color', null)";
        performQuery(postgresqlContainer, insertToTags);

        String insertToTags2 = "INSERT INTO tags (title, color, is_default) VALUES ('tag 2', 'new color', null)";
        performQuery(postgresqlContainer, insertToTags2);

        String selectById = "SELECT * FROM tags WHERE id = 3";
        ResultSet resultSet = performQuery(postgresqlContainer, selectById);

        assertFalse(resultSet.first());
    }

    private void clearTagsTable() throws SQLException {
        String sql = "DELETE FROM tags";
        performQuery(postgresqlContainer, sql);
    }


    private void prepareTagsTable() throws IOException, SQLException {
        String queryToCreateTable = getSqlFromFile("/Users/antonkupreychik/IdeaProjects/liquibase/src/main/resources/db/changelog/create_tasks.sql");
        performQuery(postgresqlContainer, queryToCreateTable);
    }

    @Test
    void findById() {
        TagCommand tagCommand = new TagCommand();
        TagDTO tagDTO = new TagDTO();


    }
}
package com.epam.esm.model.dao.impl;

import com.epam.esm.model.dao.TagDao;
import com.epam.esm.model.dao.entity.Tag;
import com.epam.esm.model.dao.exception.DaoException;
import com.epam.esm.model.dao.mapper.TagMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class TagDaoImpl implements TagDao {

    private final static String READ_TAG_SQL = "SELECT id,name FROM tag WHERE id=?";
    private final static String CREATE_TAG_SQL = "INSERT INTO tag (name) VALUES (?)";
    private final static String DELETE_TAG_SQL = "DELETE FROM tag WHERE id=?";

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public TagDaoImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean create(Tag tag) {
        int rows = jdbcTemplate.update(CREATE_TAG_SQL, tag.getName());
        return rows > 0;
    }

    public Tag read(Long id){
        Tag tag = jdbcTemplate.queryForObject(READ_TAG_SQL, new Object[]{id}, new TagMapper());
        return tag;
    }

    public boolean delete(Long id) {
        int rows = jdbcTemplate.update(DELETE_TAG_SQL, id);
        return rows > 0;
    }
}
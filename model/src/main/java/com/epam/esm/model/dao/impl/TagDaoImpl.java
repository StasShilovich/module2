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

    private final static String READ_TAG_SQL = "SELECT * FROM tag WHERE id=?";

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public TagDaoImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean create(Tag tag) throws DaoException {
        return false;
    }

    public Tag read(Long id) throws DaoException {
        Tag tag = jdbcTemplate.queryForObject(READ_TAG_SQL, new Object[]{id}, new TagMapper());
        System.out.println(tag);
        return tag;
    }

    public boolean delete(Long id) throws DaoException {
        return false;
    }
}
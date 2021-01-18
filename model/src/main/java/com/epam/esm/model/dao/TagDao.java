package com.epam.esm.model.dao;

import com.epam.esm.model.dao.entity.Tag;
import com.epam.esm.model.dao.exception.DaoException;

public interface TagDao {

    boolean create(Tag tag) throws DaoException;

    Tag read(Long id) throws DaoException;

    boolean delete(Long id) throws DaoException;
}

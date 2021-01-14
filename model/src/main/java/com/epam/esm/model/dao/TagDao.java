package com.epam.esm.model.dao;

import com.epam.esm.model.dao.entity.Tag;
import com.epam.esm.model.dao.exception.DaoException;

public interface TagDao {
    boolean create(Tag tag) ;

    Tag read(Long id);

    boolean delete(Long id) ;
}

package com.epam.esm.model.service.impl;

import com.epam.esm.model.dao.TagDao;
import com.epam.esm.model.dao.entity.Tag;
import com.epam.esm.model.dao.exception.DaoException;
import com.epam.esm.model.service.TagService;
import com.epam.esm.model.service.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public Tag find(Long id) throws ServiceException {
        try {
            Tag tag = tagDao.read(id);
            return tag;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}

package com.epam.esm.model.service.impl;

import com.epam.esm.model.dao.TagDao;
import com.epam.esm.model.dao.entity.Tag;
import com.epam.esm.model.dao.exception.DaoException;
import com.epam.esm.model.service.TagService;
import com.epam.esm.model.service.converter.impl.TagConverter;
import com.epam.esm.model.service.dto.TagDTO;
import com.epam.esm.model.service.exception.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private TagDao tagDao;
    private TagConverter tagConverter;

    public TagServiceImpl(TagDao tagDao, TagConverter tagConverter) {
        this.tagDao = tagDao;
        this.tagConverter = tagConverter;
    }

    public TagDTO find(Long id) throws ServiceException {
        try {
            Tag tag = tagDao.read(id);
            TagDTO tagDTO = tagConverter.toDTO(tag);
            return tagDTO;
        } catch (DataAccessException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean add(TagDTO tagDTO) throws ServiceException {
        try {
            Tag tag = tagConverter.fromDTO(tagDTO);
            boolean isAdd = tagDao.create(tag);
            return isAdd;
        } catch (DataAccessException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            boolean isDelete = tagDao.delete(id);
            return isDelete;
        } catch (DataAccessException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}

package com.epam.esm.model.service;

import com.epam.esm.model.dao.entity.Tag;
import com.epam.esm.model.service.exception.ServiceException;

public interface TagService {
    Tag find(Long id) throws ServiceException;
}

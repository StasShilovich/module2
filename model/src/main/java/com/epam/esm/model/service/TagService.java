package com.epam.esm.model.service;

import com.epam.esm.model.service.dto.TagDTO;
import com.epam.esm.model.service.exception.ServiceException;

public interface TagService {
    TagDTO find(Long id) throws ServiceException;

    boolean add(TagDTO tagDTO) throws ServiceException;

    boolean delete(Long id) throws ServiceException;
}

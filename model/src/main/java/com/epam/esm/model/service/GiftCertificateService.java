package com.epam.esm.model.service;

import com.epam.esm.model.dao.entity.GiftCertificate;
import com.epam.esm.model.service.dto.CertificateDTO;
import com.epam.esm.model.service.exception.ServiceException;

public interface GiftCertificateService {
    CertificateDTO find(Long id) throws ServiceException;

    boolean add(CertificateDTO certificateDTO) throws ServiceException;

    boolean delete(Long id) throws ServiceException;
}

package com.epam.esm.model.service;

import com.epam.esm.model.service.dto.CertificateDTO;
import com.epam.esm.model.service.exception.ServiceException;

import java.util.List;

public interface GiftCertificateService {

    CertificateDTO find(Long id) throws ServiceException;

    boolean add(CertificateDTO certificateDTO) throws ServiceException;

    boolean update(CertificateDTO certificateDTO) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    List<CertificateDTO> findByTag(String tagName) throws ServiceException;

    List<CertificateDTO> searchByNameOrDesc(String part) throws ServiceException;

    List<CertificateDTO> sortByName() throws ServiceException;

    List<CertificateDTO> sortByNameDesc() throws ServiceException;

    List<CertificateDTO> sortByDate() throws ServiceException;

    List<CertificateDTO> sortByDateDesc() throws ServiceException;
}

package com.epam.esm.model.service.impl;

import com.epam.esm.model.dao.GiftCertificateDao;
import com.epam.esm.model.dao.entity.GiftCertificate;
import com.epam.esm.model.dao.exception.DaoException;
import com.epam.esm.model.service.GiftCertificateService;
import com.epam.esm.model.service.converter.impl.CertificateConverter;
import com.epam.esm.model.service.dto.CertificateDTO;
import com.epam.esm.model.service.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final static Logger logger = Logger.getLogger(GiftCertificateServiceImpl.class);
    private final static String ID = "id";
    private final GiftCertificateDao certificateDao;
    private final CertificateConverter certificateConverter;

    public GiftCertificateServiceImpl(GiftCertificateDao certificateDao, CertificateConverter certificateConverter) {
        this.certificateDao = certificateDao;
        this.certificateConverter = certificateConverter;
    }

    @Override
    public CertificateDTO find(Long id) throws ServiceException {
        try {
            GiftCertificate certificate = certificateDao.read(id);
            CertificateDTO certificateDTO = certificateConverter.toDTO(certificate);
            return certificateDTO;
        } catch (DaoException e) {
            logger.error("Find certificate service exception", e);
            throw new ServiceException("Find certificate service exception", e);
        }
    }

    @Override
    public boolean add(CertificateDTO certificateDTO) throws ServiceException {
        try {
            GiftCertificate certificate = certificateConverter.fromDTO(certificateDTO);
            boolean result = certificateDao.create(certificate);
            return result;
        } catch (DaoException e) {
            logger.error("Add certificate service exception", e);
            throw new ServiceException("Add certificate service exception", e);
        }
    }

    @Override
    public boolean update(CertificateDTO certificateDTO) throws ServiceException {
        try {
            Map<String, Object> map = new ObjectMapper().convertValue(certificateDTO, Map.class);
            Map<String, Object> collect = map.entrySet().stream()
                    .filter(e -> e.getValue() != null)
                    .filter(e -> !e.getKey().equals(ID))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            boolean result = certificateDao.update(collect, (Long) map.get(ID));
            return result;
        } catch (DaoException e) {
            logger.error("Update certificate service exception", e);
            throw new ServiceException("Update certificate service exception", e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            boolean result = certificateDao.delete(id);
            return result;
        } catch (DaoException e) {
            logger.error("Delete certificate service exception", e);
            throw new ServiceException("Delete certificate service exception", e);
        }
    }

    @Override
    public List<CertificateDTO> findByTag(String tagName) throws ServiceException {
        try {
            List<GiftCertificate> byTag = certificateDao.findByTag(tagName);
            return byTag.stream().map(certificateConverter::toDTO).collect(Collectors.toList());
        } catch (DaoException e) {
            logger.error("Find by tag service exception", e);
            throw new ServiceException("Find by tag service exception", e);
        }
    }

    @Override
    public List<CertificateDTO> searchByNameOrDesc(String part) throws ServiceException {
        try {
            List<GiftCertificate> certificates = certificateDao.searchByNameOrDesc(part);
            return certificates.stream().map(certificateConverter::toDTO).collect(Collectors.toList());
        } catch (DaoException e) {
            logger.error("Search by name or description exception", e);
            throw new ServiceException("Search by name or description exception", e);
        }
    }

    @Override
    public List<CertificateDTO> sortByName() throws ServiceException {
        try {
            List<GiftCertificate> certificates = certificateDao.sortByName();
            return certificates.stream().map(certificateConverter::toDTO).collect(Collectors.toList());
        } catch (DaoException e) {
            logger.error("Sort by name exception", e);
            throw new ServiceException("Sort by name exception", e);
        }
    }

    @Override
    public List<CertificateDTO> sortByNameDesc() throws ServiceException {
        try {
            List<GiftCertificate> certificates = certificateDao.sortByName();
            List<CertificateDTO> certificateDTOS = certificates.stream().map(certificateConverter::toDTO).collect(Collectors.toList());
            Collections.reverse(certificateDTOS);
            return certificateDTOS;
        } catch (DaoException e) {
            logger.error("Sort by name desc exception", e);
            throw new ServiceException("Sort by name desc exception", e);
        }
    }

    @Override
    public List<CertificateDTO> sortByDate() throws ServiceException {
        try {
            List<GiftCertificate> certificates = certificateDao.sortByDate();
            return certificates.stream().map(certificateConverter::toDTO).collect(Collectors.toList());
        } catch (DaoException e) {
            logger.error("Sort by date exception", e);
            throw new ServiceException("Sort by date exception", e);
        }
    }

    @Override
    public List<CertificateDTO> sortByDateDesc() throws ServiceException {
        try {
            List<GiftCertificate> certificates = certificateDao.sortByDate();
            List<CertificateDTO> certificateDTOS = certificates.stream().map(certificateConverter::toDTO).collect(Collectors.toList());
            Collections.reverse(certificateDTOS);
            return certificateDTOS;
        } catch (DaoException e) {
            logger.error("Sort by date desc exception", e);
            throw new ServiceException("Sort by date desc exception", e);
        }
    }
}

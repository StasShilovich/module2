package com.epam.esm.model.service.impl;

import com.epam.esm.model.dao.GiftCertificateDao;
import com.epam.esm.model.dao.entity.GiftCertificate;
import com.epam.esm.model.dao.exception.DaoException;
import com.epam.esm.model.service.GiftCertificateService;
import com.epam.esm.model.service.converter.impl.CertificateConverter;
import com.epam.esm.model.service.dto.CertificateDTO;
import com.epam.esm.model.service.exception.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private GiftCertificateDao certificateDao;
    private CertificateConverter certificateConverter;

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
        } catch (DataAccessException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean add(CertificateDTO certificateDTO) throws ServiceException {
        try {
            GiftCertificate certificate = certificateConverter.fromDTO(certificateDTO);
            boolean result = certificateDao.create(certificate);
            return result;
        } catch (DataAccessException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            boolean result = certificateDao.delete(id);
            return result;
        } catch (DataAccessException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}

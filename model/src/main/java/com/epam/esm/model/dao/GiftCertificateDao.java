package com.epam.esm.model.dao;

import com.epam.esm.model.dao.entity.GiftCertificate;
import com.epam.esm.model.dao.exception.DaoException;

import java.util.List;

public interface GiftCertificateDao {
    boolean create(GiftCertificate certificate);

    GiftCertificate read(Long id);

    GiftCertificate update(GiftCertificate certificate);

    boolean delete(Long id);

    GiftCertificate findByTag(String tag);

    List<GiftCertificate> sortByName();

    List<GiftCertificate> sortByDate();
}
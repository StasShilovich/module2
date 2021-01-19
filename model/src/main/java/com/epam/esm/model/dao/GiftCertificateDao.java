package com.epam.esm.model.dao;

import com.epam.esm.model.dao.entity.GiftCertificate;
import com.epam.esm.model.dao.entity.SortType;
import com.epam.esm.model.dao.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface GiftCertificateDao {

    GiftCertificate create(GiftCertificate certificate) throws DaoException;

    GiftCertificate read(Long id) throws DaoException;

    boolean update(Map<String, Object> map) throws DaoException;

    boolean delete(Long id) throws DaoException;

    List<GiftCertificate> findByTag(String tag) throws DaoException;

    List<GiftCertificate> searchByNameOrDesc(String part) throws DaoException;

    List<GiftCertificate> sortByDate(SortType sortType) throws DaoException;

    List<GiftCertificate> sortByName(SortType sortType) throws DaoException;
}

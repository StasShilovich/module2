package com.epam.esm.model.dao.impl;

import com.epam.esm.model.dao.GiftCertificateDao;
import com.epam.esm.model.dao.entity.GiftCertificate;
import com.epam.esm.model.dao.exception.DaoException;
import com.epam.esm.model.dao.mapper.GiftCertificateMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final static String READ_GIFT_CERTIFICATE_SQL =
            "SELECT id,name,description,price,duration,create_date,last_update_date FROM gift_certificate WHERE id=?";
    private final static String CREATE_GIFT_CERTIFICATE_SQL =
            "INSERT INTO gift_certificate(name,description,price,duration,create_date,last_update_date) VALUES (?,?,?,?,?,?)";
    private final static String DELETE_GIFT_CERTIFICATE_SQL="DELETE FROM gift_certificate WHERE ID=?";

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public GiftCertificateDaoImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(GiftCertificate certificate) {
        int rows = jdbcTemplate.update(CREATE_GIFT_CERTIFICATE_SQL, certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(), certificate.getCreateDate(), certificate.getLastUpdateDate());
        return rows > 0;
    }

    @Override
    public GiftCertificate read(Long id) {
        GiftCertificate certificate = jdbcTemplate
                .queryForObject(READ_GIFT_CERTIFICATE_SQL, new Object[]{id}, new GiftCertificateMapper());
        return certificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate certificate) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        int rows = jdbcTemplate.update(DELETE_GIFT_CERTIFICATE_SQL, id);
        return rows>0;
    }

    @Override
    public GiftCertificate findByTag(String tag) {
        return null;
    }

    @Override
    public List<GiftCertificate> sortByName() {
        return null;
    }

    @Override
    public List<GiftCertificate> sortByDate() {
        return null;
    }
}

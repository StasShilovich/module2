package com.epam.esm.model.service.impl;

import com.epam.esm.model.dao.GiftCertificateDao;
import com.epam.esm.model.dao.entity.GiftCertificate;
import com.epam.esm.model.dao.exception.DaoException;
import com.epam.esm.model.service.GiftCertificateService;
import com.epam.esm.model.service.converter.impl.CertificateConverter;
import com.epam.esm.model.service.dto.CertificateDTO;
import com.epam.esm.model.service.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class GiftCertificateServiceImplTest {

    @Mock
    GiftCertificateDao dao;
    CertificateConverter converter;
    GiftCertificateService service;
    GiftCertificate certificate;
    CertificateDTO certificateDTO;

    @BeforeEach
    public void setUp() {
        converter = new CertificateConverter();
        service = new GiftCertificateServiceImpl(dao, converter);
        certificate = new GiftCertificate(1L, "name", "description", new BigDecimal("20"), 5,
                LocalDateTime.parse("2021-01-14T13:10:00"), LocalDateTime.parse("2022-01-14T13:10:00"));
        certificateDTO = new CertificateDTO(1L, "name", "description", new BigDecimal("20"), 5,
                "2021-01-14T13:10:00.0", "2022-01-14T13:10:00.0");
    }

    @Test
    void testFindPositive() throws DaoException, ServiceException {
        lenient().when(dao.read(anyLong())).thenReturn(certificate);
        CertificateDTO actual = service.find(1L);
        assertEquals(actual, certificateDTO);
    }

    @Test
    void testFindNegative() throws DaoException, ServiceException {
        certificate.setName("names");
        lenient().when(dao.read(anyLong())).thenReturn(certificate);
        CertificateDTO actual = service.find(1L);
        assertNotEquals(actual, certificateDTO);
    }

    @Test
    void testFindException() throws DaoException {
        lenient().when(dao.read(anyLong())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.find(1L));
    }

    @Test
    void testAddPositive() throws DaoException, ServiceException {
        lenient().when(dao.create(any(GiftCertificate.class))).thenReturn(true);
        boolean condition = service.add(certificateDTO);
        assertTrue(condition);
    }

    @Test
    void testAddNegative() throws DaoException, ServiceException {
        lenient().when(dao.create(any(GiftCertificate.class))).thenReturn(false);
        boolean condition = service.add(certificateDTO);
        assertFalse(condition);
    }

    @Test
    void testAddException() throws DaoException {
        lenient().when(dao.create(any(GiftCertificate.class))).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.add(certificateDTO));
    }

    @Test
    void testUpdatePositive() throws DaoException, ServiceException {
        lenient().when(dao.update(anyMap(), anyLong())).thenReturn(true);
        boolean condition = service.update(certificateDTO);
        assertTrue(condition);
    }

    @Test
    void testUpdateNegative() throws DaoException, ServiceException {
        lenient().when(dao.update(anyMap(), anyLong())).thenReturn(false);
        boolean condition = service.update(certificateDTO);
        assertFalse(condition);
    }

    @Test
    void testUpdateException() throws DaoException {
        lenient().when(dao.update(anyMap(), anyLong())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.update(certificateDTO));
    }

    @Test
    void testDeletePositive() throws DaoException, ServiceException {
        lenient().when(dao.delete(anyLong())).thenReturn(true);
        boolean condition = service.delete(1L);
        assertTrue(condition);
    }

    @Test
    void testDeleteNegative() throws DaoException, ServiceException {
        lenient().when(dao.delete(anyLong())).thenReturn(false);
        boolean condition = service.delete(1L);
        assertFalse(condition);
    }

    @Test
    void testDeleteException() throws DaoException {
        lenient().when(dao.delete(anyLong())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.delete(1L));
    }

    @Test
    void testFindByTagPositive() throws DaoException, ServiceException {
        lenient().when(dao.findByTag(anyString())).thenReturn(new ArrayList<>());
        List<CertificateDTO> actual = service.findByTag("tagName");
        assertEquals(actual, new ArrayList<>());
    }

    @Test
    void testFindByTagNegative() throws DaoException, ServiceException {
        ArrayList<GiftCertificate> list = new ArrayList<>();
        list.add(certificate);
        lenient().when(dao.findByTag(anyString())).thenReturn(list);
        List<CertificateDTO> actual = service.findByTag("tagName");
        assertNotEquals(actual, new ArrayList<>());
    }

    @Test
    void testFindByTagException() throws DaoException {
        lenient().when(dao.findByTag(anyString())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.findByTag("name"));
    }

    @Test
    void testSearchByNameOrDescPositive() throws DaoException, ServiceException {
        lenient().when(dao.searchByNameOrDesc(anyString())).thenReturn(new ArrayList<>());
        List<CertificateDTO> actual = service.searchByNameOrDesc("part");
        assertEquals(actual, new ArrayList<>());
    }

    @Test
    void testSearchByNameOrDescNegative() throws DaoException, ServiceException {
        ArrayList<GiftCertificate> list = new ArrayList<>();
        list.add(certificate);
        lenient().when(dao.searchByNameOrDesc(anyString())).thenReturn(list);
        List<CertificateDTO> actual = service.searchByNameOrDesc("part");
        assertNotEquals(actual, new ArrayList<>());
    }

    @Test
    void testSearchByNameOrDescException() throws DaoException {
        lenient().when(dao.searchByNameOrDesc(anyString())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.searchByNameOrDesc("part"));
    }

    @Test
    void testSortByNamePositive() throws DaoException, ServiceException {
        lenient().when(dao.sortByName()).thenReturn(new ArrayList<>());
        List<CertificateDTO> actual = service.sortByName();
        assertEquals(actual, new ArrayList<>());
    }

    @Test
    void testSortByNameNegative() throws DaoException, ServiceException {
        ArrayList<GiftCertificate> list = new ArrayList<>();
        list.add(certificate);
        lenient().when(dao.sortByName()).thenReturn(list);
        List<CertificateDTO> actual = service.sortByName();
        assertNotEquals(actual, new ArrayList<>());
    }

    @Test
    void testSortByNameException() throws DaoException {
        lenient().when(dao.sortByName()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.sortByName());
    }

    @Test
    void testSortByNameDescPositive() throws DaoException, ServiceException {
        lenient().when(dao.sortByName()).thenReturn(new ArrayList<>());
        List<CertificateDTO> actual = service.sortByNameDesc();
        assertEquals(actual, new ArrayList<>());
    }

    @Test
    void testSortByNameDescNegative() throws DaoException, ServiceException {
        ArrayList<GiftCertificate> list = new ArrayList<>();
        list.add(certificate);
        lenient().when(dao.sortByName()).thenReturn(list);
        List<CertificateDTO> actual = service.sortByNameDesc();
        assertNotEquals(actual, new ArrayList<>());
    }

    @Test
    void testSortByNameDescException() throws DaoException {
        lenient().when(dao.sortByName()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.sortByNameDesc());
    }

    @Test
    void testSortByDatePositive() throws DaoException, ServiceException {
        lenient().when(dao.sortByDate()).thenReturn(new ArrayList<>());
        List<CertificateDTO> actual = service.sortByDate();
        assertEquals(actual, new ArrayList<>());
    }

    @Test
    void testSortByDateNegative() throws DaoException, ServiceException {
        ArrayList<GiftCertificate> list = new ArrayList<>();
        list.add(certificate);
        lenient().when(dao.sortByDate()).thenReturn(list);
        List<CertificateDTO> actual = service.sortByDate();
        assertNotEquals(actual, new ArrayList<>());
    }

    @Test
    void testSortByDateException() throws DaoException {
        lenient().when(dao.sortByDate()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.sortByDate());
    }

    @Test
    void testSortByDateDescPositive() throws DaoException, ServiceException {
        lenient().when(dao.sortByDate()).thenReturn(new ArrayList<>());
        List<CertificateDTO> actual = service.sortByDateDesc();
        assertEquals(actual, new ArrayList<>());
    }

    @Test
    void testSortByDateDescNegative() throws DaoException, ServiceException {
        ArrayList<GiftCertificate> list = new ArrayList<>();
        list.add(certificate);
        lenient().when(dao.sortByDate()).thenReturn(list);
        List<CertificateDTO> actual = service.sortByDateDesc();
        assertNotEquals(actual, new ArrayList<>());
    }

    @Test
    void testSortByDateDescException() throws DaoException {
        lenient().when(dao.sortByDate()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> service.sortByDateDesc());
    }
}

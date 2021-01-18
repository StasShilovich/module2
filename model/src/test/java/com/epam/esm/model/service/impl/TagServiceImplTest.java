package com.epam.esm.model.service.impl;

import com.epam.esm.model.dao.TagDao;
import com.epam.esm.model.dao.entity.Tag;
import com.epam.esm.model.dao.exception.DaoException;
import com.epam.esm.model.service.TagService;
import com.epam.esm.model.service.converter.impl.TagConverter;
import com.epam.esm.model.service.dto.TagDTO;
import com.epam.esm.model.service.exception.ServiceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TagServiceImplTest {

    @Mock
    TagDao tagDao;
    TagConverter tagConverter;
    TagService tagService;

    @BeforeAll
    public void setUp() {
        tagConverter = new TagConverter();
        tagService = new TagServiceImpl(tagDao, tagConverter);
    }

    @Test
    void find() throws ServiceException, DaoException {
        when(tagDao.read(anyLong())).thenReturn(new Tag(1L, "name"));
        TagDTO actual = tagService.find(1L);
        TagDTO expected = new TagDTO(1L, "name");
        assertEquals(actual, expected);
    }

    @Test
    void add() {
    }

    @Test
    void delete() {
    }
}
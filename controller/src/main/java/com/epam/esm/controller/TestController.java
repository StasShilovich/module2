package com.epam.esm.controller;

import com.epam.esm.model.dao.entity.Tag;
import com.epam.esm.model.service.TagService;
import com.epam.esm.model.service.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private TagService tagService;

    public TestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/hello")
    public String print() {
        return "Hello |||||| World!";
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable(name = "id") Long id) throws ServiceException {
        Tag tag = tagService.find(id);
        return ResponseEntity.ok(tag);
    }


}

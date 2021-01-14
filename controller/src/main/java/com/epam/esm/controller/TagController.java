package com.epam.esm.controller;

import com.epam.esm.model.service.TagService;
import com.epam.esm.model.service.dto.TagDTO;
import com.epam.esm.model.service.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> find(@PathVariable(name = "id") Long id) {
        try {
            TagDTO tag = tagService.find(id);
            return ResponseEntity.ok(tag);
        } catch (ServiceException e) {
            return ResponseEntity.of(Optional.of(new TagDTO()));
        }
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Boolean> add(@RequestBody String tagName) {
        try {
            TagDTO tag = new TagDTO();
            tag.setName(tagName);
            boolean result = tagService.add(tag);
            return ResponseEntity.ok(result);
        } catch (ServiceException e) {
            return ResponseEntity.ok(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name = "id") Long id) {
        try {
            boolean result = tagService.delete(id);
            return ResponseEntity.ok(result);
        } catch (ServiceException e) {
            return ResponseEntity.ok(false);
        }
    }
}
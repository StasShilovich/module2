package com.epam.esm.controller;

import com.epam.esm.model.service.TagService;
import com.epam.esm.model.service.dto.TagDTO;
import com.epam.esm.model.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> handleException(ServiceException exception) {
        Response response = new Response(exception.getLocalizedMessage(), (long) HttpStatus.NOT_FOUND.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> find(@PathVariable(name = "id") Long id) throws ServiceException {
        TagDTO tag = tagService.find(id);
        return ResponseEntity.ok(tag);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Boolean> add(@RequestBody String tagName) throws ServiceException {
        TagDTO tag = new TagDTO();
        tag.setName(tagName);
        boolean result = tagService.add(tag);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name = "id") Long id) throws ServiceException {
        boolean result = tagService.delete(id);
        return ResponseEntity.ok(result);
    }
}

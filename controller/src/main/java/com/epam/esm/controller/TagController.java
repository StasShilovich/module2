package com.epam.esm.controller;

import com.epam.esm.model.service.TagService;
import com.epam.esm.model.service.dto.TagDTO;
import com.epam.esm.model.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Tag RestAPI.
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    private TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Handle exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> handleException(ServiceException exception) {
        Response response = new Response(exception.getLocalizedMessage(), (long) HttpStatus.NOT_FOUND.value());
        return ResponseEntity.ok(response);
    }

    /**
     * Find tag by id.
     *
     * @param id the id
     * @return the response entity
     * @throws ServiceException the service exception
     */
    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> find(@PathVariable(name = "id") Long id) throws ServiceException {
        TagDTO tag = tagService.find(id);
        return ResponseEntity.ok(tag);
    }

    /**
     * Add tag.
     *
     * @param tagName the tag name
     * @return the response entity
     * @throws ServiceException the service exception
     */
    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Boolean> add(@RequestBody String tagName) throws ServiceException {
        TagDTO tag = new TagDTO();
        tag.setName(tagName);
        boolean result = tagService.add(tag);
        return ResponseEntity.ok(result);
    }

    /**
     * Delete tag by id.
     *
     * @param id the id
     * @return the response entity
     * @throws ServiceException the service exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name = "id") Long id) throws ServiceException {
        boolean result = tagService.delete(id);
        return ResponseEntity.ok(result);
    }
}

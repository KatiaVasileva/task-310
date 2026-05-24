package com.vasileva.task310.controller;

import com.vasileva.task310.model.tag.TagIn;
import com.vasileva.task310.model.tag.TagOut;
import com.vasileva.task310.service.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1.0/tags")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public Collection<TagOut> getAll() {
        return tagService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagOut createTag(@RequestBody @Valid TagIn tagIn) {
        return tagService.create(tagIn);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TagOut updateTag(@RequestBody @Valid TagIn tagIn) {
        try {
            return tagService.update(tagIn);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public TagOut getTagById(@PathVariable Long id) {
        return tagService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTagById(@PathVariable Long id) {
        boolean isDeleted = tagService.delete(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

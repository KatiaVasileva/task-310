package com.vasileva.task310.controller;

import com.vasileva.task310.model.writer.WriterIn;
import com.vasileva.task310.model.writer.WriterOut;
import com.vasileva.task310.service.WriterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1.0/writers")
@AllArgsConstructor
public class WriterController {

    private final WriterService writerService;

    @GetMapping
    public Collection<WriterOut> getAll() {
        return writerService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WriterOut createWriter(@RequestBody @Valid WriterIn writeIn) {
        return writerService.create(writeIn);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public WriterOut updateWriter(@RequestBody @Valid WriterIn writeIn) {
        try {
            return writerService.update(writeIn);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public WriterOut getWriterById(@PathVariable Long id) {
        return writerService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWriterById(@PathVariable Long id) {
        boolean isDeleted = writerService.delete(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

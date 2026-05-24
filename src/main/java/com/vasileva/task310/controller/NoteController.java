package com.vasileva.task310.controller;

import com.vasileva.task310.model.note.NoteIn;
import com.vasileva.task310.model.note.NoteOut;
import com.vasileva.task310.service.NoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1.0/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public Collection<NoteOut> getAll() {
        return noteService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteOut createNote(@RequestBody @Valid NoteIn noteIn) {
        return noteService.create(noteIn);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public NoteOut updateNote(@RequestBody @Valid NoteIn noteIn) {
        try {
            return noteService.update(noteIn);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public NoteOut getNoteById(@PathVariable Long id) {
        return noteService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNoteById(@PathVariable Long id) {
        boolean isDeleted = noteService.delete(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

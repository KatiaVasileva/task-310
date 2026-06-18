package com.vasileva.task310.controller;

import com.vasileva.task310.model.note.NoteIn;
import com.vasileva.task310.model.note.NoteOut;
import com.vasileva.task310.service.NoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
        return noteService.update(noteIn);
    }

    @GetMapping("/{id}")
    public NoteOut getNoteById(@PathVariable Long id) {
        return noteService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNoteById(@PathVariable Long id) {
        noteService.delete(id);
    }
}

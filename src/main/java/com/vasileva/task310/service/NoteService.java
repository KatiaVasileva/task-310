package com.vasileva.task310.service;

import com.vasileva.task310.mapper.NoteDto;
import com.vasileva.task310.model.note.Note;
import com.vasileva.task310.model.note.NoteIn;
import com.vasileva.task310.model.note.NoteOut;
import com.vasileva.task310.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {

    private final Repo<Note> repoImpl;
    private final NoteDto mapper;

    public List<NoteOut> getAll() {
        return repoImpl
                .getAll()
                .map(mapper::out)
                .toList();
    }

    public NoteOut get(Long id) {
        return repoImpl
                .get(id)
                .map(mapper::out)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public NoteOut create(NoteIn noteIn) {
        return repoImpl
                .create(mapper.in(noteIn))
                .map(mapper::out)
                .orElseThrow();
    }

    public NoteOut update(NoteIn noteIn) {
        return repoImpl
                .update(mapper.in(noteIn))
                .map(mapper::out)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public boolean delete(Long id) {
        return repoImpl.delete(id);
    }
}


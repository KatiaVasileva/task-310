package com.vasileva.task310.service;

import com.vasileva.task310.mapper.NoteDto;
import com.vasileva.task310.model.Issue.Issue;
import com.vasileva.task310.model.note.Note;
import com.vasileva.task310.model.note.NoteIn;
import com.vasileva.task310.model.note.NoteOut;
import com.vasileva.task310.repository.IssueRepository;
import com.vasileva.task310.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final IssueRepository issueRepository;
    private final NoteDto mapper;

    public List<NoteOut> getAll() {
        return noteRepository
                .findAll()
                .stream()
                .map(mapper::out)
                .toList();
    }

    public NoteOut get(Long id) {
        return noteRepository
                .findById(id)
                .map(mapper::out)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Note with id=%s not found", id)));
    }

    public NoteOut create(NoteIn noteIn) {
        Note newNote = mapper.in(noteIn);
        Issue issue = issueRepository.findById(noteIn.getIssueId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Issue with id=%s not found", noteIn.getIssueId())));
        newNote.setIssue(issue);
        return mapper
                .out(noteRepository.save(newNote));
    }

    public NoteOut update(NoteIn noteIn) {
        Note noteToUpdate = noteRepository.findById(noteIn.getId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Note with id=%s not found", noteIn.getId())));
        Issue issue = issueRepository.findById(noteIn.getIssueId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Issue with id=%s not found", noteIn.getIssueId())));
        noteToUpdate.setIssue(issue);
        noteToUpdate.setContent(noteIn.getContent());
        return mapper.out(noteRepository
                .save(noteToUpdate));
    }

    public void delete(Long id) {
        if(!noteRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Note with id=%d not found", id));
        }
        noteRepository.deleteById(id);
    }
}


package com.vasileva.task310.service;

import com.vasileva.task310.model.note.NoteIn;
import com.vasileva.task310.model.note.NoteOut;
import com.vasileva.task310.repository.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService implements RestService<NoteIn, NoteOut> {

    public static final ParameterizedTypeReference<List<NoteOut>> LIST_NOTE_OUT =
            new ParameterizedTypeReference<>() {
            };
    private final RestClient noteRestClient;
    private final IssueRepository issueRepository;

    @Override
    public List<NoteOut> getAll() {
        return noteRestClient
                .get()
                .retrieve()
                .body(LIST_NOTE_OUT);
    }

    @Override
    public NoteOut get(long id) {
        return noteRestClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .body(NoteOut.class);
    }

    @Override
    public NoteOut create(NoteIn noteIn) {
        Long issueId = noteIn.getIssueId();
        if (issueRepository.existsById(issueId)) {
            return noteRestClient
                    .post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(noteIn)
                    .retrieve()
                    .body(NoteOut.class);
        } else {
            throw new IllegalStateException("incorrect issueId=" + issueId);
        }
    }

    @Override
    public NoteOut update(NoteIn noteIn) {
        Long issueId = noteIn.getIssueId();
        if (issueRepository.existsById(issueId)) {
            return noteRestClient
                    .put()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(noteIn)
                    .retrieve()
                    .body(NoteOut.class);
        } else {
            throw new IllegalStateException("incorrect storyId=" + issueId);
        }
    }

    @Override
    public boolean delete(Long id) {
        return noteRestClient
                .delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .getStatusCode()
                .is2xxSuccessful();
    }
}


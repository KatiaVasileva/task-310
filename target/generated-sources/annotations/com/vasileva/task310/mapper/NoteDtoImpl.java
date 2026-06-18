package com.vasileva.task310.mapper;

import com.vasileva.task310.model.Issue.Issue;
import com.vasileva.task310.model.note.Note;
import com.vasileva.task310.model.note.NoteIn;
import com.vasileva.task310.model.note.NoteOut;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-11T18:33:03+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class NoteDtoImpl implements NoteDto {

    @Override
    public NoteOut out(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteOut.NoteOutBuilder noteOut = NoteOut.builder();

        noteOut.issueId( noteIssueId( note ) );
        noteOut.id( note.getId() );
        noteOut.content( note.getContent() );

        return noteOut.build();
    }

    @Override
    public Note in(NoteIn noteIn) {
        if ( noteIn == null ) {
            return null;
        }

        Note.NoteBuilder note = Note.builder();

        note.id( noteIn.getId() );
        note.content( noteIn.getContent() );

        return note.build();
    }

    private Long noteIssueId(Note note) {
        if ( note == null ) {
            return null;
        }
        Issue issue = note.getIssue();
        if ( issue == null ) {
            return null;
        }
        Long id = issue.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

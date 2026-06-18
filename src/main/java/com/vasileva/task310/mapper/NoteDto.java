package com.vasileva.task310.mapper;

import com.vasileva.task310.model.note.Note;
import com.vasileva.task310.model.note.NoteIn;
import com.vasileva.task310.model.note.NoteOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteDto {

    @Mapping(target = "issueId", source = "issue.id")
    NoteOut out(Note note);

    @Mapping(target = "issue", ignore = true)
    Note in(NoteIn noteIn);
}

package com.vasileva.task310.mapper;

import com.vasileva.task310.model.note.Note;
import com.vasileva.task310.model.note.NoteIn;
import com.vasileva.task310.model.note.NoteOut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteDto {

    NoteOut out(Note note);

    Note in(NoteIn noteIn);
}

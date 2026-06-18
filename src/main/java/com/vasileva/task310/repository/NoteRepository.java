package com.vasileva.task310.repository;

import com.vasileva.task310.model.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}

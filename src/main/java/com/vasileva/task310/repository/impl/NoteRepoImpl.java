package com.vasileva.task310.repository.impl;

import com.vasileva.task310.model.note.Note;
import com.vasileva.task310.repository.Repo;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Repository
public class NoteRepoImpl implements Repo<Note> {

    private final Map<Long, Note> memoryDatabase = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Stream<Note> getAll() {
        return memoryDatabase.values().stream();
    }

    @Override
    public Optional<Note> get(Long id) {
        return Optional.ofNullable(memoryDatabase.get(id));
    }

    @Override
    public Optional<Note> create(Note note) {
        long id = idGenerator.incrementAndGet();
        note.setId(id);
        memoryDatabase.put(id, note);
        return Optional.of(note);
    }

    @Override
    public Optional<Note> update(Note note) {
        memoryDatabase.put(note.getId(), note);
        return Optional.of(note);
    }

    @Override
    public boolean delete(Long id) {
        return memoryDatabase.remove(id) != null;
    }
}

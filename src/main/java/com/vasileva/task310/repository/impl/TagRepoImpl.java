package com.vasileva.task310.repository.impl;

import com.vasileva.task310.model.tag.Tag;
import com.vasileva.task310.repository.Repo;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Repository
public class TagRepoImpl implements Repo<Tag> {

    public final Map<Long, Tag> memoryDatabase = new ConcurrentHashMap<>();
    public static final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Stream<Tag> getAll() {
        return memoryDatabase.values().stream();
    }

    @Override
    public Optional<Tag> get(Long id) {
        return Optional.ofNullable(memoryDatabase.get(id));
    }

    @Override
    public Optional<Tag> create(Tag tag) {
        Long id = idGenerator.incrementAndGet();
        tag.setId(id);
        memoryDatabase.put(id, tag);
        return Optional.of(tag);
    }

    @Override
    public Optional<Tag> update(Tag tag) {
        memoryDatabase.put(tag.getId(), tag);
        return Optional.of(tag);
    }

    @Override
    public boolean delete(Long id) {
        return memoryDatabase.remove(id) != null;
    }
}

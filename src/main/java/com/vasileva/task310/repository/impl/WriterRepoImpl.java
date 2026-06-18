package com.vasileva.task310.repository.impl;

import com.vasileva.task310.model.writer.Writer;
import com.vasileva.task310.repository.Repo;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Repository
public class WriterRepoImpl implements Repo<Writer> {

    private final Map<Long, Writer> memoryDatabase = new ConcurrentHashMap<>();
    public static final AtomicLong idGenerator = new AtomicLong();

    public WriterRepoImpl() {
        long id = idGenerator.incrementAndGet();
        memoryDatabase.put(id, Writer.builder()
                .id(id)
                .login("eka.vasilev@gmail.com")
                .password("12345678")
                .firstname("Екатерина")
                .lastname("Васильева")
                .build());
    }

    @Override
    public Stream<Writer> getAll() {
        return memoryDatabase.values().stream();
    }

    @Override
    public Optional<Writer> get(Long id) {
        return Optional.ofNullable(memoryDatabase.get(id));
    }

    @Override
    public Optional<Writer> create(Writer writer) {
        long id = idGenerator.incrementAndGet();
        writer.setId(id);
        memoryDatabase.put(id, writer);
        return Optional.of(writer);
    }

    @Override
    public Optional<Writer> update(Writer writer) {
        memoryDatabase.put(writer.getId(), writer);
        return Optional.of(writer);
    }

    @Override
    public boolean delete(Long id) {
        return memoryDatabase.remove(id) != null;
    }
}

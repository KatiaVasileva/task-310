package com.vasileva.task310.repository.impl;

import com.vasileva.task310.model.Issue.Issue;
import com.vasileva.task310.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class IssueRepoImpl implements Repo<Issue> {

    private final Map<Long, Issue> memoryDatabase = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Stream<Issue> getAll() {
        return memoryDatabase.values().stream();
    }

    @Override
    public Optional<Issue> get(Long id) {
        return Optional.ofNullable(memoryDatabase.get(id));
    }

    @Override
    public Optional<Issue> create(Issue issue) {
        long id = idGenerator.incrementAndGet();
        issue.setId(id);
        issue.setCreated(LocalDateTime.now());
        memoryDatabase.put(id, issue);
        return Optional.ofNullable(memoryDatabase.get(id));
    }

    @Override
    public Optional<Issue> update(Issue issue) {
        Issue issueBD = memoryDatabase.get(issue.getId());
        issue.setCreated(issueBD.getCreated());
        issue.setModified(LocalDateTime.now());
        memoryDatabase.put(issue.getId(), issue);
        return Optional.of(issue);
    }

    @Override
    public boolean delete(Long id) {
        return memoryDatabase.remove(id) != null;
    }
}

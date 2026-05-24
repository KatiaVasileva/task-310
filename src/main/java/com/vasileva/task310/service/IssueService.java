package com.vasileva.task310.service;

import com.vasileva.task310.mapper.IssueDto;
import com.vasileva.task310.model.Issue.Issue;
import com.vasileva.task310.model.Issue.IssueIn;
import com.vasileva.task310.model.Issue.IssueOut;
import com.vasileva.task310.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {

    private final Repo<Issue> repoImpl;
    private final IssueDto mapper;

    public List<IssueOut> getAll() {
        return repoImpl
                .getAll()
                .map(mapper::out)
                .toList();
    }

    public IssueOut get(Long id) {
        return repoImpl
                .get(id)
                .map(mapper::out)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
    }

    public IssueOut create(IssueIn issueIn) {
        return repoImpl
                .create(mapper.in(issueIn))
                .map(mapper::out)
                .orElseThrow();
    }

    public IssueOut update(IssueIn issueIn) {
        return repoImpl
                .update(mapper.in(issueIn))
                .map(mapper::out)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
    }

    public boolean delete(Long id) {
        return repoImpl.delete(id);
    }
}


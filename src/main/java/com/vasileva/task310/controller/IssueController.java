package com.vasileva.task310.controller;

import com.vasileva.task310.model.Issue.IssueIn;
import com.vasileva.task310.model.Issue.IssueOut;
import com.vasileva.task310.service.IssueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1.0/issues")
@AllArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public Collection<IssueOut> getAll() {
        return issueService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssueOut createIssue(@RequestBody @Valid IssueIn issueIn) {
        return issueService.create(issueIn);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public IssueOut updateIssue(@RequestBody @Valid IssueIn issueIn) {
        try {
            return issueService.update(issueIn);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public IssueOut getIssueById(@PathVariable Long id) {
        return issueService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIssueById(@PathVariable Long id) {
        boolean isDeleted = issueService.delete(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

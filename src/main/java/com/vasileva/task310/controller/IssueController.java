package com.vasileva.task310.controller;

import com.vasileva.task310.model.Issue.IssueIn;
import com.vasileva.task310.model.Issue.IssueOut;
import com.vasileva.task310.service.IssueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
        return issueService.update(issueIn);
    }

    @GetMapping("/{id}")
    public IssueOut getIssueById(@PathVariable Long id) {
        return issueService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIssueById(@PathVariable Long id) {
        issueService.delete(id);
    }
}

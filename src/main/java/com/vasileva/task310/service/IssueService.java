package com.vasileva.task310.service;

import com.vasileva.task310.mapper.IssueDto;
import com.vasileva.task310.model.Issue.Issue;
import com.vasileva.task310.model.Issue.IssueIn;
import com.vasileva.task310.model.Issue.IssueOut;
import com.vasileva.task310.model.tag.Tag;
import com.vasileva.task310.model.writer.Writer;
import com.vasileva.task310.repository.IssueRepository;
import com.vasileva.task310.repository.TagRepository;
import com.vasileva.task310.repository.WriterRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final WriterRepository writerRepository;
    private final IssueDto mapper;
    private final TagRepository tagRepository;

    public List<IssueOut> getAll() {
        return issueRepository
                .findAll()
                .stream()
                .map(mapper::out)
                .toList();
    }

    public IssueOut get(Long id) {
        return issueRepository
                .findById(id)
                .map(mapper::out)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Issue with id=%d not found", id)));
    }

    public IssueOut create(IssueIn issueIn) {
        Issue issueFromDB = issueRepository.findByTitle(issueIn.getTitle());
        if (issueFromDB != null && Objects.equals(issueFromDB.getWriter().getId(), issueIn.getWriterId())) {
            throw new DataIntegrityViolationException("Issue already exists");
        }
        Issue issue = createIssue(issueIn);
        return mapper.out(issueRepository.save(issue));
    }

    private Issue createIssue(IssueIn issueIn) {
        Issue issue = mapper.in(issueIn);
        Writer writer = writerRepository.findById(issueIn.getWriterId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Writer with id=%d not found", issueIn.getWriterId())));
        issue.setWriter(writer);
        updateIssueTags(issue, issueIn);
        return issue;
    }

    public IssueOut update(IssueIn issueIn) {
        Issue issueToUpdate = issueRepository.findById(issueIn.getId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Issue with id=%d not found", issueIn.getId())));
        mapper.update(issueToUpdate, issueIn);
        updateIssueTags(issueToUpdate, issueIn);
        return mapper
                .out(issueRepository.save(issueToUpdate));
    }

    private void updateIssueTags(Issue issue, IssueIn issueIn) {
        Set<Tag> newTags = issueIn.getTags().stream()
                .map(name -> tagRepository.findByName(name)
                        .orElseGet(() -> {
                            Tag newTag = new Tag();
                            newTag.setName(name);
                            return tagRepository.save(newTag);
                        }))
                .collect(Collectors.toSet());
        issue.getTags().clear();
        issue.getTags().addAll(newTags);
    }

    @Transactional
    public void delete(Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Issue with id=%d not found", id)));

        Set<Long> tagsIdToDelete = issue.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());

        issueRepository.deleteById(id);

        if(!tagsIdToDelete.isEmpty()) {
            tagRepository.deleteOrphanTags(tagsIdToDelete);
        }
    }
}


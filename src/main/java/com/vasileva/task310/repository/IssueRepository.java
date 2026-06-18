package com.vasileva.task310.repository;

import com.vasileva.task310.model.Issue.Issue;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    Issue findByTitle(@Size(min = 2, max = 64) String title);
}

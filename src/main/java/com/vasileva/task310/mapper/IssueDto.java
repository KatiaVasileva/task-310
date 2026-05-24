package com.vasileva.task310.mapper;

import com.vasileva.task310.model.Issue.Issue;
import com.vasileva.task310.model.Issue.IssueIn;
import com.vasileva.task310.model.Issue.IssueOut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IssueDto {

    IssueOut out(Issue issue);

    Issue in(IssueIn issueIn);
}

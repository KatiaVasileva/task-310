package com.vasileva.task310.mapper;

import com.vasileva.task310.model.Issue.Issue;
import com.vasileva.task310.model.Issue.IssueIn;
import com.vasileva.task310.model.Issue.IssueOut;
import com.vasileva.task310.model.writer.Writer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-18T23:37:30+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class IssueDtoImpl implements IssueDto {

    @Override
    public IssueOut out(Issue issue) {
        if ( issue == null ) {
            return null;
        }

        IssueOut.IssueOutBuilder issueOut = IssueOut.builder();

        issueOut.writerId( issueWriterId( issue ) );
        issueOut.tags( mapTagsToStrings( issue.getTags() ) );
        issueOut.id( issue.getId() );
        issueOut.title( issue.getTitle() );
        issueOut.content( issue.getContent() );
        issueOut.created( issue.getCreated() );
        issueOut.modified( issue.getModified() );

        return issueOut.build();
    }

    @Override
    public Issue in(IssueIn issueIn) {
        if ( issueIn == null ) {
            return null;
        }

        Issue.IssueBuilder issue = Issue.builder();

        issue.title( issueIn.getTitle() );
        issue.content( issueIn.getContent() );

        return issue.build();
    }

    @Override
    public void update(Issue issue, IssueIn issueIn) {
        if ( issueIn == null ) {
            return;
        }

        issue.setTitle( issueIn.getTitle() );
        issue.setContent( issueIn.getContent() );
    }

    private Long issueWriterId(Issue issue) {
        if ( issue == null ) {
            return null;
        }
        Writer writer = issue.getWriter();
        if ( writer == null ) {
            return null;
        }
        Long id = writer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

package com.vasileva.task310.mapper;

import com.vasileva.task310.model.Issue.Issue;
import com.vasileva.task310.model.Issue.IssueIn;
import com.vasileva.task310.model.Issue.IssueOut;
import com.vasileva.task310.model.tag.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IssueDto {

    @Mapping(target = "writerId", source = "writer.id")
    @Mapping(source = "tags", target = "tags", qualifiedByName = "mapTagsToStrings")
    IssueOut out(Issue issue);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "writer", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "tags", ignore = true)
    Issue in(IssueIn issueIn);

    @Named("mapTagsToStrings")
    default Set<String> mapTagsToStrings(Set<Tag> tags) {
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writer", ignore = true)
    @Mapping(target = "tags", ignore = true)
    void update(@MappingTarget Issue issue, IssueIn issueIn);
}

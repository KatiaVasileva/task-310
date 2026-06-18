package com.vasileva.task310.mapper;

import com.vasileva.task310.model.tag.Tag;
import com.vasileva.task310.model.tag.TagIn;
import com.vasileva.task310.model.tag.TagOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagDto {

    TagOut out(Tag tag);

    @Mapping(target = "issues", ignore = true)
    Tag in(TagIn tagIn);
}

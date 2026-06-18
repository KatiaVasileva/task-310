package com.vasileva.task310.mapper;

import com.vasileva.task310.model.tag.Tag;
import com.vasileva.task310.model.tag.TagIn;
import com.vasileva.task310.model.tag.TagOut;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-11T17:55:46+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class TagDtoImpl implements TagDto {

    @Override
    public TagOut out(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagOut.TagOutBuilder tagOut = TagOut.builder();

        tagOut.id( tag.getId() );
        tagOut.name( tag.getName() );

        return tagOut.build();
    }

    @Override
    public Tag in(TagIn tagIn) {
        if ( tagIn == null ) {
            return null;
        }

        Tag.TagBuilder tag = Tag.builder();

        tag.id( tagIn.getId() );
        tag.name( tagIn.getName() );

        return tag.build();
    }
}

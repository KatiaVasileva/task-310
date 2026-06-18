package com.vasileva.task310.mapper;

import com.vasileva.task310.model.writer.Writer;
import com.vasileva.task310.model.writer.WriterIn;
import com.vasileva.task310.model.writer.WriterOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WriterDto {

    WriterOut out(Writer writer);

    @Mapping(target = "issues", ignore = true)
    Writer in(WriterIn writerIn);
}


package com.vasileva.task310.mapper;

import com.vasileva.task310.model.writer.Writer;
import com.vasileva.task310.model.writer.WriterIn;
import com.vasileva.task310.model.writer.WriterOut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WriterDto {

    WriterOut out(Writer writer);

    Writer in(WriterIn writerIn);
}


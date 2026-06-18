package com.vasileva.task310.mapper;

import com.vasileva.task310.model.writer.Writer;
import com.vasileva.task310.model.writer.WriterIn;
import com.vasileva.task310.model.writer.WriterOut;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-14T20:12:39+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class WriterDtoImpl implements WriterDto {

    @Override
    public WriterOut out(Writer writer) {
        if ( writer == null ) {
            return null;
        }

        WriterOut.WriterOutBuilder writerOut = WriterOut.builder();

        writerOut.id( writer.getId() );
        writerOut.login( writer.getLogin() );
        writerOut.firstname( writer.getFirstname() );
        writerOut.lastname( writer.getLastname() );

        return writerOut.build();
    }

    @Override
    public Writer in(WriterIn writerIn) {
        if ( writerIn == null ) {
            return null;
        }

        Writer.WriterBuilder writer = Writer.builder();

        writer.id( writerIn.getId() );
        writer.login( writerIn.getLogin() );
        writer.password( writerIn.getPassword() );
        writer.firstname( writerIn.getFirstname() );
        writer.lastname( writerIn.getLastname() );

        return writer.build();
    }
}

package com.vasileva.task310.service;

import com.vasileva.task310.mapper.WriterDto;
import com.vasileva.task310.model.writer.Writer;
import com.vasileva.task310.model.writer.WriterIn;
import com.vasileva.task310.model.writer.WriterOut;
import com.vasileva.task310.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WriterService {

    private final Repo<Writer> writerRepo;
    private final WriterDto mapper;

    public List<WriterOut> getAll() {
        return writerRepo
                .getAll()
                .map(mapper::out)
                .toList();
    }

    public WriterOut get(Long id) {
        return writerRepo
                .get(id)
                .map(mapper::out)
                .orElseThrow(() -> new RuntimeException("Writer not found"));
    }

    public WriterOut create(WriterIn writerIn) {
        return writerRepo
                .create(mapper.in(writerIn))
                .map(mapper::out)
                .orElseThrow();
    }

    public WriterOut update(WriterIn writerIn) {
        return writerRepo
                .update(mapper.in(writerIn))
                .map(mapper::out)
                .orElseThrow(() -> new RuntimeException("Writer not found"));
    }

    public boolean delete(Long id) {
        return writerRepo.delete(id);
    }
}

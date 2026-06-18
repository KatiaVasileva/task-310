package com.vasileva.task310.service;

import com.vasileva.task310.mapper.WriterDto;
import com.vasileva.task310.model.writer.Writer;
import com.vasileva.task310.model.writer.WriterIn;
import com.vasileva.task310.model.writer.WriterOut;
import com.vasileva.task310.repository.WriterRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class WriterService {

    private final WriterRepository writerRepository;
    private final WriterDto mapper;

    public List<WriterOut> getAll() {
        return writerRepository
                .findAll()
                .stream()
                .map(mapper::out)
                .toList();
    }

    public WriterOut get(Long id) {
        return writerRepository
                .findById(id)
                .map(mapper::out)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Writer with id=%d not found", id)));
    }

    public WriterOut create(WriterIn writerIn) {
        Writer writerFromDB = writerRepository.findByLogin(writerIn.getLogin());
        if (writerFromDB != null) {
            throw new DataIntegrityViolationException("Writer already exists");
        }
        return mapper.out(writerRepository.save(mapper.in(writerIn)));
    }

    public WriterOut update(WriterIn writerIn) {
        Writer updatedWriter = writerRepository.findById(writerIn.getId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Writer with id=%d not found", writerIn.getId())));

        updatedWriter.setFirstname(writerIn.getFirstname());
        updatedWriter.setLastname(writerIn.getLastname());
        updatedWriter.setLogin(writerIn.getLogin());
        updatedWriter.setPassword(writerIn.getPassword());
        return mapper.out(writerRepository.save(updatedWriter));
    }

    public void delete(Long id) {
        if(!writerRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Writer with id=%d not found", id));
        }
        writerRepository.deleteById(id);
    }
}

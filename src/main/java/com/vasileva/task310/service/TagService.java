package com.vasileva.task310.service;

import com.vasileva.task310.mapper.TagDto;
import com.vasileva.task310.model.tag.Tag;
import com.vasileva.task310.model.tag.TagIn;
import com.vasileva.task310.model.tag.TagOut;
import com.vasileva.task310.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagDto mapper;

    public List<TagOut> getAll() {
        return tagRepository
                .findAll()
                .stream()
                .map(mapper::out)
                .toList();
    }

    public TagOut get(Long id) {
        return tagRepository
                .findById(id)
                .map(mapper::out)
                .orElseThrow(() -> new NoSuchElementException(String.format("Tag not found with id %d", id)));
    }

    public TagOut create(TagIn tagIn) {
        return mapper.out(tagRepository
                .save(mapper.in(tagIn)));
    }

    public TagOut update(TagIn tagIn) {
        Tag tagToUpdate = tagRepository.findById(tagIn.getId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Tag not found with id %d", tagIn.getId())));
        tagToUpdate.setName(tagIn.getName());
        return mapper.out(tagRepository
                .save(tagToUpdate));
    }

    public void delete(Long id) {
        if(!tagRepository.existsById(id)) {
            throw new NoSuchElementException("Tag not found with id=" + id);
        }
        tagRepository.deleteById(id);
    }
}

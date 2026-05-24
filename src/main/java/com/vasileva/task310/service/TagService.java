package com.vasileva.task310.service;

import com.vasileva.task310.mapper.TagDto;
import com.vasileva.task310.model.tag.Tag;
import com.vasileva.task310.model.tag.TagIn;
import com.vasileva.task310.model.tag.TagOut;
import com.vasileva.task310.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagService {
    private final Repo<Tag> repoImpl;
    private final TagDto mapper;

    public List<TagOut> getAll() {
        return repoImpl
                .getAll()
                .map(mapper::out)
                .toList();
    }

    public TagOut get(Long id) {
        return repoImpl
                .get(id)
                .map(mapper::out)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    public TagOut create(TagIn tagIn) {
        return repoImpl
                .create(mapper.in(tagIn))
                .map(mapper::out)
                .orElseThrow();
    }

    public TagOut update(TagIn tagIn) {
        return repoImpl
                .update(mapper.in(tagIn))
                .map(mapper::out)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    public boolean delete(Long id) {
        return repoImpl.delete(id);
    }
}

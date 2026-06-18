package com.vasileva.task310.service;

import java.util.List;

public interface RestService<Q,A> {
    List<A> getAll();

    A get(long id);

    A create(Q input);

    A update(Q input);

    boolean delete(Long id);
}

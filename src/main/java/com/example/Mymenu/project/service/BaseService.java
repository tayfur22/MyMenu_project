package com.example.Mymenu.project.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, E> {
    T save(T entity);

    void deleteById(E id);

    List<T> findAll();

    T findById(E id);

    T update(T entity, E id);
}

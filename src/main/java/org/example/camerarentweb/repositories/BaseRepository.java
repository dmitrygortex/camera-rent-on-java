package org.example.camerarentweb.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    Optional<T> findById(int id);

    T save(T entity);

    void softDeleteById(int id);

    // с пагинацией
    List<T> findAll(int page, int size);
}


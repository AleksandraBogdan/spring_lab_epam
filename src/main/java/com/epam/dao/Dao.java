package com.epam.dao;

import java.util.Optional;

public interface Dao<T> {
    boolean save(T item);

    Optional<T> findById(int id);

    Optional<T> update(int id, T item);

    void deleteById(int id);
}

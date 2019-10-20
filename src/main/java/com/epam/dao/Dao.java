package com.epam.dao;

public interface Dao<T> {
    void save(T item);

    T findById(long id);

    void update(long id, T item);

    void deleteById(long id);
}

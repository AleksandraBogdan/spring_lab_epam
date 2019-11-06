package com.epam.dao;

public interface Dao<T> {
    void save(T item);

    T findById(int id);

    void update(int id, T item);

    void deleteById(int id);
}

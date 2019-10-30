package com.epam.dao;

import com.epam.model.Task;

public interface Dao<T> {
    boolean save(T item);

    T findById(int id);

    void update(int id, T item);

    void deleteById(int id);
}

package ru.magazine.dao;

import java.util.List;

/**
 * Created by petka on 28.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public interface GenericDao<T> {
    void create(T object);
    void update(T object);
    void delete(T object);
    T getById(Long id);
    T getByCell(String nameCell);
    List<T> getAll();
    List<T> getAll(String where);
    void setOrder(String order);
}

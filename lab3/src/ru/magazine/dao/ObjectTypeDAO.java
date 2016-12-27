package ru.magazine.dao;

import ru.magazine.entity.NCObjectType;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by petka on 30.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public interface ObjectTypeDAO {
    String insert(NCObjectType objectType);
    void update(NCObjectType objectType);
    void delete(NCObjectType objectType);
    NCObjectType getById(String uuid) throws SQLException;
    NCObjectType getByName(String name);
    NCObjectType getParent(String parentUUID);
    List<NCObjectType> getAll() throws SQLException;
    List<NCObjectType> getChildren(NCObjectType objectType);
    List<NCObjectType> getAllParent(NCObjectType objectType);
}

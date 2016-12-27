package ru.magazine.dao;

import ru.magazine.entity.NCObject;
import ru.magazine.entity.NCObjectType;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by petka on 30.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public interface ObjectDAO {
    String insert(NCObject object);
    void update(NCObject object);
    void delete(NCObject object);
    NCObject getById(String uuid);
    NCObject getByName(String name);
    NCObject getParent(String parentUUID);
    List<NCObject> getAll(NCObject object) throws SQLException;
    List<NCObject> getChildren(NCObject object);
    List<NCObject> getParentList(NCObject object);
    List<NCObject> getByObjectType(NCObjectType objectType);
}

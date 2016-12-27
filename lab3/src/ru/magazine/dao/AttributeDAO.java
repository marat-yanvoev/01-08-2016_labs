package ru.magazine.dao;

import ru.magazine.entity.NCAttribute;

import java.util.List;

/**
 * Created by petka on 30.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public interface AttributeDAO {
    String insert(NCAttribute attribute);
    void update(NCAttribute attribute);
    void delete(NCAttribute attribute);
    NCAttribute getById(String uuid);
    NCAttribute getByName(String name);
    List<NCAttribute> getByObjectType(String objectTypeUUID);
}

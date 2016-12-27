package ru.magazine.dao.Impl.Mysql;

import ru.magazine.dao.AttributeDAO;
import ru.magazine.entity.NCAttribute;

import java.util.List;

/**
 * Created by petka on 01.12.2016.
 *
 * @author Evgeniy Tupikov
 */
public class MysqlAttributeDAO implements AttributeDAO {
    @Override
    public String insert(NCAttribute attribute) {
        return null;
    }

    @Override
    public void update(NCAttribute attribute) {

    }

    @Override
    public void delete(NCAttribute attribute) {

    }

    @Override
    public NCAttribute getById(String uuid) {
        return null;
    }

    @Override
    public NCAttribute getByName(String name) {
        return null;
    }

    @Override
    public List<NCAttribute> getByObjectType(String objectTypeUUID) {
        return null;
    }
}

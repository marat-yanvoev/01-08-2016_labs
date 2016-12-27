package ru.magazine.dao.Impl.Mysql;

import ru.magazine.dao.ParamDAO;
import ru.magazine.entity.NCAttribute;
import ru.magazine.entity.NCObject;
import ru.magazine.entity.NCParam;

import java.util.List;

/**
 * Created by petka on 01.12.2016.
 *
 * @author Evgeniy Tupikov
 */
public class MysqlParamDAO implements ParamDAO {
    @Override
    public void insert(NCObject object, NCAttribute attribute, Object value) {

    }

    @Override
    public void update(NCObject object, NCAttribute attribute, Object value) {

    }

    @Override
    public NCParam getByObjectId(NCObject object) {
        return null;
    }

    @Override
    public List<NCObject> getByValue(NCAttribute attribute, Object value) {
        return null;
    }
}

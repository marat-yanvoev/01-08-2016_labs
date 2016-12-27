package ru.magazine.dao;

import ru.magazine.entity.NCAttribute;
import ru.magazine.entity.NCObject;
import ru.magazine.entity.NCParam;

import java.util.List;

/**
 * Created by petka on 30.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public interface ParamDAO {
    void insert(NCObject object, NCAttribute attribute, Object value);
    void update(NCObject object, NCAttribute attribute, Object value);
    NCParam getByObjectId(NCObject object);
    List<NCObject> getByValue(NCAttribute attribute, Object value);
}

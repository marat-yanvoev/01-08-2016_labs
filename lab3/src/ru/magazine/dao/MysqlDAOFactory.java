package ru.magazine.dao;

import ru.magazine.dao.Impl.Mysql.MysqlAttributeDAO;
import ru.magazine.dao.Impl.Mysql.MysqlObjectDAO;
import ru.magazine.dao.Impl.Mysql.MysqlObjectTypeDAO;
import ru.magazine.dao.Impl.Mysql.MysqlParamDAO;

/**
 * Created by petka on 30.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class MysqlDAOFactory extends DAOFactory {

    @Override
    public ObjectDAO getObjectDAO() {
        return new MysqlObjectDAO();
    }

    @Override
    public ObjectTypeDAO getObjectTypeDAO() {
        return new MysqlObjectTypeDAO();
    }

    @Override
    public AttributeDAO getAttributeDAO() {
        return new MysqlAttributeDAO();
    }

    @Override
    public ParamDAO getParamDAO() {
        return new MysqlParamDAO();
    }
}

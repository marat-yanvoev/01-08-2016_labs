package ru.magazine.dao;

import ru.magazine.Database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by petka on 30.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public abstract class DAOFactory {
    public final static int MYSQL = 1;

    protected Connection connection;

    public static DAOFactory getDaoFactory(int witchFactory) {
        switch (witchFactory) {
            case MYSQL:
                return new MysqlDAOFactory();
            default:
                return null;
        }
    }

    protected Connection getConnection() {
        try {
            Database.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void connectionClose() {
        Database.getInstance().close();
    }

    public abstract ObjectDAO getObjectDAO();
    public abstract ObjectTypeDAO getObjectTypeDAO();
    public abstract AttributeDAO getAttributeDAO();
    public abstract ParamDAO getParamDAO();
}

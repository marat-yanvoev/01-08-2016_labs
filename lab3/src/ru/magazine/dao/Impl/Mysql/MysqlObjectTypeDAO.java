package ru.magazine.dao.Impl.Mysql;

import ru.magazine.dao.ObjectTypeDAO;
import ru.magazine.Database;
import ru.magazine.entity.NCObjectType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by petka on 30.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class MysqlObjectTypeDAO implements ObjectTypeDAO{

    public final static String TABLE = "nc_object_type";

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MysqlObjectTypeDAO() {
    }

    @Override
    public String insert(NCObjectType objectType) {
        objectType.setId(UUID.randomUUID().toString());
        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO " + MysqlObjectTypeDAO.TABLE +
                    " (object_type_id, object_type_name, parent_id)" +
                    " VALUES (?, ?, ?)");
            preparedStatement.setString(1, objectType.getId());
            preparedStatement.setString(2, objectType.getName());
            preparedStatement.setString(3, objectType.getParentId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return objectType.getId();
    }

    @Override
    public void update(NCObjectType objectType) {

    }

    @Override
    public void delete(NCObjectType objectType) {

    }

    @Override
    public NCObjectType getById(String uuid) throws SQLException {
        connection = Database.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM " + MysqlObjectTypeDAO.TABLE + " WHERE object_type_id = " + uuid);
        preparedStatement.execute();
        ResultSet rs = preparedStatement.getResultSet();
        NCObjectType ncObjectType = new NCObjectType(rs.getString("object_type_id"), rs.getString("object_type_name"),
                rs.getString("parent_id"));
        connection.close();
        return ncObjectType;
    }

    @Override
    public NCObjectType getByName(String name) {
        return null;
    }

    @Override
    public NCObjectType getParent(String parentUUID) {
        return null;
    }

    @Override
    public List<NCObjectType> getAll() throws SQLException {
        connection = Database.getInstance().getConnection();
        List<NCObjectType> ncObjectTypeList = new ArrayList<NCObjectType>();
        preparedStatement = connection.prepareStatement("SELECT * FROM " + MysqlObjectTypeDAO.TABLE);
        preparedStatement.execute();
        ResultSet rs = preparedStatement.getResultSet();
        while (rs.next()) {
            ncObjectTypeList.add(new NCObjectType(
                    rs.getString("object_type_id"),
                    rs.getString("object_type_name"),
                    rs.getString("parent_id")
            ));
            System.out.println(ncObjectTypeList.toString());
        }
        connection.close();
        return ncObjectTypeList;
    }

    @Override
    public List<NCObjectType> getChildren(NCObjectType objectType) {
        return null;
    }

    @Override
    public List<NCObjectType> getAllParent(NCObjectType objectType) {
        return null;
    }
}

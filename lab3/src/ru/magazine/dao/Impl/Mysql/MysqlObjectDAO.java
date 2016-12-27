package ru.magazine.dao.Impl.Mysql;

import ru.magazine.dao.ObjectDAO;
import ru.magazine.Database;
import ru.magazine.entity.NCObject;
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
public class MysqlObjectDAO implements ObjectDAO {

    public final static String TABLE = "nc_object";

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MysqlObjectDAO() {
        try {
            connection = Database.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String insert(NCObject object) {
        object.setId(UUID.randomUUID().toString());
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO " + MysqlObjectDAO.TABLE +
                    " (object_id, object_name, parent_id, object_type)" +
                    " VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, object.getId());
            preparedStatement.setString(2, object.getName());
            preparedStatement.setString(3, object.getParentId());
            preparedStatement.setString(4, object.getType());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return object.getId();
    }

    @Override
    public void update(NCObject object) {

    }

    @Override
    public void delete(NCObject object) {

    }

    @Override
    public NCObject getById(String uuid) {
        return null;
    }

    @Override
    public NCObject getByName(String name) {
        return null;
    }

    @Override
    public NCObject getParent(String parentUUID) {
        return null;
    }

    @Override
    public List<NCObject> getAll(NCObject object) throws SQLException {
        List<NCObject> ncObjectList = new ArrayList<NCObject>();
        preparedStatement = connection.prepareStatement("SELECT * FROM " + MysqlObjectDAO.TABLE);
        preparedStatement.execute();
        ResultSet rs = preparedStatement.getResultSet();
        while (rs.next()) {
            ncObjectList.add(new NCObject(rs.getString("object_id"), rs.getString("object_name"),
                    rs.getString("parent_id"), rs.getString("object_type")));
        }

        return ncObjectList;
    }

    @Override
    public List<NCObject> getChildren(NCObject object) {
        return null;
    }

    @Override
    public List<NCObject> getParentList(NCObject object) {
        return null;
    }

    @Override
    public List<NCObject> getByObjectType(NCObjectType objectType) {
        return null;
    }
}

package ru.magazine.dao.Impl.Mysql;

import ru.magazine.Database;
import ru.magazine.dao.AttributeDAO;
import ru.magazine.entity.NCAttribute;
import ru.magazine.util.ConvertToAttributeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petka on 01.12.2016.
 *
 * @author Evgeniy Tupikov
 */
public class MysqlAttributeDAO implements AttributeDAO {

    public final static String TABLE = "nc_attribute";

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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
    public List<NCAttribute> getByObjectType(String objectTypeUUID) throws SQLException {
        List<NCAttribute> ncAttributeList = new ArrayList<NCAttribute>();
        connection = Database.getInstance().getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE + " WHERE object_type = ?");
        preparedStatement.setString(1, objectTypeUUID);
        preparedStatement.execute();
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            ncAttributeList.add(new NCAttribute(
                    resultSet.getString("attribute_id"),
                    resultSet.getString("attribute_name"),
                    resultSet.getString("object_type"),
                    resultSet.getInt("order_id"),
                    ConvertToAttributeType.getAttributeType(resultSet.getString("type")),
                    resultSet.getBoolean("multiple")
            ));
        }
        connection.close();
        return ncAttributeList;
    }
}

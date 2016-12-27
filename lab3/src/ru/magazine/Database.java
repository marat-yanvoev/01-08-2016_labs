package ru.magazine;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by petka on 30.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class Database {
    private static Database instance;

    private Context context;
    private DataSource dataSource;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private Database() {
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("jdbc/MySQLDataSource");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close(){}
}

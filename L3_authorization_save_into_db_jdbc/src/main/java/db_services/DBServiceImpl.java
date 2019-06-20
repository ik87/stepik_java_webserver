package db_services;

import base.DbServices;
import base.UserProfile;
import db_services.dao.UserDAO;
import db_services.data_set.UserDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;

/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class DBServiceImpl implements DbServices {


    private final Connection connection;

    @Override
    public long addUser(UserProfile userProfile) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            dao.insertUser(userProfile);
            connection.commit();
            return dao.getUser(userProfile.getLogin());
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {

            }
        }
    }

    @Override
    public UserProfile getUser(String login) throws DBException {
        try {
            UserDAO dao = new UserDAO(connection);
            dao.getUser(login);
            UserDataSet dataSet = dao.get(login);
            return new UserProfile(dataSet.getLogin(), dataSet.getPassword());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void create() throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            dao.createTable();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {

            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {

            }
        }

    }

    public DBServiceImpl() {
        this.connection = getH2Connection();
    }

    public void cleanUp() throws DBException {
        UserDAO dao = new UserDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

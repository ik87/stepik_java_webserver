package db_services.dao;

import base.UserProfile;
import db_services.data_set.UserDataSet;
import db_services.executer.Executer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class UserDAO {
    private Executer executer;

    public UserDAO(Connection connection) {
        this.executer = new Executer(connection);
    }

    public UserDataSet get(String login) throws SQLException {
        String query = "SELECT id, login, password FROM users WHERE login = ?";
        return executer.execQuery(query, psmt -> {
            psmt.setString(1, login);
            ResultSet rs = psmt.executeQuery();
            rs.next();
            return new UserDataSet(rs.getLong(1), rs.getString(2), rs.getString(3));
        });
    }

    public long getUser(String login) throws SQLException {
        String query = "SELECT id FROM users WHERE login = ?";
        return executer.execQuery(query, psmt -> {
            psmt.setString(1, login);
            ResultSet rs = psmt.executeQuery();
            rs.next();
            return rs.getLong(1);
        });
    }

    public Long insertUser(UserProfile userProfile) throws SQLException {
        String query = "INSERT INTO users (login, password) values (?, ?)";
        return executer.execQuery(query, psmt -> {
            Long id = null;
            psmt.setString(1, userProfile.getLogin());
            psmt.setString(2, userProfile.getPassword());
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getLong(1);
            }
            return id;
        });
    }

    public void createTable() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS users");
        sb.append("(");
        sb.append("id bigint auto_increment,");
        sb.append("login varchar(256),");
        sb.append("password varchar(256),");
        sb.append("primary key (id)");
        sb.append(");");
        executer.execUpdate(sb.toString());
    }

    public void dropTable() throws SQLException {
        String query = "DROP TABLE users";
        executer.execUpdate(query);
    }
}

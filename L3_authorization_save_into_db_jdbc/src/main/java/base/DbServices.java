package base;

import db_services.DBException;

public interface DbServices {
    long addUser(UserProfile userProfile) throws DBException;

    UserProfile getUser(String login) throws DBException;

    void create() throws DBException;

    void cleanUp() throws DBException;
}

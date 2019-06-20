package base;

import db_services.DBException;

public interface DbServices {
    Long addUser(UserProfile userProfile) throws DBException;

    UserProfile getUser(String login) throws DBException;
}

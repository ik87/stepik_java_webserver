package server_services;

import base.AccountService;
import base.DbServices;
import base.UserProfile;
import db_services.DBException;

/**
 * Defined service that responsibility for storage users profile
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class AccountServiceImpl implements AccountService {
    private DbServices dbServices;

    public AccountServiceImpl(DbServices dbServices) {
        this.dbServices = dbServices;
    }

    @Override
    public boolean signUp(String login, String password) {
        boolean added = false;
        try {
            if (login != null && password != null) {
                if (!signIn(login, password)) {
                    UserProfile userProfile = new UserProfile(login, password);
                    dbServices.addUser(userProfile);
                    added = true;
                }
            }
        } catch (DBException e) {

        }
        return added;

    }

    @Override
    public boolean signIn(String login, String password) {
        boolean exist = false;
        try {
            if (login != null && password != null) {
                UserProfile userProfile = dbServices.getUser(login);
                if (userProfile != null) {
                    exist = userProfile.getPassword().equals(password);
                }
            }
        } catch (DBException e) {

        }
        return exist;
    }

}

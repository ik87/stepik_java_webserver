package db_services;

import base.DbServices;
import base.UserProfile;
import db_services.dao.UserDAO;
import db_services.data_set.UserDataSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class DBServiceImpl implements DbServices {
    private final String hibernate_show_sql = "true";
    private final String hibernate_hbm2ddl_auto = "update";
    private SessionFactory sessionFactory;


    public DBServiceImpl() {
        Configuration configuration = getH2Configuration();
        System.out.println(configuration.getProperties());
        this.sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public Long addUser(UserProfile userProfile) throws DBException {
        Long result;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDAO userDAO = new UserDAO(session);
            result = userDAO.insertUser(userProfile);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public UserProfile getUser(String login) throws DBException {
        UserProfile userProfile = null;
        try {
            Session session = sessionFactory.openSession();
            UserDAO userDAO = new UserDAO(session);
            UserDataSet userDataSet = userDAO.get(login);
            session.close();
            if (userDataSet != null) {
                userProfile = new UserProfile(userDataSet.getLogin(), userDataSet.getPassword());
            }
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return userProfile;
    }


    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}

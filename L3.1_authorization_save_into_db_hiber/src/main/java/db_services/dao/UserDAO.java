package db_services.dao;

import base.UserProfile;
import db_services.data_set.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
@SuppressWarnings("UnusedDeclaration")
public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserDataSet get(String login) {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return  (UserDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public Long getUserId(String login) {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return ((UserDataSet)criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
    }

    public Long insertUser(UserProfile userProfile)  {
      return (Long)session.save(new UserDataSet(userProfile.getLogin(), userProfile.getPassword()));
    }
}

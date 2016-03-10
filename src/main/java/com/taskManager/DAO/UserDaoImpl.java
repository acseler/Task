package com.taskManager.DAO;

import com.taskManager.DAO.Entities.User;
import com.taskManager.DAO.Interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by boduill on 09.03.16.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addUser(User user) {
        hibernateTemplate.persist(user);
    }

    @Override
    public User getUser(String login) {
        return hibernateTemplate.findByExample(getUserExample(login)).get(0);
    }

    private User getUserExample(String login) {
        User user = new User();
        user.setLogin(login);
        return user;
    }
}

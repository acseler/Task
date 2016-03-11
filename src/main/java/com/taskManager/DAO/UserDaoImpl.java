package com.taskManager.DAO;

import com.taskManager.DAO.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by boduill on 09.03.16.
 */
@Repository
public class UserDaoImpl {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void addUser(User user) {
        hibernateTemplate.persist(user);
    }

    @Transactional
    public User getUser(String login) {
        List<User> users = hibernateTemplate.findByExample(getUserExample(login));
        return users.isEmpty() ? null : users.get(0);
    }

    private User getUserExample(String login) {
        User user = new User();
        user.setLogin(login);
        return user;
    }
}

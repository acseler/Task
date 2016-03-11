package com.taskManager.Service;

import com.taskManager.DAO.Entities.User;
import com.taskManager.DAO.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by boduill on 11.03.16.
 */
@Service
public class LoginService {

    @Autowired
    private UserDaoImpl userDao;

    public User checkUser(User user) {
        User userOut = userDao.getUser(user.getLogin());
        if (userOut == null) {
            return null;
        }
        if (!userOut.getPassword().equals(user.getPassword())) {
            return null;
        }
        userOut.setPassword(null);
        return userOut;
    }
}

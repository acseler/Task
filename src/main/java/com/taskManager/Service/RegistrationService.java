package com.taskManager.Service;

import com.taskManager.DAO.Entities.User;
import com.taskManager.DAO.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by boduill on 12.03.16.
 */
@Service
public class RegistrationService {

    @Autowired
    private UserDaoImpl userDao;

    public boolean checkLogin(User user) {
        if (null == userDao.getUser(user.getLogin())) {
            userDao.addUser(user);
            return true;
        }
        return false;
    }
}

package com.taskManager.DAO.Interfaces;

import com.taskManager.DAO.Entities.User;

/**
 * Created by boduill on 09.03.16.
 */
public interface UserDao {

    void addUser(User user);
    User getUser(String login);

}

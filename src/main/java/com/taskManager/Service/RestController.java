package com.taskManager.Service;

import com.taskManager.DAO.Entities.Project;
import com.taskManager.DAO.Entities.Task;
import com.taskManager.DAO.Entities.User;
import com.taskManager.DAO.Interfaces.TaskDao;
import com.taskManager.DAO.ProjectDaoImpl;
import com.taskManager.DAO.TaskDaoImpl;
import com.taskManager.DAO.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by boduill on 09.03.16.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private ProjectDaoImpl projectDao;

    @Autowired
    private TaskDaoImpl taskDao;

//    @RequestMapping(value = "/db", method = RequestMethod.GET)
//    ResponseEntity<List<Task>> getProjects() {
////        return new ResponseEntity<User>(userDao.getUser("petr"), HttpStatus.CREATED);
////        return new ResponseEntity<List<Project>>(projectDao.getProjects("ivan"), HttpStatus.CREATED);
//        return new ResponseEntity<List<Task>>(taskDao.getTasks("ivan", "Create project"), HttpStatus.CREATED);
//    }

}

package com.taskManager.Service;

import com.taskManager.DAO.Entities.Project;
import com.taskManager.DAO.Entities.Task;
import com.taskManager.DAO.Entities.User;
import com.taskManager.DAO.Enums.PriorityEnum;
import com.taskManager.DAO.Enums.TaskStatus;
import com.taskManager.DAO.ProjectDaoImpl;
import com.taskManager.DAO.TaskDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by boduill on 09.03.16.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {

    @Autowired
    private ProjectDaoImpl projectDao;

    @Autowired
    private TaskDaoImpl taskDao;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "{user}/projects", method = RequestMethod.GET)
    ResponseEntity<List<Project>> getProjects(@PathVariable String user) {
        List<Project> projects = projectDao.getProjects(user);
        for (Project p : projects) {
            p.setTasks(taskDao.getTasks(p.getId()));
        }
        return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
    }

    @RequestMapping(value = "{user}/project/task/add", method = RequestMethod.POST)
    ResponseEntity<Void> createTask(@RequestBody Task task) {
        task.setCreateDate(new Date());
        task.setPriority(PriorityEnum.LOW.getValue());
        task.setStatus(TaskStatus.IN_PROCESS.getValue());
        taskDao.addTask(task);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "{user}/project/task/update", method = RequestMethod.POST)
    ResponseEntity<Void> updateTask(@RequestBody Task task) {
        taskDao.updateTask(task);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "{user}/project/task/delete", method = RequestMethod.POST)
    ResponseEntity<Void> deleteTask(@RequestBody Task task) {
        taskDao.deleteTask(task);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/project/create/{projectName}", method = RequestMethod.POST)
    ResponseEntity<Void> createProject(@PathVariable String userId, @PathVariable String projectName) {
        Project project = new Project();
        project.setUser(Long.parseLong(userId));
        project.setName(projectName);
        projectDao.addProject(project);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "{user}/project/update", method = RequestMethod.POST)
    ResponseEntity<Void> updateProject(@RequestBody Project project) {
        projectDao.updateProject(project);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "{user}/project/delete", method = RequestMethod.POST)
    ResponseEntity<Void> deleteProject(@RequestBody Project project) {
        projectDao.deleteProject(project);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    ResponseEntity<User> doLogin(@RequestBody User user) {
        return new ResponseEntity<User>(loginService.checkUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    ResponseEntity<Boolean> addUser(@RequestBody User user) {
        return new ResponseEntity<Boolean>(registrationService.checkLogin(user), HttpStatus.OK);
    }




}

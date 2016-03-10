package com.taskManager.DAO.Interfaces;

import com.taskManager.DAO.Entities.Project;
import com.taskManager.DAO.Entities.Task;

import java.util.List;

/**
 * Created by boduill on 09.03.16.
 */
public interface TaskDao {

    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
    List<Task> getTasks(String user, String project);

}

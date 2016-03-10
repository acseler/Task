package com.taskManager.DAO.Interfaces;

import com.taskManager.DAO.Entities.Project;
import com.taskManager.DAO.Entities.User;

import java.util.List;

/**
 * Created by boduill on 09.03.16.
 */
public interface ProjectDao {

    void addProject(Project project);
    void updateProject(Project project);
    void deleteProject(Project project);
    List<Project> getProjects(String user);
    Project getProject(String user, String project);
}

package com.taskManager.DAO;

import com.taskManager.DAO.Entities.Project;
import com.taskManager.DAO.Entities.Task;
import com.taskManager.DAO.Interfaces.TaskDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by boduill on 10.03.16.
 */
@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private ProjectDaoImpl projectDao;

    @Override
    public void addTask(Task task) {
        hibernateTemplate.persist(task);
    }

    @Override
    public void updateTask(Task task) {
        hibernateTemplate.update(task);
    }

    @Override
    public void deleteTask(Task task) {
        hibernateTemplate.delete(task);
    }

    @Override
    public List<Task> getTasks(String user, String project) {
        Project p = projectDao.getProject(user, project);
        List<Task> tasks = (List<Task>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Task.class).add(Restrictions.eq("project", p.getId())));
        return tasks;
    }
}

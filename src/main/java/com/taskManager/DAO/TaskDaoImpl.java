package com.taskManager.DAO;

import com.taskManager.DAO.Entities.Project;
import com.taskManager.DAO.Entities.Task;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by boduill on 10.03.16.
 */
@Repository
public class TaskDaoImpl {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private ProjectDaoImpl projectDao;

    @Transactional
    public void addTask(Task task) {
        hibernateTemplate.persist(task);
    }

    @Transactional
    public void updateTask(Task task) {
        hibernateTemplate.update(task);
    }

    @Transactional
    public void deleteTask(Task task) {
        hibernateTemplate.delete(task);
    }

    @Transactional
    public List<Task> getTasks(String user, String project) {
        Project p = projectDao.getProject(user, project);
        List<Task> tasks = (List<Task>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Task.class).add(Restrictions.eq("project", p.getId())));
        return tasks;
    }
}

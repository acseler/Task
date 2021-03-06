package com.taskManager.DAO;

import com.taskManager.DAO.Entities.Project;
import com.taskManager.DAO.Entities.Task;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
public class ProjectDaoImpl {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private TaskDaoImpl taskDao;

    @Transactional
    public void addProject(Project project) {
        hibernateTemplate.persist(project);
    }

    @Transactional
    public void updateProject(Project project) {
        hibernateTemplate.update(project);
    }

    @Transactional
    public void deleteProject(Project project) {
        List<Task> tasks = taskDao.getTasks(project.getId());
        hibernateTemplate.deleteAll(tasks);
        hibernateTemplate.delete(project);
    }

    @Transactional
    public List<Project> getProjects(String user) {
        long id = userDao.getUser(user).getId();
        return (List<Project>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Project.class).add(
                Restrictions.eq("user", id)).addOrder(Order.asc("name").ignoreCase()));
    }

    @Transactional
    public Project getProject(String user, String project) {
        long id = userDao.getUser(user).getId();
        return (Project) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Project.class).add(
                Restrictions.eq("name", project)).add(Restrictions.eq("user", id))).get(0);
    }
}

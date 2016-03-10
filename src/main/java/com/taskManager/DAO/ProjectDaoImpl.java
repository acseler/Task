package com.taskManager.DAO;

import com.taskManager.DAO.Entities.Project;
import com.taskManager.DAO.Entities.User;
import com.taskManager.DAO.Interfaces.ProjectDao;
import com.taskManager.DAO.Interfaces.UserDao;
import org.hibernate.Criteria;
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
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private UserDao userDao;

    @Override
    public void addProject(Project project) {
        hibernateTemplate.persist(project);
    }

    @Override
    public void updateProject(Project project) {
        hibernateTemplate.update(project);
    }

    @Override
    public void deleteProject(Project project) {
        hibernateTemplate.delete(project);
    }

    @Override
    public List<Project> getProjects(String user) {
        long id = userDao.getUser(user).getId();
        return (List<Project>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Project.class).add(
                Restrictions.eq("user", id)));
    }

    @Override
    public Project getProject(String user, String project) {
        long id = userDao.getUser(user).getId();
        return (Project) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Project.class).add(
                Restrictions.eq("name", project)).add(Restrictions.eq("user", id))).get(0);
    }
}

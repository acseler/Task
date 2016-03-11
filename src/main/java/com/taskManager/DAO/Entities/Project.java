package com.taskManager.DAO.Entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by boduill on 09.03.16.
 */
@Entity
@Table(name = "PROJECTS")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
    @TableGenerator(
            name = "idGenerator",
            table = "IDS",
            pkColumnName = "table_name",
            valueColumnName = "id_value",
            allocationSize = 100
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USER_ID")
    private Long user;

    @Transient
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}

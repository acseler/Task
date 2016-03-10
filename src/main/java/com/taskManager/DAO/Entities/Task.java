package com.taskManager.DAO.Entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by boduill on 09.03.16.
 */
@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEADLINE_DATE")
    private Date deadlineDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "project")
    private long project;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public long getProject() {
        return project;
    }

    public void setProject(long project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

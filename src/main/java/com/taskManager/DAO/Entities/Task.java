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

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEADLINE_DATE")
    private Date deadlineDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "project")
    private Long project;

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", deadlineDate=" + deadlineDate +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", project=" + project +
                '}';
    }
}

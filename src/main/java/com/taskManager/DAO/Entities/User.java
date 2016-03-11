package com.taskManager.DAO.Entities;

import javax.persistence.*;

/**
 * Created by boduill on 09.03.16.
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
    @TableGenerator(
            name = "idGenerator",
            table = "IDS",
            pkColumnName = "table_name",
            valueColumnName = "id_value",
            allocationSize = 100
    )
    private Long id;

    @Column(name = "LOGIN", unique = true)
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.college.management.command;

import com.college.management.model.User;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


public class RoleCommand {

    private Long id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    private Set<User> users = new HashSet<>();

    public RoleCommand(){}

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

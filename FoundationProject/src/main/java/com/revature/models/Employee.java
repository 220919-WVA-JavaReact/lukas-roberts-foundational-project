package com.revature.models;

import java.util.Objects;

public class Employee {
    // employee id
    private int id;
    private String first;
    private String last;
    private String username;
    private String password;
    private boolean manager;

    public Employee(int id, String first, String last, String username, String password, boolean manager) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        this.manager = manager;
    }

    public Employee(String first, String last, String username, String password) {
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        this.manager = false;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email='" + username + '\'' +
                ", password='" + password + '\'' +
                ", manager=" + manager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && manager == employee.manager && first.equals(employee.first) && last.equals(employee.last) && username.equals(employee.username) && password.equals(employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first, last, username, password, manager);
    }
}

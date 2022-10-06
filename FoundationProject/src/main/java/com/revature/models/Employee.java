package com.revature.models;

import java.util.Objects;

public class Employee {
    // employee id
    private int id;
    private String first;
    private String last;
    private String email;
    private String password;
    private boolean manager;

    public Employee(int id, String first, String last, String email, String password, boolean manager) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.manager = manager;
    }

    public Employee(String first, String last, String email, String password) {
        this.first = first;
        this.last = last;
        this.email = email;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", manager=" + manager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && manager == employee.manager && first.equals(employee.first) && last.equals(employee.last) && email.equals(employee.email) && password.equals(employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first, last, email, password, manager);
    }
}

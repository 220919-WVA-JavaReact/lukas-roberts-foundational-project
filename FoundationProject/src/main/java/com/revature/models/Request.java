package com.revature.models;

import java.util.Objects;

public class Request {
    // id reference
    private int id;
//    private int employeeId;
    private double price;
    private String description;
    private String approvalStatus = "Pending";
    private boolean completed = false;

    private Employee employee;

    public Request() {

    }

    public Request(double price, String description, Employee employee) {
        this.price = price;
        this.description = description;
        this.employee = employee;
    }

    public Request(int id, double price, String description, String approvalStatus, boolean completed, Employee employee) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.approvalStatus = approvalStatus;
        this.completed = completed;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "ReimbursementRequest{" +
                "employeeId=" + employee +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", approved=" + approvalStatus +
                ", completed=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request that = (Request) o;
        return Double.compare(that.price, price) == 0 && approvalStatus == that.approvalStatus && completed == that.completed && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, description, approvalStatus, completed);
    }
}

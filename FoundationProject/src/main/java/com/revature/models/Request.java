package com.revature.models;
import java.util.Objects;

public class Request {
    // id reference
    private int id;
    private Employee employee;
    private double price;
    private String description;
    private String approvalStatus;
    private boolean completed;

    private String type;

    public Request() {

    }

    public Request(Employee employee, double price, String description, String type ) {
        this.employee = employee;
        this.price = price;
        this.description = description;
        this.type = type;
    }

    public Request(int id, Employee employee, double price, String description, String type, String approvalStatus, boolean completed) {
        this.id = id;
        this.employee = employee;
        this.price = price;
        this.description = description;
        this.type = type;
        this.approvalStatus = approvalStatus;
        this.completed = completed;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "Request{" +
                "id=" + id +
                ", employee=" + employee +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", completed=" + completed +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id && Double.compare(request.price, price) == 0 && completed == request.completed && employee.equals(request.employee) && description.equals(request.description) && approvalStatus.equals(request.approvalStatus) && type.equals(request.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, price, description, approvalStatus, completed, type);
    }
}

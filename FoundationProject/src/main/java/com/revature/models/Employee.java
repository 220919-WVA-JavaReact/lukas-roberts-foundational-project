package com.revature.models;
import java.util.Objects;

public class Employee {
    private int id;
    private String first;
    private String last;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zip;
    private String username;
    private String password;
    private EmployeeType employeeLevel;

    public Employee(int id, String first, String last, String address1, String address2, String city, String state, int zip, String username, String password, EmployeeType employeeLevel) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.username = username;
        this.password = password;
        this.employeeLevel = employeeLevel;
    }

    public Employee(String first, String last, String address1, String address2, String city, String state, int zip, String username, String password) {
        this.first = first;
        this.last = last;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.username = username;
        this.password = password;
    }

    public Employee(int id, String first, String last, String username, EmployeeType employeeLevel) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.address1 = "Hidden";
        this.address2 = "Hidden";
        this.city = "Hidden";
        this.state = "Hidden";
        this.zip = 0;
        this.username = username;
        this.password = "Hidden";
        this.employeeLevel = employeeLevel;
    }

    public Employee() {
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
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

    public EmployeeType getEmployeeLevel() {
        return employeeLevel;
    }

    public void setEmployeeLevel(EmployeeType employeeLevel) {
        this.employeeLevel = employeeLevel;
    }

//    public String toJsonString() {
//        return "{\"id\":" + id + ", " +
//                "\"first\":\"" + first + "\", " +
//                "\"last\":\"" + last + "\", " +
//                "\"address1\":\"" + address1 + "\", " +
//                "\"address2\":\"" + address2 + "\", " +
//                "\"city\":\"" + city + "\", " +
//                "\"state\":\"" + state + "\", " +
//                "\"zip\":" + zip + ", " +
//                "\"username\":\"" + username + "\", " +
//                "\"password\":\"" + password + "\", " +
//                "\"employeeLevel\":\"" + employeeLevel + "\"" +
//        "}";
//    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", employeeLevel=" + employeeLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && zip == employee.zip && first.equals(employee.first) && last.equals(employee.last) && address1.equals(employee.address1) && address2.equals(employee.address2) && city.equals(employee.city) && state.equals(employee.state) && username.equals(employee.username) && password.equals(employee.password) && employeeLevel == employee.employeeLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first, last, address1, address2, city, state, zip, username, password, employeeLevel);
    }
}

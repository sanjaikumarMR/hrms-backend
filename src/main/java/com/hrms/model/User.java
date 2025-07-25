package com.hrms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users") // or "employees" if that's your collection
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String position;
    private String department;
    private String role; // Normal, HR, RM
    private double salary;
    private String dateOfJoining;
    private String status; // Active, Inactive, etc.
    private String address;
    private String dob;
    private String password;
    private String managerId;



    // Default constructor
    public User() {}

    // Constructor with common fields (can be extended)
    public User(String name, String email, String position, double salary) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.salary = salary;
    }
    
// Add getter and setter
public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(String dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    
public String getManagerId() {
    return managerId;
}

public void setManagerId(String managerId) {
    this.managerId = managerId;
}

}
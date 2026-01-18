package com.hostel.model;

public class Staff {

    private int id;
    private String username;
    private String password;
    private String department;

    
    public Staff() {
    }

    
    public Staff(int id, String username, String password, String department) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.department = department;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }
 
    public void setDepartment(String department) {
        this.department = department;
    }
}

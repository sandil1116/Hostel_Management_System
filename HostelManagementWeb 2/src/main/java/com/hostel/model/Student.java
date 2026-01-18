package com.hostel.model;

public class Student {

    private int id;
    private String username;
    private String password;
    private String roomNo;
    private String accountStatus;

    
    public Student() {}

    
    public Student(int id, String username, String password, String roomNo , String accountStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roomNo = roomNo;
        this.accountStatus = accountStatus;
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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }
}

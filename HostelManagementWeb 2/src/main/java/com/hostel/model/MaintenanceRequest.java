package com.hostel.model;

import java.sql.Timestamp;

public class MaintenanceRequest {

    private int id;
    private int studentId;
    private String studentUsername;
    private String roomNo;
    private String category;
    private String description;
    private String status;
    private Integer assignedStaffId;
    private String staffNote;
    private Timestamp createdAt;
    private String complaint;


    public MaintenanceRequest() {}

    public MaintenanceRequest(int id, String studentUsername, String roomNo, String category,
                              String description, String status, Integer assignedStaffId,
                              String staffNote, Timestamp createdAt) {
        this.id = id;
        this.studentUsername = studentUsername;
        this.roomNo = roomNo;
        this.category = category;
        this.description = description;
        this.status = status;
        this.assignedStaffId = assignedStaffId;
        this.staffNote = staffNote;
        this.createdAt = createdAt;
        
    }

    public int getId() 
    { return id; }
    
    public void setId(int id) 
    { this.id = id; }

    public String getStudentUsername() 
    { return studentUsername; }
    
    public void setStudentUsername(String studentUsername) 
    { this.studentUsername = studentUsername; }

    public String getRoomNo() 
    { return roomNo; }
    
    public void setRoomNo(String roomNo) 
    { this.roomNo = roomNo; }

    public String getCategory() 
    { return category; }
    
    public void setCategory(String category)
    { this.category = category; }

    public String getDescription() 
    { return description; }
    
    public void setDescription(String description)
    { this.description = description; }

    public String getStatus() 
    { return status; }
    
    public void setStatus(String status)
    { this.status = status; }

    public Integer getAssignedStaffId() 
    { return assignedStaffId; }
    
    public void setAssignedStaffId(Integer assignedStaffId)
    { this.assignedStaffId = assignedStaffId; }

    public String getStaffNote() 
    { return staffNote; }
    
    public void setStaffNote(String staffNote) 
    { this.staffNote = staffNote; }

    public Timestamp getCreatedAt() 
    { return createdAt; }
    
    public void setCreatedAt(Timestamp createdAt) 
    { this.createdAt = createdAt; }
    
    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

}

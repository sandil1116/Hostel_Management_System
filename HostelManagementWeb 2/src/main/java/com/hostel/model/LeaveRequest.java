package com.hostel.model;

import java.sql.Timestamp;

public class LeaveRequest {
    private int id;
    private int studentId;
    private String studentUsername; 
    private Timestamp fromDt;
    private Timestamp toDt;
    private String reason;
    private String destination;
    private String status; 
    private Timestamp createdAt;
    private Integer approvedBy;
    private Timestamp approvedAt;

    public LeaveRequest() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }

    public Timestamp getFromDt() { return fromDt; }
    public void setFromDt(Timestamp fromDt) { this.fromDt = fromDt; }

    public Timestamp getToDt() { return toDt; }
    public void setToDt(Timestamp toDt) { this.toDt = toDt; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Integer getApprovedBy() { return approvedBy; }
    public void setApprovedBy(Integer approvedBy) { this.approvedBy = approvedBy; }

    public Timestamp getApprovedAt() { return approvedAt; }
    public void setApprovedAt(Timestamp approvedAt) { this.approvedAt = approvedAt; }
}

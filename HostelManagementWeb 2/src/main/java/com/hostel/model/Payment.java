package com.hostel.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Payment {

    private int id;
    private int studentId;
    private String studentUsername;

    private double amount;
    private String paymentMethod;
    private String referenceNo;
    private Date paymentDate;
    private String proofPath;
    private String status;

    private Integer verifiedBy;
    private Timestamp verifiedAt;
    private Timestamp createdAt;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getReferenceNo() { return referenceNo; }
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public String getProofPath() { return proofPath; }
    public void setProofPath(String proofPath) { this.proofPath = proofPath; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getVerifiedBy() { return verifiedBy; }
    public void setVerifiedBy(Integer verifiedBy) { this.verifiedBy = verifiedBy; }

    public Timestamp getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(Timestamp verifiedAt) { this.verifiedAt = verifiedAt; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}

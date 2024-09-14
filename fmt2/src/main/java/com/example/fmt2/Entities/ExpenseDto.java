package com.example.fmt2.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ExpenseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;

    @NotNull(message = "UserID is required")
    @Column(nullable = false)
    private Long uid;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private String tharik;

    @NotEmpty(message = "Category is required")
    @Column(nullable = false)
    private String category;

    @NotEmpty(message = "Transaction Type is required")
    @Column(nullable = false)
    private String transType;

    @Size(min = 0, message = "Description of at least 10 characters")
    @Size(max = 500, message = "Description in about 500 characters")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Amount is required")
    @Column(nullable = false)
    private Long amt;

    // Getters and setters
    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTharik() {
        return tharik;
    }

    public void setTharik(String tharik) {
        this.tharik = tharik;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmt() {
        return amt;
    }

    public void setAmt(Long amt) {
        this.amt = amt;
    }
}

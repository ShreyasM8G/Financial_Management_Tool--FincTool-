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

public class IncomeDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;


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

    @Size(min = 0, message = "Description of at least 10 characters")
    @Size(max = 500, message = "Description in about 500 characters")
    @Column(nullable = true, length = 500)
    private String description;

    @NotNull(message = "Amount is required")
    @Column(nullable = false)
    private Long amt;

    // Getters and setters
    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmt() {return amt;}

    public void setAmt(Long amt) {
        this.amt = amt;
    }
}

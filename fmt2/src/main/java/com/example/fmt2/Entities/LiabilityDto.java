package com.example.fmt2.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LiabilityDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lid;

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
    private Integer period;

    @NotNull(message = "Amount is required")
    @Column(nullable = false)
    private Long amount;

    // Getters and setters
    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}

package com.example.fmt2.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AssetDto {

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @NotNull(message = "UserID is required")
    @Column(nullable = false)
    private Long uid;

    public String getTharik() {
        return tharik;
    }

    public void setTharik(String tharik) {
        this.tharik = tharik;
    }
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private String tharik;

    public Long getValuation() {
        return valuation;
    }

    public void setValuation(Long valuation) {
        this.valuation = valuation;
    }
    @Min(value = 1, message = "Valuation must be greater than or equal to 1")
    @Column(nullable = false)
    private Long valuation;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @NotEmpty(message = "Category is required")
    @Column(nullable = false)
    private String category;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Size(min = 0, message = "Description of at least 10 characters")
    @Size(max = 500, message = "Description in about 500 characters")
    @Column(nullable = true, length = 500)
    private String description;

}

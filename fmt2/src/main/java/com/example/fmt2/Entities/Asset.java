package com.example.fmt2.Entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "asset")
public class Asset {

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

    @Column(nullable = false)
    private Long uid;

    public Date getTharik() {
        return tharik;
    }

    public void setTharik(Date tharik) {
        this.tharik = tharik;
    }

    @Column(nullable = false)
    private Date tharik;

    public Long getValuation() {
        return valuation;
    }

    public void setValuation(Long valuation) {
        this.valuation = valuation;
    }

    @Column(nullable = false)
    private Long valuation;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(nullable = false)
    private String category;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = true, length = 500)
    private String description;

    // Getters and setters
}

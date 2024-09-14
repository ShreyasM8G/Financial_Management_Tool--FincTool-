package com.example.fmt2.Entities;

import jakarta.persistence.*;


import java.util.Date;


@Entity
@Table(name = "income")
public class Income {

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

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

    public Long getAmt() {
        return amt;
    }

    public void setAmt(Long amt) {
        this.amt = amt;
    }

    @Column(nullable = false)
    private Long amt;

    // Getters and setters
}

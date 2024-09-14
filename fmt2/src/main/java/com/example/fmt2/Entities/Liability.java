package com.example.fmt2.Entities;


import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "liability")
public class Liability {

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lid;

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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @Column(nullable = true)
    private Integer period;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Column(nullable = false)
    private Long amount;

    // Getters and setters
}

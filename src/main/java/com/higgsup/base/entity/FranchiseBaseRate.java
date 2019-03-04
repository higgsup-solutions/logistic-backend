package com.higgsup.base.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class FranchiseBaseRate {
    private Long id;
    private Long carrierId;
    private BigDecimal franchiseBaseRate1;
    private BigDecimal franchiseBaseRate2;
    private BigDecimal franchiseBaseRate3;
    private BigDecimal franchiseBaseRate4;
    private Timestamp updatedDate;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "carrier_id", nullable = true)
    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    @Basic
    @Column(name = "franchise_base_rate1", nullable = true)
    public BigDecimal getFranchiseBaseRate1() {
        return franchiseBaseRate1;
    }

    public void setFranchiseBaseRate1(BigDecimal franchiseBaseRate1) {
        this.franchiseBaseRate1 = franchiseBaseRate1;
    }

    @Basic
    @Column(name = "franchise_base_rate2", nullable = true)
    public BigDecimal getFranchiseBaseRate2() {
        return franchiseBaseRate2;
    }

    public void setFranchiseBaseRate2(BigDecimal franchiseBaseRate2) {
        this.franchiseBaseRate2 = franchiseBaseRate2;
    }

    @Basic
    @Column(name = "franchise_base_rate3", nullable = true)
    public BigDecimal getFranchiseBaseRate3() {
        return franchiseBaseRate3;
    }

    public void setFranchiseBaseRate3(BigDecimal franchiseBaseRate3) {
        this.franchiseBaseRate3 = franchiseBaseRate3;
    }

    @Basic
    @Column(name = "franchise_base_rate4", nullable = true)
    public BigDecimal getFranchiseBaseRate4() {
        return franchiseBaseRate4;
    }

    public void setFranchiseBaseRate4(BigDecimal franchiseBaseRate4) {
        this.franchiseBaseRate4 = franchiseBaseRate4;
    }

    @Basic
    @Column(name = "updated_date", nullable = true)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

}

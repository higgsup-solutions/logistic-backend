package com.higgsup.base.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class UserBaseRate {
    private Long id;
    private Long carrierId;
    private BigDecimal userBaseRate1;
    private BigDecimal userBaseRate2;
    private BigDecimal userBaseRate3;
    private BigDecimal userBaseRate4;
    private Timestamp updatedDate;
    private BigDecimal dangerousCharge;

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
    @Column(name = "carrer_id", nullable = true)
    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    @Basic
    @Column(name = "user_base_rate1", nullable = true)
    public BigDecimal getUserBaseRate1() {
        return userBaseRate1;
    }

    public void setUserBaseRate1(BigDecimal userBaseRate1) {
        this.userBaseRate1 = userBaseRate1;
    }

    @Basic
    @Column(name = "user_base_rate2", nullable = true)
    public BigDecimal getUserBaseRate2() {
        return userBaseRate2;
    }

    public void setUserBaseRate2(BigDecimal userBaseRate2) {
        this.userBaseRate2 = userBaseRate2;
    }

    @Basic
    @Column(name = "user_base_rate3", nullable = true)
    public BigDecimal getUserBaseRate3() {
        return userBaseRate3;
    }

    public void setUserBaseRate3(BigDecimal userBaseRate3) {
        this.userBaseRate3 = userBaseRate3;
    }

    @Basic
    @Column(name = "user_base_rate4", nullable = true)
    public BigDecimal getUserBaseRate4() {
        return userBaseRate4;
    }

    public void setUserBaseRate4(BigDecimal userBaseRate4) {
        this.userBaseRate4 = userBaseRate4;
    }

    @Basic
    @Column(name = "updated_date", nullable = true)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Basic
    @Column(name = "dangerous_charge", nullable = true)
    public BigDecimal getDangerousCharge() {
        return dangerousCharge;
    }

    public void setDangerousCharge(BigDecimal dangerousCharge) {
        this.dangerousCharge = dangerousCharge;
    }
}

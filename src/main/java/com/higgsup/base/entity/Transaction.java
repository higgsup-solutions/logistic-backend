package com.higgsup.base.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Transaction {
    private long id;
    private Timestamp shippingDate;
    private String carierName;
    private String packageType;
    private String contactSender;
    private Long trackingNo;
    private Integer pieces;
    private Double actualWeight;
    private String destCountry;
    private Byte status;
    private BigDecimal baseCharge;
    private BigDecimal preClearance;
    private BigDecimal fuelSurcharge;
    private BigDecimal dtpAdminFee;
    private BigDecimal totalCharge;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "shipping_date", nullable = true)
    public Timestamp getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Timestamp shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Basic
    @Column(name = "carier_name", nullable = true, length = 100)
    public String getCarierName() {
        return carierName;
    }

    public void setCarierName(String carierName) {
        this.carierName = carierName;
    }

    @Basic
    @Column(name = "package_type", nullable = true, length = 50)
    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    @Basic
    @Column(name = "contact_sender", nullable = true, length = 225)
    public String getContactSender() {
        return contactSender;
    }

    public void setContactSender(String contactSender) {
        this.contactSender = contactSender;
    }

    @Basic
    @Column(name = "tracking_no", nullable = true)
    public Long getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(Long trackingNo) {
        this.trackingNo = trackingNo;
    }

    @Basic
    @Column(name = "pieces", nullable = true)
    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    @Basic
    @Column(name = "actual_weight", nullable = true, precision = 0)
    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    @Basic
    @Column(name = "dest_country", nullable = true, length = 225)
    public String getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "base_charge", nullable = true, precision = 2)
    public BigDecimal getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(BigDecimal baseCharge) {
        this.baseCharge = baseCharge;
    }

    @Basic
    @Column(name = "pre_clearance", nullable = true, precision = 2)
    public BigDecimal getPreClearance() {
        return preClearance;
    }

    public void setPreClearance(BigDecimal preClearance) {
        this.preClearance = preClearance;
    }

    @Basic
    @Column(name = "fuel_surcharge", nullable = true, precision = 2)
    public BigDecimal getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(BigDecimal fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    @Basic
    @Column(name = "dtp_admin_fee", nullable = true, precision = 2)
    public BigDecimal getDtpAdminFee() {
        return dtpAdminFee;
    }

    public void setDtpAdminFee(BigDecimal dtpAdminFee) {
        this.dtpAdminFee = dtpAdminFee;
    }

    @Basic
    @Column(name = "total_charge", nullable = true, precision = 2)
    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                Objects.equals(shippingDate, that.shippingDate) &&
                Objects.equals(carierName, that.carierName) &&
                Objects.equals(packageType, that.packageType) &&
                Objects.equals(contactSender, that.contactSender) &&
                Objects.equals(trackingNo, that.trackingNo) &&
                Objects.equals(pieces, that.pieces) &&
                Objects.equals(actualWeight, that.actualWeight) &&
                Objects.equals(destCountry, that.destCountry) &&
                Objects.equals(status, that.status) &&
                Objects.equals(baseCharge, that.baseCharge) &&
                Objects.equals(preClearance, that.preClearance) &&
                Objects.equals(fuelSurcharge, that.fuelSurcharge) &&
                Objects.equals(dtpAdminFee, that.dtpAdminFee) &&
                Objects.equals(totalCharge, that.totalCharge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shippingDate, carierName, packageType, contactSender, trackingNo, pieces, actualWeight, destCountry, status, baseCharge, preClearance, fuelSurcharge, dtpAdminFee, totalCharge);
    }
}

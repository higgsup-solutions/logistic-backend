package com.higgsup.base.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "price_detail")
public class PriceDetail {
    private long id;
    private Long packageId;
    private String addressFrom;
    private String addressTo;
    private Integer zoneNo;
    private BigDecimal zonePrice;
    private BigDecimal baseRate1;
    private BigDecimal baseRate2;
    private BigDecimal baseRate3;
    private BigDecimal baseRate4;
    private String contentType;
    private BigDecimal dangerousCharge;

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
    @Column(name = "package_id", nullable = true)
    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    @Basic
    @Column(name = "address_from", nullable = true, length = 225)
    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    @Basic
    @Column(name = "address_to", nullable = true, length = 225)
    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    @Basic
    @Column(name = "zone_no", nullable = true)
    public Integer getZoneNo() {
        return zoneNo;
    }

    public void setZoneNo(Integer zoneNo) {
        this.zoneNo = zoneNo;
    }

    @Basic
    @Column(name = "zone_price", nullable = true, precision = 2)
    public BigDecimal getZonePrice() {
        return zonePrice;
    }

    public void setZonePrice(BigDecimal zonePrice) {
        this.zonePrice = zonePrice;
    }

    @Basic
    @Column(name = "base_rate_1", nullable = true, precision = 2)
    public BigDecimal getBaseRate1() {
        return baseRate1;
    }

    public void setBaseRate1(BigDecimal baseRate1) {
        this.baseRate1 = baseRate1;
    }

    @Basic
    @Column(name = "base_rate_2", nullable = true, precision = 2)
    public BigDecimal getBaseRate2() {
        return baseRate2;
    }

    public void setBaseRate2(BigDecimal baseRate2) {
        this.baseRate2 = baseRate2;
    }

    @Basic
    @Column(name = "base_rate_3", nullable = true, precision = 2)
    public BigDecimal getBaseRate3() {
        return baseRate3;
    }

    public void setBaseRate3(BigDecimal baseRate3) {
        this.baseRate3 = baseRate3;
    }

    @Basic
    @Column(name = "base_rate_4", nullable = true, precision = 2)
    public BigDecimal getBaseRate4() {
        return baseRate4;
    }

    public void setBaseRate4(BigDecimal baseRate4) {
        this.baseRate4 = baseRate4;
    }

    @Basic
    @Column(name = "content_type", nullable = true, length = 100)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "dangerous_charge", nullable = true, precision = 2)
    public BigDecimal getDangerousCharge() {
        return dangerousCharge;
    }

    public void setDangerousCharge(BigDecimal dangerousCharge) {
        this.dangerousCharge = dangerousCharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDetail that = (PriceDetail) o;
        return id == that.id &&
                Objects.equals(packageId, that.packageId) &&
                Objects.equals(addressFrom, that.addressFrom) &&
                Objects.equals(addressTo, that.addressTo) &&
                Objects.equals(zoneNo, that.zoneNo) &&
                Objects.equals(zonePrice, that.zonePrice) &&
                Objects.equals(baseRate1, that.baseRate1) &&
                Objects.equals(baseRate2, that.baseRate2) &&
                Objects.equals(baseRate3, that.baseRate3) &&
                Objects.equals(baseRate4, that.baseRate4) &&
                Objects.equals(contentType, that.contentType) &&
                Objects.equals(dangerousCharge, that.dangerousCharge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packageId, addressFrom, addressTo, zoneNo, zonePrice, baseRate1, baseRate2, baseRate3, baseRate4, contentType, dangerousCharge);
    }
}

package com.higgsup.base.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Transaction {
    private Long id;
    private Timestamp shippingDate;
    private String carierName;
    private String serviceType;
    private String packageType;
    private String contactSender;
    private String trackingNo;
    private Integer pieces;
    private Double actualWeight;
    private String destCountry;
    private Byte status;
    private BigDecimal baseCharge;
    private BigDecimal preClearance;
    private BigDecimal fuelSurcharge;
    private BigDecimal dtpAdminFee;
    private BigDecimal totalCharge;

    private Double dimentionLength;
    private Double dimentionWeight;
    private Double dimentionHeight;

    private Double cubicWeight;

    private Double gst;
    private String contentType;

    private String senderCountryName;
    private String senderCompany;
    private String senderContactName;
    private String senderAddress;
    private String senderCityName;
    private String senderStateProvince;
    private String senderPostalCode;

    private String recipientCountryName;
    private String recipientCompany;
    private String recipientContactName;
    private String recipientAddress;
    private String recipientCityName;
    private String recipientStateProvince;
    private String recipientPostalCode;

    private Long userId;

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
    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSenderCountryName() {
        return senderCountryName;
    }

    public void setSenderCountryName(String senderCountryName) {
        this.senderCountryName = senderCountryName;
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public String getSenderContactName() {
        return senderContactName;
    }

    public void setSenderContactName(String senderContactName) {
        this.senderContactName = senderContactName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderCityName() {
        return senderCityName;
    }

    public void setSenderCityName(String senderCityName) {
        this.senderCityName = senderCityName;
    }

    public String getSenderStateProvince() {
        return senderStateProvince;
    }

    public void setSenderStateProvince(String senderStateProvince) {
        this.senderStateProvince = senderStateProvince;
    }

    public String getSenderPostalCode() {
        return senderPostalCode;
    }

    public void setSenderPostalCode(String senderPostalCode) {
        this.senderPostalCode = senderPostalCode;
    }

    public String getRecipientCountryName() {
        return recipientCountryName;
    }

    public void setRecipientCountryName(String recipientCountryName) {
        this.recipientCountryName = recipientCountryName;
    }

    public String getRecipientCompany() {
        return recipientCompany;
    }

    public void setRecipientCompany(String recipientCompany) {
        this.recipientCompany = recipientCompany;
    }

    public String getRecipientContactName() {
        return recipientContactName;
    }

    public void setRecipientContactName(String recipientContactName) {
        this.recipientContactName = recipientContactName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientCityName() {
        return recipientCityName;
    }

    public void setRecipientCityName(String recipientCityName) {
        this.recipientCityName = recipientCityName;
    }

    public String getRecipientStateProvince() {
        return recipientStateProvince;
    }

    public void setRecipientStateProvince(String recipientStateProvince) {
        this.recipientStateProvince = recipientStateProvince;
    }

    public String getRecipientPostalCode() {
        return recipientPostalCode;
    }

    public void setRecipientPostalCode(String recipientPostalCode) {
        this.recipientPostalCode = recipientPostalCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Double getDimentionLength() {
        return dimentionLength;
    }

    public void setDimentionLength(Double dimentionLength) {
        this.dimentionLength = dimentionLength;
    }

    public Double getDimentionWeight() {
        return dimentionWeight;
    }

    public void setDimentionWeight(Double dimentionWeight) {
        this.dimentionWeight = dimentionWeight;
    }

    public Double getDimentionHeight() {
        return dimentionHeight;
    }

    public void setDimentionHeight(Double dimentionHeight) {
        this.dimentionHeight = dimentionHeight;
    }

    public Double getCubicWeight() {
        return cubicWeight;
    }

    public void setCubicWeight(Double cubicWeight) {
        this.cubicWeight = cubicWeight;
    }

    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
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

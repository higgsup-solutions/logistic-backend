package com.higgsup.base.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

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

    private Byte status;
    private BigDecimal baseCharge;
    private BigDecimal fuelSurcharge;
    private BigDecimal totalCharge;
    private String contentType;

    private String senderCountryName;
    private String senderCompany;
    private String senderContactName;
    private String senderAddress1;
    private String senderAddress2;
    private String senderCityName;
    private String senderStateProvince;
    private String senderPostalCode;
    private String senderPhoneNumber;

    private String recipientCountryName;
    private String recipientCompany;
    private String recipientContactName;
    private String recipientAddress1;
    private String recipientAddress2;
    private String recipientCityName;
    private String recipientStateProvince;
    private String recipientPostalCode;
    private String recipientPhoneNumber;

    private Double totalWeight;

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
    @Column(name = "fuel_surcharge", nullable = true, precision = 2)
    public BigDecimal getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(BigDecimal fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
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

    public String getSenderAddress1() {
        return senderAddress1;
    }

    public void setSenderAddress1(String senderAddress1) {
        this.senderAddress1 = senderAddress1;
    }

    public String getSenderAddress2() {
        return senderAddress2;
    }

    public void setSenderAddress2(String senderAddress2) {
        this.senderAddress2 = senderAddress2;
    }

    public String getRecipientAddress1() {
        return recipientAddress1;
    }

    public void setRecipientAddress1(String recipientAddress1) {
        this.recipientAddress1 = recipientAddress1;
    }

    public String getRecipientAddress2() {
        return recipientAddress2;
    }

    public void setRecipientAddress2(String recipientAddress2) {
        this.recipientAddress2 = recipientAddress2;
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

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
}

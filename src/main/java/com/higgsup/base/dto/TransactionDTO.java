package com.higgsup.base.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TransactionDTO {
    private Long id;
    private Timestamp shippingDate;
    private String carierName;
    private String serviceType;
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
}

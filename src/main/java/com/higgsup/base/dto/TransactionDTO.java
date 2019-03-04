package com.higgsup.base.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class TransactionDTO {
    private Long id;
    private Timestamp shippingDate;
    private String carierName = "";
    private String serviceType = "";
    private String packageType = "";
    private String contactSender = "";
    private String trackingNo = "";
    private Integer pieces;

    private Byte status;
    private BigDecimal baseCharge;
    private BigDecimal fuelSurcharge;
    private BigDecimal totalCharge;
    private String contentType = "";

    private String senderCountryName = "";
    private String senderCompany= "";
    private String senderContactName = "";
    private String senderAddress1= "";
    private String senderAddress2 = "";
    private String senderCityName = "";
    private String senderStateProvince = "";
    private String senderPostalCode = "";
    private String senderPhoneNumber = "";

    private String recipientCountryName = "";
    private String recipientCompany = "";
    private String recipientContactName = "";
    private String recipientAddress1 = "";
    private String recipientAddress2 = "";
    private String recipientCityName = "";
    private String recipientStateProvince = "";
    private String recipientPostalCode = "";
    private String recipientPhoneNumber = "";

    private Double totalWeight;

    List<TransactionDimensionDTO> dimensionDTOList;

}

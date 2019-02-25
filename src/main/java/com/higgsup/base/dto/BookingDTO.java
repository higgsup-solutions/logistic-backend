package com.higgsup.base.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BookingDTO {

    private AddressDTO senderAddress;
    private AddressDTO recipientAddress;
    private QuoteRequest quoteRequest;
    private Timestamp shippingDate;
    private String serviceType;

}

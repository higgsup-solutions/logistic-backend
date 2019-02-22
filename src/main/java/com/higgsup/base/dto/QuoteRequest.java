package com.higgsup.base.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuoteRequest {
    List<DimensionDTO> dimensionDTOList;
    private Long carrierId;
    private Long packageId;
    private String contentType;
    private AddressDTO senderDTO;
    private AddressDTO receiverDTO;
    private Boolean dangerousGoods;
}

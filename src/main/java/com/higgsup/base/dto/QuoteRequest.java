package com.higgsup.base.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuoteRequest {
    List<DimensionDTO> dimensionDTOList;
    private Long countryId;
    private Long carrierId;
    private Long packageId;
    private String contentType = "";
    private String senderCityName = "";
    private String recipientCityName = "";
    private Boolean dangerousGoods;
}

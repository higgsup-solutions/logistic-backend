package com.higgsup.base.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private Long countryId;
    private Long cityId;
    private String userType;
    private String company;
    private String contactName;
    private Boolean senderDefault;
    private Boolean receipientDefault;
    private String address1;
    private String address2;
    private String cityName;
    private String countryName;
    private String postalCode;
    private String stateProvince;

}

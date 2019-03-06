package com.higgsup.base.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class FranchiseBasicInfoDTO {
    private String name;
    private String relationship;
    private String customerName;
    private String contactName;
    private String contactTitle;
    private String address;
    private Long countryId;
    private String cityName;
    private String fax;
    private String phone;
    private String stateCode;
    private String postalCode;
    private String email;

}

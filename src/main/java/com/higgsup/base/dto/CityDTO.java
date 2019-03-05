package com.higgsup.base.dto;

import lombok.Data;

@Data
public class CityDTO {
    private Long id;
    private String cityName = "";
    private String postalCode = "";
    private String stateProvince = "";
}

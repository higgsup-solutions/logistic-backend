package com.higgsup.base.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarrierDTO {
    private long id;
    private String carrierType;
    private Boolean carrierDefault;
    private List<PackageDTO> packageDTO;
}

package com.higgsup.base.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarrierDTO {
    private long id;
    private String carrierType = "";
    private List<PackageDTO> packageDTO = new ArrayList<>();
}

package com.higgsup.base.dto;

import lombok.Data;

@Data
public class PackageDTO {
    private Long id;
    private CarrierDTO carrier;
    private String packageType;
}

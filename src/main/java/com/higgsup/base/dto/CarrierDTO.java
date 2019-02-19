package com.higgsup.base.dto;

import com.higgsup.base.entity.Carrier;
import com.higgsup.base.dto.base.IPagedResponse;
import lombok.Data;

import java.util.List;

@Data
public class CarrierDTO {
    private long id;
    private String carrierType;
}

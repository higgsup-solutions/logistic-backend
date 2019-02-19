package com.higgsup.base.service;

import com.higgsup.base.dto.CarrierDTO;
import com.higgsup.base.dto.PackageDTO;
import com.higgsup.base.entity.Carrier;

import java.util.List;

public interface ICarrierService {
    List<CarrierDTO> getAllCarrierType();
    List<PackageDTO> getAllPackageType();
}

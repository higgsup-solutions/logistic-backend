package com.higgsup.base.service.impl;

import com.higgsup.base.dto.CarrierDTO;
import com.higgsup.base.dto.PackageDTO;
import com.higgsup.base.entity.Carrier;
import com.higgsup.base.entity.Package;
import com.higgsup.base.repository.CarrierRepository;
import com.higgsup.base.repository.PackageRepository;
import com.higgsup.base.service.ICarrierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional()
public class CarrierService implements ICarrierService {
    private final CarrierRepository carrierRepository;
    private final PackageRepository packageRepository;

    public CarrierService(CarrierRepository carrierRepository, PackageRepository packageRepository) {
        this.carrierRepository = carrierRepository;
        this.packageRepository = packageRepository;
    }

    @Override
    public List<CarrierDTO> getAllCarrierType() {
        List<CarrierDTO> carrierDTOList = new ArrayList<>();
        List<Carrier> carrierList = carrierRepository.getAllCarrierType();
        for (Carrier carrier : carrierList){
            CarrierDTO carrierDTO = new CarrierDTO();

            carrierDTO.setId(carrier.getId());
            carrierDTO.setCarrierType(carrier.getCarrierType());

            // get list package entity of current carrier by carrier id
            Long carrierId = carrier.getId();
            List<Package> packages =  packageRepository.getpackageByCarrierId(carrierId);

            //copy package entity to package DTO and set it to carrier DTO

            List<PackageDTO> packageDTOList = new ArrayList<>();
            for (Package packageItem : packages) {
                PackageDTO packageDTO = new PackageDTO();
                BeanUtils.copyProperties(packageItem,packageDTO);
                packageDTOList.add(packageDTO);
                carrierDTO.setPackageDTO(packageDTOList);
            }


            carrierDTOList.add(carrierDTO);
        }
        return carrierDTOList;
    }

}

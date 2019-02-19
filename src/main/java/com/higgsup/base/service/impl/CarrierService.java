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
            BeanUtils.copyProperties(carrier,carrierDTO);
            carrierDTOList.add(carrierDTO);
        }
        return carrierDTOList;
    }

    @Override
    public List<PackageDTO> getAllPackageType() {
        List<PackageDTO> packageDTOList = new ArrayList<>();
        List<Package> packageList = packageRepository.findAll();
        for (Package packageItem : packageList){
            PackageDTO packageDTO = new PackageDTO();

            //get carrier info  of current package
            Long carrierId = packageItem.getCarrierId();
            Carrier carrier = carrierRepository.getOne(carrierId);
            CarrierDTO carrierDTO = new CarrierDTO();
            BeanUtils.copyProperties(carrier,carrierDTO);

            //set data for current package dto by package entity and add to list
            packageDTO.setId(packageItem.getId());
            packageDTO.setCarrier(carrierDTO);
            packageDTO.setPackageType(packageItem.getPackageType());

            packageDTOList.add(packageDTO);
        }
        return packageDTOList;
    }
}

package com.higgsup.base.service.impl;

import com.higgsup.base.common.CarrierType;
import com.higgsup.base.common.ContentType;
import com.higgsup.base.common.ErrorCode;
import com.higgsup.base.dto.*;
import com.higgsup.base.entity.*;
import com.higgsup.base.entity.Package;
import com.higgsup.base.exception.BusinessException;
import com.higgsup.base.repository.*;
import com.higgsup.base.service.ICarrierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class CarrierService implements ICarrierService {
    private final CarrierRepository carrierRepository;
    private final PackageRepository packageRepository;

    private PriceDetailRepository priceDetailRepository;
    private ZonePriceRepository zonePriceRepository;
    private LocationZoneRepository locationZoneRepository;
    private CityRepository cityRepository;

    public CarrierService(CarrierRepository carrierRepository,
                          PackageRepository packageRepository,
                          PriceDetailRepository priceDetailRepository,
                          ZonePriceRepository zonePriceRepository,
                          LocationZoneRepository locationZoneRepository,
                          CityRepository cityRepository) {
        this.carrierRepository = carrierRepository;
        this.packageRepository = packageRepository;
        this.priceDetailRepository = priceDetailRepository;
        this.zonePriceRepository = zonePriceRepository;
        this.locationZoneRepository = locationZoneRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public List<CarrierDTO> getAllCarrierType() {
        List<CarrierDTO> carrierDTOList = new ArrayList<>();
        List<Carrier> carrierList = carrierRepository.getAllCarrierType();
        for (Carrier carrier : carrierList) {
            CarrierDTO carrierDTO = new CarrierDTO();

            carrierDTO.setId(carrier.getId());
            carrierDTO.setCarrierType(carrier.getCarrierType());

            // get list package entity of current carrier by carrier id
            Long carrierId = carrier.getId();
            List<Package> packages = packageRepository.getpackageByCarrierId(carrierId);

            //copy package entity to package DTO and set it to carrier DTO
            List<PackageDTO> packageDTOList = new ArrayList<>();
            for (Package packageItem : packages) {
                PackageDTO packageDTO = new PackageDTO();
                BeanUtils.copyProperties(packageItem, packageDTO);
                packageDTOList.add(packageDTO);
                carrierDTO.setPackageDTO(packageDTOList);
            }
            carrierDTOList.add(carrierDTO);
        }
        return carrierDTOList;
    }

    /*Find Carrier
           check carrier name contain Domestic or international
           if domestic compaire weigh and cubic weight =  (L * W * H )/ 6000
           else international compaire weigh and cubic weight
        */
    @Override
    public QuoteResultDTO showQuoteResult(QuoteRequest quoteRequest) {
        Optional<Carrier> carrierOptional = carrierRepository.findById(quoteRequest.getCarrierId());
        if (!carrierOptional.isPresent()) {
            throw new BusinessException(ErrorCode.CARRIER_NOT_FOUND, String.valueOf(ErrorCode.CARRIER_NOT_FOUND.getErrorCode()));
        }
        else if(CollectionUtils.isEmpty(quoteRequest.getDimensionDTOList())){
            throw new BusinessException(ErrorCode.DIMENSION_IS_EMPTY, String.valueOf(ErrorCode.DIMENSION_IS_EMPTY.getErrorCode()));
        }
        else {
            Carrier carrier = carrierOptional.get();
            if (carrier.getCarrierType().toLowerCase().contains(CarrierType.DOMESTIC.getContent())) {
                return doQuoteForDomestic(quoteRequest.getCarrierId(), quoteRequest.getPackageId(),
                        quoteRequest.getContentType(), quoteRequest.getCountryId(), quoteRequest.getSenderCityName(),
                        quoteRequest.getRecipientCityName(), quoteRequest.getDimensionDTOList(), quoteRequest.getDangerousGoods());
            } else {
                return doQuoteForInternational(quoteRequest.getCarrierId(), quoteRequest.getPackageId(),
                        quoteRequest.getContentType(), quoteRequest.getDimensionDTOList(), quoteRequest.getDangerousGoods());
            }
        }
    }

    private Double calculateCubicWeight(Double length, Double width, Double height) {
        return (length * width * height) / 6000;
    }

    private QuoteResultDTO doQuoteForDomestic(Long carrierId, Long packageId, String contentType,
                                              Long countryId, String cityFrom, String cityTo, List<DimensionDTO> dimensionDTOs, boolean dangerousGoods) {
        BigDecimal totalCharge;
        BigDecimal totalBaseCharge = BigDecimal.ZERO;
        Double totalWeight = 0d;
        Double actualWeight;
        int quantity;

        QuoteResultDTO quoteResultDTO = new QuoteResultDTO();

        Optional<PriceDetail> priceDetailOptional = priceDetailRepository.findByPackageId(packageId);
        if (!priceDetailOptional.isPresent()) {
            throw new BusinessException(ErrorCode.PACKAGE_NOT_FOUND, String.valueOf(ErrorCode.PACKAGE_NOT_FOUND.getErrorCode()));
        }
        List<City> citiesFrom = cityRepository.findByCountryIdAndCityName(countryId, cityFrom);
        List<City> citiesTo = cityRepository.findByCountryIdAndCityName(countryId, cityTo);
        String zoneName = "2";
        if (!CollectionUtils.isEmpty(citiesFrom)
                && !CollectionUtils.isEmpty(citiesTo)) {
            List<LocationZone> locationZones = locationZoneRepository.findByAddressFromIdAndAddressToId(citiesFrom.get(0).getId(),
                    citiesTo.get(0).getId());
            if (!CollectionUtils.isEmpty(locationZones)) {
                zoneName = locationZones.get(0).getZoneName();
            }
        }
        ZonePrice zonePrice = zonePriceRepository.findByCarrierIdAndName(carrierId, zoneName);
        for (DimensionDTO dimensionDTO : dimensionDTOs) {
            BigDecimal baseCharge;
            Double weight = setWeight(contentType, dimensionDTO.getLength(), dimensionDTO.getWidth(), dimensionDTO.getHeight(),dimensionDTO.getWeights(), dimensionDTO);
            quantity = dimensionDTO.getQuantity();
            actualWeight = (weight * quantity);

            //rate
            BigDecimal weighBaseRate = getRateWeightOfDomestic(priceDetailOptional.get(), weight);
            BigDecimal weighRateZone = zonePrice.getZonePrice();
            baseCharge = (new BigDecimal(actualWeight)).multiply(weighBaseRate).multiply(weighRateZone);
            totalBaseCharge = totalBaseCharge.add(baseCharge);
            totalWeight += actualWeight;
        }

        if (dangerousGoods) {
            quoteResultDTO.setDangerousCharge(priceDetailOptional.get().getDangerousCharge() == null ? BigDecimal.ZERO : priceDetailOptional.get().getDangerousCharge());
            totalCharge = totalBaseCharge.add(priceDetailOptional.get().getDangerousCharge());
        } else {
            quoteResultDTO.setDangerousCharge(BigDecimal.ZERO);
            totalCharge = totalBaseCharge;
        }

        //find zone name
        quoteResultDTO.setTotalWeight(totalWeight);
        quoteResultDTO.setDimensions(dimensionDTOs);
        quoteResultDTO.setBaseCharge(totalBaseCharge);
        quoteResultDTO.setTotalCharge(totalCharge);
        quoteResultDTO.setWeightType("Actual");
        return quoteResultDTO;

    }

    private QuoteResultDTO doQuoteForInternational(Long carrierId, Long packageId, String contentType, List<DimensionDTO> dimensionDTOs, boolean dangerousGoods) {
        BigDecimal totalCharge;
        BigDecimal totalBaseCharge = BigDecimal.ZERO;
        Double totalWeight = 0d;
        int quantity;
        Double actualWeight;

        QuoteResultDTO quoteResultDTO = new QuoteResultDTO();
        Carrier carrier = carrierRepository.findCarrierById(carrierId);

        Optional<PriceDetail> priceDetailOptional = priceDetailRepository.findByPackageId(packageId);
        if (!priceDetailOptional.isPresent()) {
            throw new BusinessException(ErrorCode.PACKAGE_NOT_FOUND, String.valueOf(ErrorCode.PACKAGE_NOT_FOUND.getErrorCode()));
        }

        for (DimensionDTO dimensionDTO : dimensionDTOs) {
            BigDecimal baseCharge;
            Double weight = setWeight(contentType,dimensionDTO.getLength(),dimensionDTO.getWidth(), dimensionDTO.getHeight(), dimensionDTO.getWeights(), dimensionDTO);
            quantity = dimensionDTO.getQuantity();
            actualWeight = (weight * quantity);

            //rate
            BigDecimal weighBaseRate = getRateWeightOfInternational(priceDetailOptional.get(), weight);
            baseCharge = (BigDecimal.valueOf(actualWeight)).multiply(weighBaseRate);
            totalBaseCharge = totalBaseCharge.add(baseCharge);
            totalWeight += actualWeight;
        }

        if (dangerousGoods) {
            quoteResultDTO.setDangerousCharge(priceDetailOptional.get().getDangerousCharge() == null ? BigDecimal.ZERO : priceDetailOptional.get().getDangerousCharge());
            totalCharge = totalBaseCharge.add(priceDetailOptional.get().getDangerousCharge()).add(fuelSurcharge(totalBaseCharge, carrier.getCarrierType()));
        } else {
            quoteResultDTO.setDangerousCharge(BigDecimal.ZERO);
            totalCharge = totalBaseCharge.add(fuelSurcharge(totalBaseCharge, carrier.getCarrierType()));
        }
        quoteResultDTO.setTotalWeight(totalWeight);
        quoteResultDTO.setBaseCharge(totalBaseCharge);
        quoteResultDTO.setTotalCharge(totalCharge);
        quoteResultDTO.setFuelSurcharge(fuelSurcharge(totalBaseCharge, carrier.getCarrierType()));
        quoteResultDTO.setWeightType("Actual");
        quoteResultDTO.setDimensions(dimensionDTOs);
        return quoteResultDTO;
    }

    private BigDecimal getRateWeightOfDomestic(PriceDetail priceDetail, Double weight) {
        if (weight <= 1) {
            return priceDetail.getBaseRate1();
        } else if (weight <= 10) {
            return priceDetail.getBaseRate2();
        } else if (weight <= 50) {
            return priceDetail.getBaseRate3();
        } else {
            return priceDetail.getBaseRate4();
        }
    }

    private BigDecimal getRateWeightOfInternational(PriceDetail priceDetail, Double weight) {
        if (weight <= 1) {
            return priceDetail.getBaseRate1();
        } else if (weight <= 5) {
            return priceDetail.getBaseRate2();
        } else if (weight <= 30) {
            return priceDetail.getBaseRate3();
        } else {
            return priceDetail.getBaseRate4();
        }
    }

    private BigDecimal fuelSurcharge(BigDecimal baseCharge, String carrierType) {
        if (carrierType.toUpperCase().contains(CarrierType.DHL.getContent())) {
            return (baseCharge.multiply(BigDecimal.valueOf(4L)).divide(BigDecimal.valueOf(100L)));

        } else {
            return (baseCharge.multiply(BigDecimal.valueOf(3L))).divide(BigDecimal.valueOf(100L));
        }
    }

    private Double setWeight(String contentType, Double length, Double width, Double height, Double baseWeight, DimensionDTO dimensionDTO){
        Double weight = 1D;
        if (contentType.equals(ContentType.Documents.getContent())) {
            return weight;
        } else {
            Double cubicWeight = calculateCubicWeight(length, width, height);
            dimensionDTO.setCubicWeight(cubicWeight);
            return baseWeight > cubicWeight ? baseWeight : cubicWeight;
        }
    }
}

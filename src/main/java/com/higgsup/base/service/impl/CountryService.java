package com.higgsup.base.service.impl;

import com.higgsup.base.dto.CityDTO;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.repository.CityRepository;
import com.higgsup.base.service.ICountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CountryService implements ICountryService {

    private CityRepository cityRepository;

    public CountryService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public ResponseMessage getCity(Long countryId, String cityName) {
        ResponseMessage result = new ResponseMessage();

        List<Object[]> cityList = cityRepository.getCity(countryId, cityName);

        if(CollectionUtils.isEmpty(cityList)) {
            return result;
        }

        List<CityDTO> cityDTOList = new ArrayList<>();

        for (Object[] data: cityList
        ) {
            CityDTO cityDTO = new CityDTO();
            if (data[0] != null) {
                cityDTO.setId(Long.valueOf(data[0].toString()));
            }

            if (data[1] != null) {
                cityDTO.setCityName(data[1].toString());
            }

            if (data[2] != null) {
                cityDTO.setPostalCode(data[2].toString());
            }

            if (data[3] != null) {
                cityDTO.setStateProvince(data[3].toString());
            }

            cityDTOList.add(cityDTO);
        }
        result.setData(cityDTOList);
        return result;
    }
}

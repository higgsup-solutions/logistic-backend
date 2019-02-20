package com.higgsup.base.service;

import com.higgsup.base.dto.base.ResponseMessage;

public interface ICountryService {

  ResponseMessage getCity(Long countryId);

}

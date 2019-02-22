package com.higgsup.base.service;

import com.higgsup.base.dto.CarrierDTO;
import com.higgsup.base.dto.QuoteRequest;
import com.higgsup.base.dto.QuoteResultDTO;

import java.util.List;

public interface ICarrierService {
    List<CarrierDTO> getAllCarrierType();
    QuoteResultDTO showQuoteResult(QuoteRequest quoteRequest);

}

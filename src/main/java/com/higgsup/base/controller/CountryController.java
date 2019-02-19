package com.higgsup.base.controller;

import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.service.ICountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/countries")
@Validated
public class CountryController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ICountryService countryService;

    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{id}/cities/{name}")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getCity(@PathVariable("id") Long countryId, @PathVariable("name") String cityName) {
        ResponseMessage result = countryService.getCity(countryId, cityName);
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }




}

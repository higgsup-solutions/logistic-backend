package com.higgsup.base.controller;

import com.higgsup.base.dto.CarrierDTO;

import com.higgsup.base.dto.base.IPagedResponse;
import com.higgsup.base.dto.base.ResponseMessage;

import com.higgsup.base.locale.Translator;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.service.ICarrierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/carrier")
@Validated
public class CarrierController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ICarrierService carrierService;

    public CarrierController(ICarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @GetMapping("/ping")
    public ResponseEntity ping() {
        return ResponseEntity.ok(Translator.toLocale("hello"));
    }

    @GetMapping("")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getAllCarrierType() {
        ResponseMessage<List<CarrierDTO>> responseMessage = new ResponseMessage<>();
        responseMessage.setData(carrierService.getAllCarrierType());
        responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());

        return ResponseEntity.ok(responseMessage);
    }
}

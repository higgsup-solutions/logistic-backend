package com.higgsup.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


}

package com.higgsup.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
@Validated
public class CountryController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
}

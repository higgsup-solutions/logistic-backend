package com.higgsup.base.controller;

import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.service.IUserService;
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
@RequestMapping("/user")
@Validated
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{id}/addresses")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getAddressList(@PathVariable("id") Long id) {
        ResponseMessage result = userService.getAddressList(id);
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }


}

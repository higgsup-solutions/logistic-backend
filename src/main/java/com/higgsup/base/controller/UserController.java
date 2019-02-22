package com.higgsup.base.controller;

import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.base.IPagedResponse;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.security.model.UserContext;
import com.higgsup.base.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/dimensions")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getDimensions(@PathVariable("id") Long id, @RequestParam("limit") Integer limit) {

        ResponseMessage<List<DimensionDTO>> responseMessage = new ResponseMessage<>();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        responseMessage.setData(userService.getDimensions(userContext.getUserId(),limit));
        responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping(value = "/{id}/addresses")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getAddressList(@PathVariable("id") Long id) {
        ResponseMessage result = new ResponseMessage();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        result.setData(userService.getAddressList(userContext.getUserId()));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/{id}/addresses")
    @RequestLogger
    public ResponseEntity<ResponseMessage> saveAddress(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseMessage result = new ResponseMessage();
        result.setData(userService.saveAddress(addressDTO, userContext.getUserId()));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

}

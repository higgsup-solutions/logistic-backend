package com.higgsup.base.controller;

import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.security.auth.ajax.ChangePassRequest;
import com.higgsup.base.security.model.UserContext;
import com.higgsup.base.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getUserInfo(@PathVariable("id") Long id) {
        ResponseMessage<UserDTO> responseMessage = new ResponseMessage<>();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        responseMessage.setData(userService.findUser(userContext.getUserId()));
        responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/{id}")
    @RequestLogger
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        ResponseMessage<UserDTO> responseMessage = new ResponseMessage<>();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        responseMessage.setData(userService.updateUser(userContext.getUserId(), userDTO));
        responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(responseMessage);
    }


    @GetMapping("/{id}/dimensions")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getDimensions(@PathVariable("id") Long id, @RequestParam(value ="limit",  required = false) Integer limit) {

        ResponseMessage<List<DimensionDTO>> responseMessage = new ResponseMessage<>();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        responseMessage.setData(userService.getDimensions(userContext.getUserId(), limit));
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

    @PutMapping(value = "/{id}/addresses/{addressId}")
    @RequestLogger
    public ResponseEntity<ResponseMessage> updateAddress(@PathVariable("id") Long id,
                                                         @PathVariable("addressId") Long addressId,
                                                         @RequestBody AddressDTO addressDTO) {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseMessage result = new ResponseMessage();
        result.setData(userService.updateAddress(addressDTO, userContext.getUserId(), addressId));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}/addresses/{addressId}")
    @RequestLogger
    public ResponseEntity<ResponseMessage> deleteAddress(@PathVariable("id") Long id,
                                                         @PathVariable("addressId") Long addressId) {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseMessage result = new ResponseMessage();
        userService.delete(userContext.getUserId(), addressId);
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}/dimension/{dimensionId}")
    @RequestLogger
    public ResponseEntity<ResponseMessage> deleteDimension(@PathVariable("id") Long id,
                                                           @PathVariable("dimensionId") Long dimensionId) {

        ResponseMessage result = new ResponseMessage();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteDimension(dimensionId,userContext.getUserId());
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/change_pass")
    public ResponseEntity<ResponseMessage> changePass(@PathVariable("id") Long id,
                                                      @RequestBody ChangePassRequest changePassRequest) {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseMessage result = new ResponseMessage();
        result.setData(userService.changePassword(userContext.getUserId(), changePassRequest.getOldPassword(),
                changePassRequest.getNewPassword(), changePassRequest.getConfirmPassword()));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/dimension")
    public ResponseEntity<ResponseMessage> saveDimension(@PathVariable("id") Long id,
                                                         @RequestBody DimensionDTO dimensionDTO) {
        ResponseMessage result = new ResponseMessage();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        result.setData(userService.saveDimension(dimensionDTO,userContext.getUserId()));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/dimension/{dimensionId}")
    @RequestLogger
    public ResponseEntity<ResponseMessage> updateDimension(@PathVariable("id") Long id,
                                                           @RequestBody DimensionDTO dimensionDTO,
                                                           @PathVariable("dimensionId") Long dimensionId) {
        ResponseMessage<DimensionDTO> responseMessage = new ResponseMessage<>();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        responseMessage.setData(userService.updateDimension(userContext.getUserId(), dimensionDTO, dimensionId));
        responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(responseMessage);
    }
}

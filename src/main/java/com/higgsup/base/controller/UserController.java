package com.higgsup.base.controller;

import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.dto.base.IPagedResponse;
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


    @GetMapping("/dimension")
    @RequestLogger
    public IPagedResponse<List<DimensionDTO>> getTop5Dimension(HttpServletRequest request) {
        IPagedResponse iPagedResponse = new IPagedResponse();

        ResponseMessage<List<DimensionDTO>> responseMessage = new ResponseMessage<>();
        responseMessage.setData(userService.getTop5Dimension());
        responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
        iPagedResponse.setResponseMessage(responseMessage);

        return iPagedResponse;
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
        userService.deleteDimension(dimensionId);
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }
    @PostMapping("/{id}/change_pass")
    public ResponseEntity<ResponseMessage> changePass(
            @RequestBody ChangePassRequest changePassRequest) {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseMessage result = new ResponseMessage();
        result.setData(userService.changePassword(userContext.getUserId(), changePassRequest.getOldPassword(),
                changePassRequest.getNewPassword(), changePassRequest.getConfirmPassword()));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }
}

package com.higgsup.base.controller;

import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.base.IPagedResponse;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/addresses")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getAddressList() {
        ResponseMessage result = userService.getAddressList(5L);
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/addresses/save")
    @RequestLogger
    public ResponseEntity<ResponseMessage> saveAddress(@RequestBody AddressDTO addressDTO) {
        ResponseMessage result = new ResponseMessage();
        result.setData(userService.saveAddress(addressDTO, 5L));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

}

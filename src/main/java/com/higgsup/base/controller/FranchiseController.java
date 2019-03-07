package com.higgsup.base.controller;

import com.higgsup.base.common.ErrorCode;
import com.higgsup.base.dto.*;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.exception.BusinessException;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.repository.FranchiseRepository;
import com.higgsup.base.security.model.UserContext;
import com.higgsup.base.service.IFranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchise")
@Validated
public class FranchiseController {
    private final IFranchiseService franchiseService;
    private final FranchiseRepository franchiseRepository;

    public FranchiseController(IFranchiseService franchiseService, FranchiseRepository franchiseRepository) {
        this.franchiseService = franchiseService;
        this.franchiseRepository = franchiseRepository;
    }

    @PostMapping(value = "/{id}/sub-franchise")
    public ResponseEntity<ResponseMessage> createFranchise(@PathVariable("id") Long id, @RequestBody FranchiseDTO franchiseDTO) {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long franchiseId = franchiseRepository.findFranchiseIdByUserId(userContext.getUserId());

        if (franchiseId == null){
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, String.valueOf(ErrorCode.USER_NOT_FOUND.getErrorCode()));
        } else {
            ResponseMessage result = new ResponseMessage();
            result.setData(franchiseService.createFranchise(franchiseId,franchiseDTO));
            result.setStatus(HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/{id}/sub-franchises")
    public ResponseEntity<ResponseMessage> getFranchises(@PathVariable("id") Long id) {
        ResponseMessage<SubFranchiseDTO> responseMessage = new ResponseMessage<>();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long franchiseId = franchiseRepository.findFranchiseIdByUserId(userContext.getUserId());

        if (franchiseId == null){
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, String.valueOf(ErrorCode.USER_NOT_FOUND.getErrorCode()));
        } else{
            responseMessage.setData(franchiseService.getListInfoFranchise(franchiseId));
            responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(responseMessage);
        }
    }

    @GetMapping("/{id}/franchise-users")
    public ResponseEntity<ResponseMessage> getFranchiseUsers(@PathVariable("id") Long id) {
        ResponseMessage<List<FranchiseUserDTO>> responseMessage = new ResponseMessage<>();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long franchiseId = franchiseRepository.findFranchiseIdByUserId(userContext.getUserId());

        if (franchiseId == null){
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, String.valueOf(ErrorCode.USER_NOT_FOUND.getErrorCode()));
        } else{
            responseMessage.setData(franchiseService.getListUsers(franchiseId));
            responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(responseMessage);
        }
    }
}

package com.higgsup.base.controller;

import com.higgsup.base.dto.FranchiseDTO;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.repository.FranchiseRepository;
import com.higgsup.base.security.model.UserContext;
import com.higgsup.base.service.IFranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

        ResponseMessage result = new ResponseMessage();
        result.setData(franchiseService.createFranchise(franchiseId,franchiseDTO));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }
}

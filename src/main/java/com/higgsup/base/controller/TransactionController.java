package com.higgsup.base.controller;

import com.higgsup.base.dto.TransactionDTO;
import com.higgsup.base.dto.base.IPagedResponse;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.security.model.UserContext;
import com.higgsup.base.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@Validated
public class TransactionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/search")
    @RequestLogger
    public IPagedResponse<List<TransactionDTO>> getTransaction(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(value ="textSearch", required = false) String textSearch) {
        if(textSearch == null || StringUtils.isEmpty(textSearch) ) {
            UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return transactionService.getTransactionList(userContext.getUserId(), page, size);
        } else {
            UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return transactionService.fullTextSearch(userContext.getUserId(), textSearch, page, size);
        }
    }

}

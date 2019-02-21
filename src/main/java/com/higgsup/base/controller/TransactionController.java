package com.higgsup.base.controller;

import com.higgsup.base.dto.base.IPagedResponse;
import com.higgsup.base.dto.base.ResponseMessage;
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

import java.util.Map;

import static com.higgsup.base.service.impl.TransactionService.RESULT_KEY;
import static com.higgsup.base.service.impl.TransactionService.TOTAL_ITEM_KEY;

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
    public IPagedResponse getTransaction(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(value = "textSearch", required = false) String textSearch) {
        Map<String, Object> dataMap;
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        IPagedResponse iPagedResponse = new IPagedResponse();
        if (textSearch == null || StringUtils.isEmpty(textSearch)) {
            dataMap = transactionService.getTransactionList(userContext.getUserId(), page, size);
        } else {
            dataMap = transactionService.fullTextSearch(userContext.getUserId(), textSearch, page, size);
        }

        if (dataMap != null) {
            iPagedResponse.setResponseMessage((ResponseMessage) dataMap.get(RESULT_KEY));
            iPagedResponse.setPageSize(size);
            iPagedResponse.setPageIndex(page);
            iPagedResponse.setTotalItem((Long) dataMap.get(TOTAL_ITEM_KEY));
        } else {
            iPagedResponse.setPageSize(size);
            iPagedResponse.setPageIndex(page);
            iPagedResponse.setTotalItem(0);
        }
        return iPagedResponse;
    }

}

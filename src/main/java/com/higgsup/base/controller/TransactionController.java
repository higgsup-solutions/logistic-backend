package com.higgsup.base.controller;

import com.higgsup.base.dto.BookingDTO;
import com.higgsup.base.dto.TransactionDTO;
import com.higgsup.base.dto.base.IPagedResponse;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.log.RequestLogger;
import com.higgsup.base.security.model.UserContext;
import com.higgsup.base.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public IPagedResponse<List<TransactionDTO>> getTransaction(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(value = "textSearch", required = false) String textSearch) {
        Map<String, Object> dataMap;
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        IPagedResponse iPagedResponse = new IPagedResponse();
        if (textSearch == null || StringUtils.isEmpty(textSearch)) {
            dataMap = transactionService.getTransactionList(userContext.getUserId(), page, size);
        } else {
            dataMap = transactionService.fullTextSearch(userContext.getUserId(), textSearch, page, size);
        }

        ResponseMessage<List<TransactionDTO>> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
        if (dataMap != null) {
            responseMessage.setData((List<TransactionDTO>) dataMap.get(RESULT_KEY));
            iPagedResponse.setResponseMessage(responseMessage);
            iPagedResponse.setPageSize(size);
            iPagedResponse.setPageIndex(page);
            iPagedResponse.setTotalItem((Long) dataMap.get(TOTAL_ITEM_KEY));
        } else {
            responseMessage.setData(null);
            iPagedResponse.setResponseMessage(responseMessage);
            iPagedResponse.setPageSize(size);
            iPagedResponse.setPageIndex(page);
            iPagedResponse.setTotalItem(0);
        }
        return iPagedResponse;
    }

    @PostMapping(value = "/confirmBooking")
    @RequestLogger
    public ResponseEntity<ResponseMessage> saveBooking(@RequestBody BookingDTO bookingDTO) {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseMessage result = new ResponseMessage();
        result.setData(transactionService.saveBooking(bookingDTO, userContext.getUserId()));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}/location")
    @RequestLogger
    public ResponseEntity<ResponseMessage> getLocationLog(@PathVariable("id") Long transactionId) {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseMessage result = new ResponseMessage();
        result.setData(transactionService.getLocationLog(transactionId, userContext.getUserId()));
        result.setStatus(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(result);
    }

}

package com.higgsup.base.service.impl;

import com.higgsup.base.dto.TransactionDTO;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.entity.Transaction;
import com.higgsup.base.repository.TransactionRepository;
import com.higgsup.base.service.ITransactionService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TransactionService implements ITransactionService {

    public static final String RESULT_KEY = "result";
    public static final String TOTAL_ITEM_KEY = "totalItem";

    private final TransactionRepository transactionRepository;
    private final MapperFacade mapperFacade;

    public TransactionService(TransactionRepository transactionRepository, MapperFacade mapperFacade) {
        this.transactionRepository = transactionRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public Map<String, Object> getTransactionList(Long userId, int page, int size) {
        Pageable sortedByShippingDate = PageRequest.of(page, size, Sort.by("shippingDate").descending());
        Page<Transaction> transactionList = transactionRepository.findByUserId(userId, sortedByShippingDate);
        return processDataAddress(transactionList);
    }

    @Override
    public Map<String, Object> fullTextSearch(Long userId, String textSearch, int page, int size) {
        Pageable sortedByShippingDate = PageRequest.of(page, size, Sort.by("shipping_date").descending());
        Page<Transaction> transactionList = transactionRepository.fullTextSearch(userId, textSearch, sortedByShippingDate);
        return processDataAddress(transactionList);
    }

    private Map<String, Object> processDataAddress(Page<Transaction> transactionList) {
        if (transactionList != null && !CollectionUtils.isEmpty(transactionList.getContent())) {
            Map<String, Object> result = new HashMap<>();
            List<TransactionDTO> transactionDTOList = mapperFacade.mapAsList(transactionList.getContent(), TransactionDTO.class);
            ResponseMessage<List<TransactionDTO>> responseMessage = new ResponseMessage<>();
            responseMessage.setData(transactionDTOList);
            responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
            result.put(RESULT_KEY, responseMessage);
            result.put(TOTAL_ITEM_KEY, transactionList.getTotalElements());
            return result;
        }
        return null;
    }
}

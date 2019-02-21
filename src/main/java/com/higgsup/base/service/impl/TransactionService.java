package com.higgsup.base.service.impl;

import com.higgsup.base.dto.TransactionDTO;
import com.higgsup.base.dto.base.IPagedResponse;
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

import java.util.List;

@Service
@Transactional
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final MapperFacade mapperFacade;

    public TransactionService(TransactionRepository transactionRepository, MapperFacade mapperFacade) {
        this.transactionRepository = transactionRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public IPagedResponse<List<TransactionDTO>> getTransactionList(Long userId, int page, int size) {
        Pageable sortedByShippingDate = PageRequest.of(page, size, Sort.by("shippingDate").descending());

        Page<Transaction> transactionList = transactionRepository.findByUserId(userId, sortedByShippingDate);
        if(transactionList != null && !CollectionUtils.isEmpty(transactionList.getContent())) {
            List<TransactionDTO> transactionDTOList = mapperFacade.mapAsList(transactionList.getContent(), TransactionDTO.class);
            IPagedResponse iPagedResponse = new IPagedResponse();
            ResponseMessage<List<TransactionDTO>> responseMessage = new ResponseMessage<>();
            responseMessage.setData(transactionDTOList);
            responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
            iPagedResponse.setPageIndex(page);
            iPagedResponse.setPageSize(size);
            iPagedResponse.setResponseMessage(responseMessage);
            iPagedResponse.setTotalItem(transactionList.getTotalElements());
            return iPagedResponse;
        }
        return null;
    }

    @Override
    public IPagedResponse<List<TransactionDTO>> fullTextSearch(Long userId, String textSearch, int page, int size) {
        Pageable sortedByShippingDate = PageRequest.of(page, size, Sort.by("shipping_date").descending());
        Page<Transaction> transactionList = transactionRepository.fullTextSearch(userId, textSearch, sortedByShippingDate);
        if(transactionList != null && !CollectionUtils.isEmpty(transactionList.getContent())) {
            List<TransactionDTO> transactionDTOList = mapperFacade.mapAsList(transactionList.getContent(), TransactionDTO.class);
            IPagedResponse iPagedResponse = new IPagedResponse();
            ResponseMessage<List<TransactionDTO>> responseMessage = new ResponseMessage<>();
            responseMessage.setData(transactionDTOList);
            responseMessage.setStatus(HttpStatus.OK.getReasonPhrase());
            iPagedResponse.setPageIndex(page);
            iPagedResponse.setPageSize(size);
            iPagedResponse.setResponseMessage(responseMessage);
            iPagedResponse.setTotalItem(transactionList.getTotalElements());
            return iPagedResponse;
        }
        return null;
    }
}

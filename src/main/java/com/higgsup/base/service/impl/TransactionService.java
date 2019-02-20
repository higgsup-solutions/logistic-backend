package com.higgsup.base.service.impl;

import com.higgsup.base.dto.TransactionDTO;
import com.higgsup.base.entity.Transaction;
import com.higgsup.base.repository.TransactionRepository;
import com.higgsup.base.service.ITransactionService;
import ma.glasnost.orika.MapperFacade;
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
    public List<TransactionDTO> getTransactionList(Long userId) {
        List<Transaction> transactionList = transactionRepository.findByUserId(userId);
        if(CollectionUtils.isEmpty(transactionList)) {
           return null;
        }
        return mapperFacade.mapAsList(transactionList, TransactionDTO.class);
    }

    @Override
    public List<TransactionDTO> fullTextSearch(Long userId, String textSearch) {
        List<Transaction> transactionList = transactionRepository.fullTextSearch(userId, textSearch);
        if(CollectionUtils.isEmpty(transactionList)) {
            return null;
        }
        return mapperFacade.mapAsList(transactionList, TransactionDTO.class);
    }
}

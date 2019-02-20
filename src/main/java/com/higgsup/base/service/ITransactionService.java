package com.higgsup.base.service;

import com.higgsup.base.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {

    List<TransactionDTO> getTransactionList(Long userId);

    List<TransactionDTO> fullTextSearch(Long userId, String textSearch);
}

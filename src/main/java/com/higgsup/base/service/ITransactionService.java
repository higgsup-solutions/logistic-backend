package com.higgsup.base.service;

import com.higgsup.base.dto.TransactionDTO;
import com.higgsup.base.dto.base.IPagedResponse;

import java.util.List;

public interface ITransactionService {

    IPagedResponse<List<TransactionDTO>> getTransactionList(Long userId, int page, int size);

    IPagedResponse<List<TransactionDTO>> fullTextSearch(Long userId, String textSearch, int page, int size);
}

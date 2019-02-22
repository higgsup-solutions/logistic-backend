package com.higgsup.base.service;

import java.util.Map;

public interface ITransactionService {

    Map<String, Object> getTransactionList(Long userId, int page, int size);

    Map<String, Object> fullTextSearch(Long userId, String textSearch, int page, int size);
}

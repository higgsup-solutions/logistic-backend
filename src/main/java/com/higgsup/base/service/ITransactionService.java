package com.higgsup.base.service;

import com.higgsup.base.dto.BookingDTO;
import com.higgsup.base.entity.LocationLog;
import com.higgsup.base.entity.Transaction;

import java.util.List;
import java.util.Map;

public interface ITransactionService {

    Map<String, Object> getTransactionList(Long userId, int page, int size);

    Map<String, Object> fullTextSearch(Long userId, String textSearch, int page, int size);

    Transaction saveBooking(BookingDTO bookingDTO, Long userId);

    List<LocationLog> getLocationLog(Long transactionId, Long userId);
}

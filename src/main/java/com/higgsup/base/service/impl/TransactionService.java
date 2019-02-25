package com.higgsup.base.service.impl;

import com.higgsup.base.dto.*;
import com.higgsup.base.entity.Transaction;
import com.higgsup.base.entity.TransactionDimension;
import com.higgsup.base.repository.CarrierRepository;
import com.higgsup.base.repository.PackageRepository;
import com.higgsup.base.repository.TransactionDimensionRepository;
import com.higgsup.base.repository.TransactionRepository;
import com.higgsup.base.service.ITransactionService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Transactional
public class TransactionService implements ITransactionService {

    public static final String RESULT_KEY = "result";
    public static final String TOTAL_ITEM_KEY = "totalItem";

    private final TransactionRepository transactionRepository;
    private final MapperFacade mapperFacade;
    private final CarrierService carrierService;
    private final CarrierRepository carrierRepository;
    private final PackageRepository packageRepository;
    private final TransactionDimensionRepository transactionDimensionRepository;


    public TransactionService(TransactionRepository transactionRepository, MapperFacade mapperFacade, CarrierService carrierService, CarrierRepository carrierRepository, PackageRepository packageRepository, TransactionDimensionRepository transactionDimensionRepository) {
        this.transactionRepository = transactionRepository;
        this.mapperFacade = mapperFacade;
        this.carrierService = carrierService;
        this.carrierRepository = carrierRepository;
        this.packageRepository = packageRepository;
        this.transactionDimensionRepository = transactionDimensionRepository;
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
            for (TransactionDTO transactionDTO: transactionDTOList
                 ) {
                List<TransactionDimension> transactionDimensionList = transactionDimensionRepository.findByTransactionId(transactionDTO.getId());
                if(!CollectionUtils.isEmpty(transactionDimensionList)) {
                    transactionDTO.setDimensionDTOList(mapperFacade.mapAsList(transactionDimensionList, TransactionDimensionDTO.class));
                }

            }
            result.put(RESULT_KEY, transactionDTOList);
            result.put(TOTAL_ITEM_KEY, transactionList.getTotalElements());
            return result;
        }
        return null;
    }

    @Override
    public Transaction saveBooking(BookingDTO bookingDTO, Long userId) {
        QuoteResultDTO quoteResultDTO = carrierService.showQuoteResult(bookingDTO.getQuoteRequest());

        Transaction transaction = new Transaction();

        transaction.setShippingDate(bookingDTO.getShippingDate());
        transaction.setCarierName(carrierRepository.getOne(bookingDTO.getQuoteRequest().getCarrierId()).getCarrierType());
        transaction.setPackageType(packageRepository.getOne(bookingDTO.getQuoteRequest().getPackageId()).getPackageType());
        transaction.setContactSender(bookingDTO.getSenderAddress().getContactName());
        transaction.setTrackingNo(getTrackingNumber());
        transaction.setBaseCharge(quoteResultDTO.getBaseCharge());
        transaction.setFuelSurcharge(quoteResultDTO.getFuelSurcharge());
        transaction.setTotalCharge(quoteResultDTO.getTotalCharge());
        transaction.setContentType(bookingDTO.getQuoteRequest().getContentType());
        transaction.setRecipientAddress1(bookingDTO.getRecipientAddress().getAddress1());
        if(bookingDTO.getRecipientAddress().getAddress2() != null) {
            transaction.setRecipientAddress2(bookingDTO.getRecipientAddress().getAddress2());
        }
        transaction.setRecipientCityName(bookingDTO.getRecipientAddress().getCityName());
        transaction.setRecipientCompany(bookingDTO.getRecipientAddress().getCompany());
        transaction.setRecipientContactName(bookingDTO.getRecipientAddress().getContactName());
        transaction.setRecipientCountryName(bookingDTO.getRecipientAddress().getCountryName());
        transaction.setRecipientPostalCode(bookingDTO.getRecipientAddress().getPostalCode());
        if(bookingDTO.getRecipientAddress().getStateProvince() != null) {
            transaction.setRecipientStateProvince(bookingDTO.getRecipientAddress().getStateProvince());
        }
        transaction.setRecipientPhoneNumber(bookingDTO.getRecipientAddress().getPhoneNumber());
        transaction.setSenderCityName(bookingDTO.getSenderAddress().getCityName());
        transaction.setSenderCompany(bookingDTO.getSenderAddress().getCompany());
        transaction.setSenderContactName(bookingDTO.getSenderAddress().getContactName());
        transaction.setSenderCountryName(bookingDTO.getSenderAddress().getCountryName());
        transaction.setSenderPostalCode(bookingDTO.getSenderAddress().getPostalCode());
        if(bookingDTO.getSenderAddress().getStateProvince() != null) {
            transaction.setSenderStateProvince(bookingDTO.getSenderAddress().getStateProvince());
        }
        transaction.setSenderPhoneNumber(bookingDTO.getSenderAddress().getPhoneNumber());
        transaction.setUserId(userId);
        transaction.setServiceType(bookingDTO.getServiceType());
        transaction.setTotalWeight(quoteResultDTO.getTotalWeight());

        Long transactionId = transactionRepository.save(transaction).getId();

        List<TransactionDimension> transactionDimensionList = new ArrayList<>();
        int peices = 0 ;
        for (DimensionDTO dimensionDTO: quoteResultDTO.getDimensions()
             ) {
            TransactionDimension transactionDimension = mapperFacade.map(dimensionDTO, TransactionDimension.class);
            transactionDimension.setTransactionId(transactionId);
            transactionDimensionList.add(transactionDimension);
            peices = peices + transactionDimension.getQuantity();
        }

        if(!CollectionUtils.isEmpty(transactionDimensionList)) {
            transactionDimensionRepository.saveAll(transactionDimensionList);
        }
        transaction.setPieces(peices);

        return transactionRepository.save(transaction);
    }

    private String getTrackingNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 8 character.
        return String.format("%08d", number);

    }
}

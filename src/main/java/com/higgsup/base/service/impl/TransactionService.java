package com.higgsup.base.service.impl;

import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.QuoteRequest;
import com.higgsup.base.dto.QuoteResultDTO;
import com.higgsup.base.entity.Carrier;
import com.higgsup.base.entity.PriceDetail;
import com.higgsup.base.repository.PriceDetailRepository;
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
    private PriceDetailRepository priceDetailRepository;
    private final MapperFacade mapperFacade;

    public TransactionService(TransactionRepository transactionRepository, MapperFacade mapperFacade, PriceDetailRepository priceDetailRepository) {
        this.transactionRepository = transactionRepository;
        this.mapperFacade = mapperFacade;
        this.priceDetailRepository = priceDetailRepository;
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
            result.put(RESULT_KEY, transactionDTOList);
            result.put(TOTAL_ITEM_KEY, transactionList.getTotalElements());
            return result;
        }
        return null;
    }

    @Override
    public QuoteResultDTO showQuoteResult(QuoteRequest quoteRequest) {
        QuoteResultDTO quoteResultDTO = new QuoteResultDTO();
        Double baseCharge = baseCharge(quoteRequest);
        quoteResultDTO.setBaseCharge(baseCharge);
        quoteResultDTO.setFuelSurcharge(fuelSurcharge(baseCharge));
        return null;
    }

    private double baseCharge (QuoteRequest quoteRequest){
        Carrier carrier = priceDetailRepository.getCarrier(quoteRequest.getCarrierId());
        String carrierType = carrier.getCarrierType().toLowerCase();
        Double baseCharge = null;

        //get sender address and receiver address to confirm zone price if have
        PriceDetail priceDetail = priceDetailRepository.getPriceOfZone(quoteRequest.getPackageId());

        // check carrier is domestic or international and calculate base charge
        for (DimensionDTO dimensionDTO : quoteRequest.getDimensionDTOList()){
            if (carrierType.contains("domestic")) {
//                  baseCharge = dimensionDTO.getWeights()
//                                * rateWeight(priceDetail,quoteRequest.getContentType(),dimensionDTO.getWeights())
//                                * priceDetail.getZonePrice().doubleValue();
            } else {
                 baseCharge = dimensionDTO.getWeights()
                               * rateWeight(priceDetail,quoteRequest.getContentType(),dimensionDTO.getWeights());
            }
        }
        return baseCharge;
    }

    private double rateWeight (PriceDetail priceDetail, String contentType, Double weight){
            if (weight <= 1 || contentType == "D"){
                return priceDetail.getBaseRate1().doubleValue();
            }else if (weight >1 && weight <= 5){
                return priceDetail.getBaseRate2().doubleValue();
            }else if (weight >5 && weight <= 30){
                return priceDetail.getBaseRate3().doubleValue();
            } else{
                return priceDetail.getBaseRate4().doubleValue();
            }
    }

    private double fuelSurcharge (double baseCharge){
        return (baseCharge * 4)/100;
    }
    private double dangerousCharge(){
        return 0;
    }
}

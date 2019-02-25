package com.higgsup.base.repository;

import com.higgsup.base.entity.TransactionDimension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDimensionRepository extends JpaRepository<TransactionDimension, Long> {

    List<TransactionDimension> findByTransactionId(Long transactionId);

}

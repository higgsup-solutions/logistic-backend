package com.higgsup.base.repository;

import com.higgsup.base.entity.LocationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationLogRepository extends JpaRepository<LocationLog, Long> {

    List<LocationLog> findByTransactionId(Long transactionId);
}

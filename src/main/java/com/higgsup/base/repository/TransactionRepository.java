package com.higgsup.base.repository;

import com.higgsup.base.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByUserId(Long userId, Pageable pageable);

    @Query(value = "select * from transaction WHERE MATCH (carier_name,package_type,contact_sender,tracking_no,content_type,recipient_address,recipient_city_name,recipient_company,recipient_contact_name,recipient_country_name,sender_address,sender_city_name,sender_company,sender_contact_name,sender_country_name) AGAINST (:textSearch IN NATURAL LANGUAGE MODE) and user_id = :userId",
            countQuery = "select count(*) from transaction WHERE MATCH (carier_name,package_type,contact_sender,tracking_no,content_type,recipient_address,recipient_city_name,recipient_company,recipient_contact_name,recipient_country_name,sender_address,sender_city_name,sender_company,sender_contact_name,sender_country_name) AGAINST (:textSearch IN NATURAL LANGUAGE MODE) and user_id = :userId",
            nativeQuery = true)
    Page<Transaction> fullTextSearch(@Param("userId") Long userId, @Param("textSearch") String textSearch, Pageable pageable);
}

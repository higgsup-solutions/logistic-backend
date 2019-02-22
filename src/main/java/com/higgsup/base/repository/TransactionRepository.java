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

    @Query(value = "select * from transaction where (match (recipient_address,recipient_city_name,recipient_company,recipient_contact_name,recipient_country_name,recipient_postal_code,recipient_state_province,sender_address,sender_city_name,sender_company,sender_contact_name,sender_country_name,sender_postal_code,sender_state_province,service_type) AGAINST (:textSearch IN NATURAL LANGUAGE MODE) or match (carier_name,package_type,contact_sender,tracking_no,dest_country,content_type,recipient_address1,recipient_address2,sender_address1,sender_address2) AGAINST (:textSearch  IN NATURAL LANGUAGE MODE)) and user_id = :userId",
            countQuery = "select count(*) from transaction where (match (recipient_address,recipient_city_name,recipient_company,recipient_contact_name,recipient_country_name,recipient_postal_code,recipient_state_province,sender_address,sender_city_name,sender_company,sender_contact_name,sender_country_name,sender_postal_code,sender_state_province,service_type) AGAINST (:textSearch IN NATURAL LANGUAGE MODE) or match (carier_name,package_type,contact_sender,tracking_no,dest_country,content_type,recipient_address1,recipient_address2,sender_address1,sender_address2) AGAINST (:textSearch  IN NATURAL LANGUAGE MODE)) and user_id = :userId",
            nativeQuery = true)
    Page<Transaction> fullTextSearch(@Param("userId") Long userId, @Param("textSearch") String textSearch, Pageable pageable);
}

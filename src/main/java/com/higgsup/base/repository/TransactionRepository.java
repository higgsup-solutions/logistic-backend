package com.higgsup.base.repository;

import com.higgsup.base.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByUserId(Long userId, Pageable pageable);

    @Query(value = "select * from transaction where (match (carier_name,service_type,package_type,contact_sender,tracking_no,content_type,sender_country_name,sender_company,sender_contact_name,sender_address1,sender_address2,sender_city_name,sender_state_province,sender_postal_code,sender_phone_number,recipient_country_name) AGAINST (:textSearch IN NATURAL LANGUAGE MODE) or match (recipient_company,recipient_contact_name,recipient_address1,recipient_address2,recipient_city_name,recipient_state_province,recipient_postal_code,recipient_phone_number) AGAINST (:textSearch  IN NATURAL LANGUAGE MODE)) and user_id = :userId",
            countQuery = "select count(*) from transaction where (match (carier_name,service_type,package_type,contact_sender,tracking_no,content_type,sender_country_name,sender_company,sender_contact_name,sender_address1,sender_address2,sender_city_name,sender_state_province,sender_postal_code,sender_phone_number,recipient_country_name) AGAINST (:textSearch IN NATURAL LANGUAGE MODE) or match (recipient_company,recipient_contact_name,recipient_address1,recipient_address2,recipient_city_name,recipient_state_province,recipient_postal_code,recipient_phone_number) AGAINST (:textSearch  IN NATURAL LANGUAGE MODE)) and user_id = :userId",
            nativeQuery = true)
    Page<Transaction> fullTextSearch(@Param("userId") Long userId, @Param("textSearch") String textSearch, Pageable pageable);

    Boolean existsByIdAndUserId(Long id, Long userId);
}

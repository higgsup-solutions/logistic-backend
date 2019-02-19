package com.higgsup.base.repository;

import com.higgsup.base.entity.Carrier;
import com.higgsup.base.entity.Dimention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    @Query(value = "SELECT * from carrier", nativeQuery = true)
    List<Carrier> getAllCarrierType();
}

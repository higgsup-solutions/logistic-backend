package com.higgsup.base.repository;

import com.higgsup.base.entity.ZonePrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZonePriceRepository extends JpaRepository<ZonePrice, Long> {
     ZonePrice findByCarrierIdAndName(Long carrierId, String name);

}


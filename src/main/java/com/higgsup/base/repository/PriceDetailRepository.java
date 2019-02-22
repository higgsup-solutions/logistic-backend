package com.higgsup.base.repository;

import com.higgsup.base.entity.Carrier;
import com.higgsup.base.entity.PriceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PriceDetailRepository extends JpaRepository<PriceDetail, Long> {
    @Query(value = "select select c.id, c.carrier_type " +
                    "from price_detail pd " +
                    "join package p on p.id = pd.package_id " +
                    "join carrier c on c.id = p.carrier_id " +
                    "where c.id =:carrierId;", nativeQuery = true)
    Carrier getCarrier(Long carrierId);

    @Query(value = "select pd.* from price_detail pd" +
            "JOIN city c on c.id = pd.address_from_id" +
            "where pd.package_id =:packageId;", nativeQuery = true)
    PriceDetail getPriceOfZone(Long packageId);

    Optional<PriceDetail> findByPackageId(Long packageId);
}


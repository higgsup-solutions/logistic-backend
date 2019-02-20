package com.higgsup.base.repository;

import com.higgsup.base.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Long> {
    @Query(value = "SELECT * from package where carrier_id =:carrierId", nativeQuery = true)
    List<Package> getpackageByCarrierId(Long carrierId);
}

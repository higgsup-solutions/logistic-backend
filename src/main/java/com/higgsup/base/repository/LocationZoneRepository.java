package com.higgsup.base.repository;


import com.higgsup.base.entity.LocationZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LocationZoneRepository extends JpaRepository<LocationZone, Long> {
    List<LocationZone> findByAddressFromIdAndAddressToId(Long addressFromId, Long addressToId);
}


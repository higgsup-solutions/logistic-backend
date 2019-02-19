package com.higgsup.base.repository;

import com.higgsup.base.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "select ci.id, ci.city_name, ci.postal_code, ci.state_province from city ci, country co where  co.id = :countryId and co.id= ci.country_id and ci.city_name like %:cityName%", nativeQuery = true)
    List<Object[]> getCity(@Param("countryId") Long countryId, @Param("cityName") String cityName);
}

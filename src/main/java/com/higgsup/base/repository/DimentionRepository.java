package com.higgsup.base.repository;

import com.higgsup.base.entity.Dimention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DimentionRepository extends JpaRepository<Dimention, Long> {
    @Query(value = "SELECT * from dimention WHERE user_id =:id ORDER BY last_updated ASC LIMIT :dimensionNumber", nativeQuery = true)
    List<Dimention> getDimentions(Long id, Integer dimensionNumber);
}

package com.higgsup.base.repository;

import com.higgsup.base.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    @Query(value = "select relationship FROM franchise where id =:id", nativeQuery = true)
    String findRelationshipById(Long id);

    @Query(value = "SELECT f.id from franchise f join `user` u on f.id = u.franchise_id where u.id =:userId", nativeQuery = true)
    Long findFranchiseIdByUserId(Long userId);

    @Query(value = "SELECT email from franchise", nativeQuery = true)
    List<String> emailList();

    @Query(value = "SELECT * from franchise", nativeQuery = true)
    List<Franchise> getAllFranchiseInfo();

    @Query(value = "SELECT * from franchise where relationship LIKE CONCAT('%',:id,'%')", nativeQuery = true)
    List<Franchise> getAllSubFranchiseInfo(Long id);

    @Query(value = "select f.started_date from user u " +
            "join franchise f on u.franchise_id = f.id " +
            " where f.relationship like CONCAT('%',:id,'%') and u.id =:userId", nativeQuery = true)
    String getStartDateOfFranchise(Long id, Long userId);
}

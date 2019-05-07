package com.higgsup.base.repository;

import com.higgsup.base.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    Boolean existsByEmail(String email);
    @Query(value = "select relationship FROM franchise where id =:id", nativeQuery = true)
    String findRelationshipById(Long id);

    @Query(value = "SELECT f.id from franchise f join `user` u on f.id = u.franchise_id where u.id =:userId", nativeQuery = true)
    Long findFranchiseIdByUserId(Long userId);

    @Query(value = "SELECT email from franchise", nativeQuery = true)
    List<String> emailList();

}

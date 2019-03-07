package com.higgsup.base.repository;

import com.higgsup.base.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query(value = "select ur.role from user u\n" +
            "\tjoin franchise f on u.franchise_id = f.id\n" +
            "\tjoin user_role ur on u.id = ur.app_user_id \n" +
            " where f.relationship like CONCAT('%',:id,'%') and u.id =:userId", nativeQuery = true)
    List<String> getUserRoleOfFranchise(Long id, Long userId);
}

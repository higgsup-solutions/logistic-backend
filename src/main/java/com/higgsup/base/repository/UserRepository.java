package com.higgsup.base.repository;

import com.higgsup.base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

  @Query("select u from User u left join fetch u.roles r where u.email=:email")
  User findByEmail(@Param("email") String email);

  @Query(value = "SELECT * FROM APP_USER WHERE UPPER(email) like 'N%' ", nativeQuery = true)
  List<User> findAllUserEmailStartWithN();

  List<User> findUserById(Long id);

    Boolean existsByEmail(String email);

}

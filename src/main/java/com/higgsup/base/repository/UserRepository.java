package com.higgsup.base.repository;

import com.higgsup.base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u left join fetch u.roles r where u.email=:email")
  User findByEmail(@Param("email") String email);

  @Query(value = "SELECT * FROM APP_USER WHERE UPPER(email) like 'N%' ", nativeQuery = true)
  List<User> findAllUserEmailStartWithN();

  List<User> findUserById(Long id);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT a.id, a.country_id, a.company, a.contact_name, a.sender_default, a.receipient_default, a.address1, a.address2, a.city_id, ci.city_name, ci.postal_code, co.country_name, ci.state_province, a.email, a.phone_number  FROM address_book a, country co, city ci  where user_id = :userId and a.city_id = ci.id and a.country_id = co.id", nativeQuery = true)
    List<Object[]> selectAddressList(@Param("userId") Long userId);
}

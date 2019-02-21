package com.higgsup.base.repository.impl;

import com.higgsup.base.model.UserAddress;
import com.higgsup.base.repository.result_mapper.JpaResultConverter;
import com.higgsup.base.repository.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<UserAddress> selectAddressList(Long userId) {
        String sql = "SELECT a.id, a.country_id, a.company, a.contact_name, a.sender_default, a.receipient_default," +
                " a.address1, a.address2, a.city_id, ci.city_name, ci.postal_code, co.country_name, ci.state_province, a.email, a.phone_number  " +
                "FROM address_book a, country co, city ci  where user_id = :userId and a.city_id = ci.id and a.country_id = co.id";

        Query query = em.createNativeQuery(sql).setParameter("userId", userId);
        return new JpaResultConverter().list(query,UserAddress.class);
    }


}

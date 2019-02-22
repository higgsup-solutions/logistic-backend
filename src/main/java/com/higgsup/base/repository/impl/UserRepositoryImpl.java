package com.higgsup.base.repository.impl;

import com.higgsup.base.model.UserAddress;
import com.higgsup.base.model.UserAddress2;
import com.higgsup.base.model.UserAddress3;
import com.higgsup.base.repository.result_mapper.JpaResultConverter;
import com.higgsup.base.repository.UserRepositoryCustom;
import org.hibernate.transform.Transformers;

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
                " a.address1, a.address2, a.city_name, a.postal_code, co.country_name, a.state, a.email, a.phone_number  " +
                "FROM address_book a, country co where user_id = :userId  and a.country_id = co.id";

        Query query = em.createNativeQuery(sql).setParameter("userId", userId);
        return new JpaResultConverter().list(query,UserAddress.class);
    }


    @Override
    public List<UserAddress3> selectAddressListUseTransformerWithNativeQuery(Long userId) {

        String queryNative = "SELECT a.id as id, a.country_id as countryId, a.company as company, a.contact_name as contactName, a.sender_default as senderDefault, a.receipient_default as receipientDefault," +
                " a.address1 as address1, a.address2 as address2, a.city_name as cityName, a.postal_code as postalCode, co.country_name as countryName, a.state as stateProvince, a.email as email, a.phone_number as phoneNumber  " +
                "FROM address_book a, country co where user_id = :userId  and a.country_id = co.id";

        // Note: If use native query: The data type of properties of DTO class must match with data type of column of table in database
        List<UserAddress3> userAddresses = em.createNativeQuery(queryNative).setParameter("userId", userId).unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( UserAddress3.class ) )
                .getResultList();
        return userAddresses;
    }

    public List<UserAddress2> selectAddressListUseTransformerWithHibernateQuery(Long userId) {
        String queryHibernate = "SELECT a.id as id, a.countryId as countryId, a.company as company, a.contactName as contactName, a.senderDefault as senderDefault, a.receipientDefault as receipientDefault," +
                " a.address1 as address1, a.address2 as address2, a.cityName as cityName, a.postalCode as cityName, co.countryName as countryName, a.stateProvince as stateProvince, a.email as email, a.phoneNumber as phoneNumber  " +
                "FROM AddressBook a, Country co where a.userId = :userId and a.countryId = co.id";

        // Note: If use hibernate query: The data type of properties of DTO class must match with data type of properties of entity class
        List<UserAddress2> userAddresses = em.createQuery(queryHibernate).setParameter("userId", userId).unwrap( org.hibernate.query.Query.class )
                .setResultTransformer( Transformers.aliasToBean( UserAddress2.class ) )
                .getResultList();
        return userAddresses;
    }

}

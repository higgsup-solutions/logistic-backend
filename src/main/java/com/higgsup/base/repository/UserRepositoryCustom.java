package com.higgsup.base.repository;

import com.higgsup.base.entity.User;
import com.higgsup.base.model.UserAddress;
import com.higgsup.base.model.UserAddress2;
import com.higgsup.base.model.UserAddress3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserAddress> selectAddressList(@Param("userId") Long userId);
    List<UserAddress3> selectAddressListUseTransformerWithNativeQuery(@Param("userId") Long userId);
    List<UserAddress2> selectAddressListUseTransformerWithHibernateQuery(@Param("userId") Long userId);
}

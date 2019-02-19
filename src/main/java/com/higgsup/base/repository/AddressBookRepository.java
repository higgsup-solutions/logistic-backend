package com.higgsup.base.repository;

import com.higgsup.base.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    Boolean existsByCompanyAndContactNameAndUserId(String company, String contactName, Long userId);
}

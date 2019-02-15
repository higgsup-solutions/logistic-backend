package com.higgsup.base.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address_book")
public class AddressBook {
    private long id;
    private Long userId;
    private Long countryId;
    private String userType;
    private String company;
    private String contactName;
    private Byte senderDefault;
    private Byte receipientDefault;
    private String address1;
    private String address2;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "country_id", nullable = true)
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "user_type", nullable = true, length = 2)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "company", nullable = true, length = 225)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "contact_name", nullable = true, length = 225)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Basic
    @Column(name = "sender_default", nullable = true)
    public Byte getSenderDefault() {
        return senderDefault;
    }

    public void setSenderDefault(Byte senderDefault) {
        this.senderDefault = senderDefault;
    }

    @Basic
    @Column(name = "receipient_default", nullable = true)
    public Byte getReceipientDefault() {
        return receipientDefault;
    }

    public void setReceipientDefault(Byte receipientDefault) {
        this.receipientDefault = receipientDefault;
    }

    @Basic
    @Column(name = "address1", nullable = true, length = 225)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "address2", nullable = true, length = 225)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBook that = (AddressBook) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(countryId, that.countryId) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(company, that.company) &&
                Objects.equals(contactName, that.contactName) &&
                Objects.equals(senderDefault, that.senderDefault) &&
                Objects.equals(receipientDefault, that.receipientDefault) &&
                Objects.equals(address1, that.address1) &&
                Objects.equals(address2, that.address2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, countryId, userType, company, contactName, senderDefault, receipientDefault, address1, address2);
    }
}

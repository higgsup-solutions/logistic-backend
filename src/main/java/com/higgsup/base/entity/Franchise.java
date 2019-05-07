package com.higgsup.base.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Franchise {
    private Long id;
    private String name;
    private String relationship;
    private String customerName;
    private String contactName;
    private String contactTitle;
    private String address;
    private Long countryId;
    private String cityName;
    private String fax;
    private String phone;
    private String stateCode;
    private String postalCode;
    private Timestamp startedDate;
    private String email;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "relationship", nullable = true)
    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Basic
    @Column(name = "customser_name", nullable = true)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "contact_name", nullable = true)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Basic
    @Column(name = "contact_title", nullable = true)
    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    @Basic
    @Column(name = "address", nullable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "contry_id", nullable = true)
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "city_name", nullable = true)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "fax", nullable = true)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "phone", nullable = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "state_code", nullable = true)
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Basic
    @Column(name = "postal_code", nullable = true)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "started_date", nullable = true)
    public Timestamp getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Timestamp startedDate) {
        this.startedDate = startedDate;
    }

    @Basic
    @Column(name = "email", nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Franchise)) return false;
        Franchise franchise = (Franchise) o;
        return id.equals(franchise.id) &&
                Objects.equals(name, franchise.name) &&
                Objects.equals(relationship, franchise.relationship) &&
                Objects.equals(customerName, franchise.customerName) &&
                Objects.equals(contactName, franchise.contactName) &&
                Objects.equals(contactTitle, franchise.contactTitle) &&
                Objects.equals(address, franchise.address) &&
                Objects.equals(countryId, franchise.countryId) &&
                Objects.equals(cityName, franchise.cityName) &&
                Objects.equals(fax, franchise.fax) &&
                Objects.equals(phone, franchise.phone) &&
                Objects.equals(stateCode, franchise.stateCode) &&
                Objects.equals(postalCode, franchise.postalCode) &&
                Objects.equals(startedDate, franchise.startedDate) &&
                Objects.equals(email, franchise.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, relationship, customerName, contactName, contactTitle, address, countryId, cityName, fax, phone, stateCode, postalCode, startedDate, email);
    }
}

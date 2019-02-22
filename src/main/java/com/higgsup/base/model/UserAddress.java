package com.higgsup.base.model;

import java.math.BigInteger;

public class UserAddress {

    private BigInteger id;
    private BigInteger countryId;
    private String company;
    private String contactName;
    private Boolean senderDefault;
    private Boolean receipientDefault;
    private String address1;
    private String address2;
    private String cityName;
    private String postalCode;
    private String countryName;
    private String stateProvince;
    private String email;
    private String phoneNumber;


    public UserAddress(BigInteger id, BigInteger countryId, String company,
                       String contactName, Boolean senderDefault, Boolean receipientDefault, String address1,
                       String address2, String cityName, String postalCode,
                       String countryName, String stateProvince, String email, String phoneNumber) {
        this.id = id;
        this.countryId = countryId;
        this.company = company;
        this.contactName = contactName;
        this.senderDefault = senderDefault;
        this.receipientDefault = receipientDefault;
        this.address1 = address1;
        this.address2 = address2;

        this.cityName = cityName;
        this.postalCode = postalCode;
        this.countryName = countryName;
        this.stateProvince = stateProvince;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCountryId() {
        return countryId;
    }

    public void setCountryId(BigInteger countryId) {
        this.countryId = countryId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Boolean getSenderDefault() {
        return senderDefault;
    }

    public void setSenderDefault(Boolean senderDefault) {
        this.senderDefault = senderDefault;
    }

    public Boolean getReceipientDefault() {
        return receipientDefault;
    }

    public void setReceipientDefault(Boolean receipientDefault) {
        this.receipientDefault = receipientDefault;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

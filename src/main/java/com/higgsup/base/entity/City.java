package com.higgsup.base.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class City {
    private long id;
    private Long countryId;
    private String cityName;
    private String postalCode;
    private String stateProvince;

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
    @Column(name = "country_id", nullable = true)
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "city_name", nullable = true, length = 225)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "postal_code", nullable = true, length = 225)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "state_province", nullable = true, length = 225)
    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(countryId, city.countryId) &&
                Objects.equals(cityName, city.cityName) &&
                Objects.equals(postalCode, city.postalCode) &&
                Objects.equals(stateProvince, city.stateProvince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryId, cityName, postalCode, stateProvince);
    }
}

package com.higgsup.base.entity;

import javax.persistence.*;

@Entity
public class LocationZone {
    private Long id;
    private Long addressFromId;
    private Long addressToId;
    private String zoneName;

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
    @Column(name = "address_from_id", nullable = true)
    public Long getAddressFromId() {
        return addressFromId;
    }

    public void setAddressFromId(Long addressFromId) {
        this.addressFromId = addressFromId;
    }

    @Basic
    @Column(name = "address_to_id", nullable = true)
    public Long getAddressToId() {
        return addressToId;
    }

    public void setAddressToId(Long addressToId) {
        this.addressToId = addressToId;
    }

    @Basic
    @Column(name = "zone_name", nullable = true)
    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}

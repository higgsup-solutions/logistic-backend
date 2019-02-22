package com.higgsup.base.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ZonePrice {
    private Long id;
    private String name;
    private Long carrierId;
    private BigDecimal zonePrice;

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
    @Column(name = "carrier_id", nullable = true)
    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    @Basic
    @Column(name = "zone_price", nullable = true)
    public BigDecimal getZonePrice() {
        return zonePrice;
    }

    public void setZonePrice(BigDecimal zonePrice) {
        this.zonePrice = zonePrice;
    }
}

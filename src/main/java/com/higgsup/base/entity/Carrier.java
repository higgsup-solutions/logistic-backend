package com.higgsup.base.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Carrier {
    private long id;
    private String carrierType;

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
    @Column(name = "carrier_type", nullable = true, length = 255)
    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrier carrier = (Carrier) o;
        return id == carrier.id &&
                Objects.equals(carrierType, carrier.carrierType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carrierType);
    }
}

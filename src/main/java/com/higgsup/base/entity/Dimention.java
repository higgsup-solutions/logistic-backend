package com.higgsup.base.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Dimention {
    private long id;
    private Long userId;
    private Double length;
    private Double weight;
    private Double height;
    private Byte dimentionDefault;

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
    @Column(name = "length", nullable = true, precision = 0)
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Basic
    @Column(name = "weight", nullable = true, precision = 0)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "height", nullable = true, precision = 0)
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Basic
    @Column(name = "dimention_default", nullable = true)
    public Byte getDimentionDefault() {
        return dimentionDefault;
    }

    public void setDimentionDefault(Byte dimentionDefault) {
        this.dimentionDefault = dimentionDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimention dimention = (Dimention) o;
        return id == dimention.id &&
                Objects.equals(userId, dimention.userId) &&
                Objects.equals(length, dimention.length) &&
                Objects.equals(weight, dimention.weight) &&
                Objects.equals(height, dimention.height) &&
                Objects.equals(dimentionDefault, dimention.dimentionDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, length, weight, height, dimentionDefault);
    }
}

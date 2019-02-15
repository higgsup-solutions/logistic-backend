package com.higgsup.base.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Package {
    private long id;
    private Long carrierId;
    private String packageType;
    private String contentType;

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
    @Column(name = "carrier_id", nullable = true)
    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    @Basic
    @Column(name = "package_type", nullable = true, length = 100)
    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    @Basic
    @Column(name = "Content_type", nullable = true, length = 50)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return id == aPackage.id &&
                Objects.equals(carrierId, aPackage.carrierId) &&
                Objects.equals(packageType, aPackage.packageType) &&
                Objects.equals(contentType, aPackage.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carrierId, packageType, contentType);
    }
}

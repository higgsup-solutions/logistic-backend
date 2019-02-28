package com.higgsup.base.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="USER")
@Data
public class User {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="password")
    private String password;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;


    @Column(name="address")
    private String address;

    @Column(name="language")
    private String language;

    @OneToMany
    @JoinColumn(name="APP_USER_ID", referencedColumnName="ID")
    private List<UserRole> roles;

    public User() { }

    public User(Long id,  String password, String email, List<UserRole> roles) {
        this.id = id;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }
    
 }

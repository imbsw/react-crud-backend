package com.example.vms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user",indexes = {@Index(name = "idx_user",columnList = "nic,email,phone_number")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name",unique=true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private String active;

    @Column(name = "AType")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middlName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "email",unique=true)
    private String email;

    @Column(name = "phone_number",unique=true)
    private String phoneNumber;

    @Column(name = "nic",unique=true)
    private String nic;

    @Column(name = "address")
    private String address;
}

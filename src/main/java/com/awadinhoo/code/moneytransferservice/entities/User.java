package com.awadinhoo.code.moneytransferservice.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "USERS")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "MOBILE_NUMBER", nullable = false, unique = true)
    private String mobileNumber;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "IS_DELETED", nullable = false)
    private Integer isDeleted = 0;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

}

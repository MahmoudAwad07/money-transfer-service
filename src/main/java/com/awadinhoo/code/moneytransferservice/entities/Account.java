package com.awadinhoo.code.moneytransferservice.entities;


import com.awadinhoo.code.moneytransferservice.enums.AccountStatus;
import com.awadinhoo.code.moneytransferservice.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "ACCOUNTS")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
    private UUID accountNumber = UUID.randomUUID();

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private AccountType accountType;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @Column(name = "CURRENCY", nullable = false)
    private Currency currency;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_STATUS", nullable = false)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @Column(name = "IS_DELETED", nullable = false)
    private Integer isDeleted = 0;

    @OneToMany(mappedBy = "sourceAccount" , fetch = FetchType.LAZY)
    private List<Transfer> sourceTransfers;

    @OneToMany(mappedBy = "targetAccount" , fetch = FetchType.LAZY)
    private List<Transfer> targetTransfers;
}

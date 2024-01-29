package com.awadinhoo.code.moneytransferservice.entities;


import com.awadinhoo.code.moneytransferservice.enums.TransferStatus;
import com.awadinhoo.code.moneytransferservice.enums.TransferType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "TRANSACTIONS")
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger transferId;

    @Column(name = "TRANSFER_NUMBER", nullable = false)
    private UUID transferNumber;

    @Column(name = "TRANSACTION_AMOUNT", nullable = false)
    private BigDecimal transferAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private TransferType transferType;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_STATUS", nullable = false)
    private TransferStatus transferStatus;

    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    private LocalDateTime transferDate = LocalDateTime.now();

    @Column(name = "REQUEST_SOURCE_ACCOUNT_ID")
    private String requestSourceAccountId;

    @Column(name = "REQUEST_TARGET_ACCOUNT_ID")
    private String requestTargetAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_ACCOUNT_ID")
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TARGET_ACCOUNT_ID")
    private Account targetAccount;

}

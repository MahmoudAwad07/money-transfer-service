package com.awadinhoo.code.moneytransferservice.dto;

import com.awadinhoo.code.moneytransferservice.enums.AccountStatus;
import com.awadinhoo.code.moneytransferservice.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record AccountResponse( Long accountId,
                               String accountNumber,
                               Long userId,
                               AccountType accountType,
                               BigDecimal balance,
                               Currency currency,
                               LocalDateTime createdDate,
                               AccountStatus accountStatus) {
}

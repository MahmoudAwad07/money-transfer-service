package com.awadinhoo.code.moneytransferservice.records;

import com.awadinhoo.code.moneytransferservice.enums.AccountType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;

public record AccountRequest(@NotNull Long userId ,
                            @NotNull AccountType accountType ,
                             @NotNull BigDecimal balance,
                             @NotNull Currency currency) {
}

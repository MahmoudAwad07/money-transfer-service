package com.awadinhoo.code.moneytransferservice.records;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;

public record CashTransferRequest(@NotNull Long userId,
                                  @NotEmpty String sourceAccountNumber,
                                  @NotEmpty String targetAccountNumber,
                                  @NotNull @Min(0) BigDecimal amount,
                                  @NotNull Currency currency,
                                  Boolean withInAccounts) {
}

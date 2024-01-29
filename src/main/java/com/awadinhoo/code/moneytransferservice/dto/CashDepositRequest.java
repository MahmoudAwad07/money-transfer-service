package com.awadinhoo.code.moneytransferservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;

public record CashDepositRequest(@NotNull Long userId,
                                 @NotEmpty String accountNumber,
                                 @NotNull @Min(0) BigDecimal amount,
                                 @NotNull Currency currency) {
}

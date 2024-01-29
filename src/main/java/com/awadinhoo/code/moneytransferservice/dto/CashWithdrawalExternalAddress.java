package com.awadinhoo.code.moneytransferservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CashWithdrawalExternalAddress(@NotEmpty String address,
                                            @NotNull BigDecimal amount) {
}

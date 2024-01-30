package com.awadinhoo.code.moneytransferservice.records;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record UserRecord(Long userId,
                         @NotEmpty @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String email,
                         @NotEmpty @Pattern(regexp = "[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s./0-9]*$") String mobileNumber,
                         @NotEmpty String fullName,
                         LocalDateTime createdDate) {
}

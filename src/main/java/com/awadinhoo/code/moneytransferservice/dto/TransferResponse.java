package com.awadinhoo.code.moneytransferservice.dto;

import java.util.UUID;

public record TransferResponse(UUID transferNumber,
                               String message) {
}

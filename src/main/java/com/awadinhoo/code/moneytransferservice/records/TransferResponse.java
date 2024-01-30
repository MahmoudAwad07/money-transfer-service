package com.awadinhoo.code.moneytransferservice.records;

import java.util.UUID;

public record TransferResponse(UUID transferNumber,
                               String message) {
}

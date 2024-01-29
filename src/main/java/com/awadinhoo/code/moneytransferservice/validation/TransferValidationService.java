package com.awadinhoo.code.moneytransferservice.validation;

import java.util.UUID;

public interface TransferValidationService {

    void checkPendingRequests(String accountNumber, UUID transferNumber);
    void removePendingRequest(String accountNumber);

}

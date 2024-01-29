package com.awadinhoo.code.moneytransferservice.validation.impl;

import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.exceptions.TransferMoneyCustomException;
import com.awadinhoo.code.moneytransferservice.validation.TransferValidationService;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {


    private final ConcurrentMap<String,UUID> servingRequests = new ConcurrentHashMap<>();


    @Override
    public void checkPendingRequests(String accountNumber, UUID transferNumber) {

        UUID existingTransferNumber = servingRequests.putIfAbsent(accountNumber, transferNumber);

        if(existingTransferNumber != null){
            throw new TransferMoneyCustomException(Constants.StatusMessages.ACCOUNT_HAS_REQUEST_UNDER_PROCESSING_MESSAGE);
        }
    }

    @Override
    public void removePendingRequest(String accountNumber) {
        servingRequests.remove(accountNumber);
    }
}

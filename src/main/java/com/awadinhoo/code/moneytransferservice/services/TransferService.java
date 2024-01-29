package com.awadinhoo.code.moneytransferservice.services;

import com.awadinhoo.code.moneytransferservice.dto.CashDepositRequest;
import com.awadinhoo.code.moneytransferservice.dto.CashTransferRequest;
import com.awadinhoo.code.moneytransferservice.dto.CashWithdrawalExternalAddress;
import com.awadinhoo.code.moneytransferservice.dto.CashWithdrawalRequest;

import java.util.UUID;

public interface TransferService {
    UUID depositCash(CashDepositRequest cashDepositRequest);
    UUID withdrawCash(CashWithdrawalRequest cashWithdrawalRequest);
    UUID transferCash(CashTransferRequest cashTransferRequest);
    UUID withdrawCashToExternalAddress(CashWithdrawalExternalAddress cashWithdrawalExternalAddress);
    String getTransferStatus(String transferNumber);

}

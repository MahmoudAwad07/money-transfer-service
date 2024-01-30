package com.awadinhoo.code.moneytransferservice.services;

import com.awadinhoo.code.moneytransferservice.records.CashDepositRequest;
import com.awadinhoo.code.moneytransferservice.records.CashTransferRequest;
import com.awadinhoo.code.moneytransferservice.records.CashWithdrawalExternalAddress;
import com.awadinhoo.code.moneytransferservice.records.CashWithdrawalRequest;

import java.util.UUID;

public interface TransferService {
    UUID depositCash(CashDepositRequest cashDepositRequest);
    UUID withdrawCash(CashWithdrawalRequest cashWithdrawalRequest);
    UUID transferCash(CashTransferRequest cashTransferRequest);
    UUID withdrawCashToExternalAddress(CashWithdrawalExternalAddress cashWithdrawalExternalAddress);
    String getTransferStatus(String transferNumber);

}

package com.awadinhoo.code.moneytransferservice.services.impl;

import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.records.CashDepositRequest;
import com.awadinhoo.code.moneytransferservice.records.CashTransferRequest;
import com.awadinhoo.code.moneytransferservice.records.CashWithdrawalExternalAddress;
import com.awadinhoo.code.moneytransferservice.records.CashWithdrawalRequest;
import com.awadinhoo.code.moneytransferservice.entities.Account;
import com.awadinhoo.code.moneytransferservice.entities.Transfer;
import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.enums.TransferStatus;
import com.awadinhoo.code.moneytransferservice.enums.TransferType;
import com.awadinhoo.code.moneytransferservice.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.moneytransferservice.exceptions.TransferMoneyCustomException;
import com.awadinhoo.code.moneytransferservice.repositories.TransferRepository;
import com.awadinhoo.code.moneytransferservice.services.AccountService;
import com.awadinhoo.code.moneytransferservice.services.TransferService;
import com.awadinhoo.code.moneytransferservice.services.WithdrawalService;
import com.awadinhoo.code.moneytransferservice.validation.AccountValidationService;
import com.awadinhoo.code.moneytransferservice.validation.TransferValidationService;
import com.awadinhoo.code.moneytransferservice.validation.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    private final UserValidationService userValidationService;
    private final TransferValidationService transferValidationService;
    private final AccountValidationService accountValidationService;
    private final ApplicationContext applicationContext;
    private final AccountService accountService;
    private final TransferRepository transferRepository;
    private final WithdrawalService withdrawalService;


    @Override
    public UUID depositCash(CashDepositRequest cashDepositRequest) {

        UUID transferNumber = UUID.randomUUID();
        Transfer transfer = null;
        Account account = null;
        boolean successRequest = true;
        String errorMessage = null;

        try {

            transferValidationService.checkPendingRequests(cashDepositRequest.accountNumber(), transferNumber);

            User user = userValidationService.checkUserIfExistAndGet(cashDepositRequest.userId());

            account = accountValidationService.
                    checkAccountRelatedToUserAndCheckCurrencyAndGet(cashDepositRequest.accountNumber(),
                            user, cashDepositRequest.currency());

            accountService.depositAccountBalance(account, cashDepositRequest.amount());

        } catch (ResourceNotFoundException ex){
            successRequest = false;
            errorMessage = ex.getMessage();
            throw new ResourceNotFoundException(ex.getMessage() , transferNumber.toString());
        } catch (Exception ex) {
            successRequest = false;
            errorMessage = ex.getMessage();
            throw new TransferMoneyCustomException(ex.getMessage() , transferNumber.toString());
        } finally {

            transfer = buildTransferEntity(transferNumber, null, cashDepositRequest.accountNumber(),
                    null, account, cashDepositRequest.amount(), TransferType.DEPOSIT, TransferStatus.SUCCESS, null);

            if(!successRequest){
                transfer.setTransferStatus(TransferStatus.FAILED);
                transfer.setErrorMessage(errorMessage);
            }
            transferRepository.save(transfer);
            transferValidationService.removePendingRequest(cashDepositRequest.accountNumber());
        }

        return transferNumber;
    }

    @Override
    public UUID withdrawCash(CashWithdrawalRequest cashWithdrawalRequest) {

        UUID transferNumber = UUID.randomUUID();
        Transfer transfer = null;
        Account account = null;
        boolean successRequest = true;
        String errorMessage = null;

        try {
            transferValidationService.checkPendingRequests(cashWithdrawalRequest.accountNumber(), transferNumber);

            User user = userValidationService.checkUserIfExistAndGet(cashWithdrawalRequest.userId());

            account = accountValidationService.
                    checkAccountRelatedToUserAndCheckCurrencyAndGet(cashWithdrawalRequest.accountNumber(),
                            user, cashWithdrawalRequest.currency());

            accountService.withdrawAccountBalance(account, cashWithdrawalRequest.amount());

        } catch (ResourceNotFoundException ex){
            successRequest = false;
            errorMessage = ex.getMessage();
            throw new ResourceNotFoundException(ex.getMessage() , transferNumber.toString());
        } catch (Exception ex) {
            successRequest = false;
            errorMessage = ex.getMessage();
            throw new TransferMoneyCustomException(ex.getMessage() , transferNumber.toString());
        } finally {

            transfer = buildTransferEntity(transferNumber, cashWithdrawalRequest.accountNumber(), null, account,
                    null, cashWithdrawalRequest.amount(), TransferType.WITHDRAWAL, TransferStatus.SUCCESS, null);

            if(!successRequest){
                transfer.setTransferStatus(TransferStatus.FAILED);
                transfer.setErrorMessage(errorMessage);
            }
            transferRepository.save(transfer);
            transferValidationService.removePendingRequest(cashWithdrawalRequest.accountNumber());
        }

        return transferNumber;
    }

    @Override
    public UUID transferCash(CashTransferRequest cashTransferRequest) {

        UUID transferNumber = UUID.randomUUID();
        Account sourceAccount = null;
        Account targetAccount = null;
        Transfer transfer = null;
        boolean successRequest = true;
        String errorMessage = null;

        try {

            transferValidationService.checkPendingRequests(cashTransferRequest.sourceAccountNumber(), transferNumber);
            transferValidationService.checkPendingRequests(cashTransferRequest.targetAccountNumber(), transferNumber);

            User user = userValidationService.checkUserIfExistAndGet(cashTransferRequest.userId());

            sourceAccount = accountValidationService.
                    checkAccountRelatedToUserAndCheckCurrencyAndGet(cashTransferRequest.sourceAccountNumber(),
                            user, cashTransferRequest.currency());

            if(cashTransferRequest.withInAccounts().equals(Boolean.TRUE)){
                targetAccount = accountValidationService.
                        checkAccountRelatedToUserAndCheckCurrencyAndGet(cashTransferRequest.targetAccountNumber(),
                                user, cashTransferRequest.currency());
            }else {
                targetAccount = accountValidationService.checkAccountIfExistsAndGet(UUID.fromString(cashTransferRequest.targetAccountNumber()));
                accountValidationService.checkValidCurrency(targetAccount, cashTransferRequest.currency());
            }

            accountService.withdrawAccountBalance(sourceAccount, cashTransferRequest.amount());
            accountService.depositAccountBalance(targetAccount, cashTransferRequest.amount());

        } catch (ResourceNotFoundException ex){
            successRequest = false;
            errorMessage = ex.getMessage();
            throw new ResourceNotFoundException(ex.getMessage() , transferNumber.toString());
        } catch (Exception ex) {
            successRequest = false;
            errorMessage = ex.getMessage();
            throw new TransferMoneyCustomException(ex.getMessage() , transferNumber.toString());
        } finally {

            transfer = buildTransferEntity(transferNumber, cashTransferRequest.sourceAccountNumber(), cashTransferRequest.targetAccountNumber(),
                    sourceAccount, targetAccount, cashTransferRequest.amount(), TransferType.TRANSFER, TransferStatus.SUCCESS, null);

            if(!successRequest){
                transfer.setTransferStatus(TransferStatus.FAILED);
                transfer.setErrorMessage(errorMessage);
            }

            transferRepository.save(transfer);
            transferValidationService.removePendingRequest(cashTransferRequest.sourceAccountNumber());
            transferValidationService.removePendingRequest(cashTransferRequest.targetAccountNumber());
        }

        return transferNumber;
    }

    @Override
    public UUID withdrawCashToExternalAddress(CashWithdrawalExternalAddress cashWithdrawalExternalAddress) {

        UUID transferNumber = UUID.randomUUID();

        withdrawalService.requestWithdrawal(new WithdrawalService.WithdrawalId(transferNumber),
                new WithdrawalService.Address(cashWithdrawalExternalAddress.address()),
                cashWithdrawalExternalAddress.amount());

        return transferNumber;
    }

    @Override
    public String getTransferStatus(String transferNumber) {
        return withdrawalService.getRequestState( new WithdrawalService.WithdrawalId(UUID.fromString(transferNumber))).toString();
    }


    private Transfer buildTransferEntity(UUID transferNumber, String requestSourceAccountNumber, String requestTargetAccountNumber,
                                         Account sourceAccount, Account targetAccount, BigDecimal amount,
                                         TransferType transferType, TransferStatus transferStatus, String errorMessage) {

        Transfer transfer = applicationContext.getBean(Transfer.class);
        transfer.setTransferNumber(transferNumber);
        transfer.setTransferType(transferType);
        transfer.setTransferAmount(amount);
        transfer.setTransferStatus(transferStatus);
        transfer.setErrorMessage(errorMessage);

        switch (transferType) {
            case DEPOSIT:
                transfer.setRequestTargetAccountId(requestTargetAccountNumber);
                transfer.setTargetAccount(targetAccount);
                break;
            case WITHDRAWAL:
                transfer.setRequestSourceAccountId(requestSourceAccountNumber);
                transfer.setSourceAccount(sourceAccount);
                break;
            case TRANSFER:
                transfer.setRequestSourceAccountId(requestSourceAccountNumber);
                transfer.setRequestTargetAccountId(requestTargetAccountNumber);
                transfer.setSourceAccount(sourceAccount);
                transfer.setTargetAccount(targetAccount);
                break;
            default:
                throw new TransferMoneyCustomException(Constants.StatusMessages.TRANSFER_TYPE_IS_NOT_SUPPORTED_MESSAGE + transferType);
        }
        return transfer;

    }
}

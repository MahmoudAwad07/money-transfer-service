package com.awadinhoo.code.moneytransferservice.validation.impl;

import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.entities.Account;
import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.moneytransferservice.exceptions.TransferMoneyCustomException;
import com.awadinhoo.code.moneytransferservice.repositories.AccountRepository;
import com.awadinhoo.code.moneytransferservice.validation.AccountValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountValidationServiceImpl implements AccountValidationService {


    private final AccountRepository accountRepository;

    @Override
    public Account checkAccountIfExistsAndGet(Long accountId) {

        return accountRepository.findByAccountIdAndIsDeleted(accountId, Constants.DeletionStatusValues.NOT_DELETED)
                .orElseThrow( () -> new ResourceNotFoundException(Constants.StatusMessages.ACCOUNT_NOT_FOUND_MESSAGE + accountId ));
    }

    @Override
    public Account checkAccountIfExistsAndGet(UUID accountNumber) {
        return accountRepository.findByAccountNumberAndIsDeleted(accountNumber, Constants.DeletionStatusValues.NOT_DELETED)
                .orElseThrow( () -> new ResourceNotFoundException(Constants.StatusMessages.ACCOUNT_NUMBER_NOT_FOUND_MESSAGE + accountNumber));
    }

    @Override
    public Account checkAccountRelatedToUserAndGet(String accountNumber, User user) {

        return user.getAccounts().stream()
                .filter( userAccount -> String.valueOf(userAccount.getAccountNumber()).equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Constants.StatusMessages.ACCOUNT_NOT_FOUND_FOR_ACCOUNT_NUMBER_MESSAGE + accountNumber));
    }

    @Override
    public Account checkAccountRelatedToUserAndCheckCurrencyAndGet(String accountNumber, User user, Currency currency) {

        Account account = checkAccountRelatedToUserAndGet(accountNumber, user);
        if(!account.getCurrency().equals(currency)){
            throw new TransferMoneyCustomException(Constants.StatusMessages.TRANSFER_MUST_BE_ON_THE_SAME_CURRENCY_MESSAGE);
        }
        return account;
    }

    @Override
    public void checkValidCurrency(Account account, Currency currency) {

        if(!account.getCurrency().equals(currency)){
            throw new TransferMoneyCustomException(Constants.StatusMessages.TRANSFER_MUST_BE_ON_THE_SAME_CURRENCY_MESSAGE);
        }
    }
}

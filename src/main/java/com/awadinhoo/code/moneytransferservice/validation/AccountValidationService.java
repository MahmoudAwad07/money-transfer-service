package com.awadinhoo.code.moneytransferservice.validation;

import com.awadinhoo.code.moneytransferservice.entities.Account;
import com.awadinhoo.code.moneytransferservice.entities.User;

import java.util.Currency;
import java.util.UUID;

public interface AccountValidationService {

    Account checkAccountIfExistsAndGet(Long accountId);
    Account checkAccountIfExistsAndGet(UUID accountNumber);
    Account checkAccountRelatedToUserAndGet(String accountNumber, User user);
    Account checkAccountRelatedToUserAndCheckCurrencyAndGet(String accountNumber, User user, Currency currency);
    void checkValidCurrency(Account account , Currency currency);
}

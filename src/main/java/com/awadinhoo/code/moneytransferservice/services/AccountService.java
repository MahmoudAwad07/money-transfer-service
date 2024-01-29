package com.awadinhoo.code.moneytransferservice.services;

import com.awadinhoo.code.moneytransferservice.dto.AccountRequest;
import com.awadinhoo.code.moneytransferservice.dto.AccountResponse;
import com.awadinhoo.code.moneytransferservice.entities.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountResponse createAccount(AccountRequest accountRequest);
    List<AccountResponse> getAllAccounts();
    AccountResponse getAccountById(Long accountId);
    List<AccountResponse> getAccountsByUserId(Long userId);
    void deleteAccount(Long accountId);
    void suspendAccount(Long accountId);
    void depositAccountBalance(Account account, BigDecimal amount);
    void withdrawAccountBalance(Account account, BigDecimal amount);
}

package com.awadinhoo.code.moneytransferservice.services.impl;

import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.dto.AccountRequest;
import com.awadinhoo.code.moneytransferservice.dto.AccountResponse;
import com.awadinhoo.code.moneytransferservice.entities.Account;
import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.enums.AccountStatus;
import com.awadinhoo.code.moneytransferservice.mappers.AccountMapper;
import com.awadinhoo.code.moneytransferservice.repositories.AccountRepository;
import com.awadinhoo.code.moneytransferservice.services.AccountService;
import com.awadinhoo.code.moneytransferservice.services.UserService;
import com.awadinhoo.code.moneytransferservice.validation.AccountValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserService userService;
    private final AccountValidationService accountValidationService;

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {

        User user = userService.getUserById(accountRequest.userId());
        Account account = accountMapper.getAccountEntityFromAccountRequest(accountRequest);
        account.setUser(user);

        return accountMapper.getAccountResponseFromEntity(accountRepository.save(account));
    }

    @Override
    public List<AccountResponse> getAllAccounts() {

        Optional<List<Account>> optionalAccounts = accountRepository.findAllByIsDeleted(Constants.DeletionStatusValues.NOT_DELETED);
        return accountMapper.getListAccountResponseFromAccountEntities(optionalAccounts.orElse(new ArrayList<>()));
    }

    @Override
    public AccountResponse getAccountById(Long accountId) {

        return accountMapper.getAccountResponseFromEntity(accountValidationService.checkAccountIfExistsAndGet(accountId));
    }

    @Override
    public List<AccountResponse> getAccountsByUserId(Long userId) {

        User user = userService.getUserById(userId);
        return accountMapper.getListAccountResponseFromAccountEntities(
                accountRepository.findAllByUserIdAndIsDeleted(userId, Constants.DeletionStatusValues.NOT_DELETED)
                .orElse(new ArrayList<>()));
    }

    @Override
    public void deleteAccount(Long accountId) {

        Account account = accountValidationService.checkAccountIfExistsAndGet(accountId);
        account.setIsDeleted(Constants.DeletionStatusValues.DELETED);
        account.setAccountStatus(AccountStatus.INACTIVE);
        account.setUser(null);

        accountRepository.save(account);
    }

    @Override
    public void suspendAccount(Long accountId) {

        Account account = accountValidationService.checkAccountIfExistsAndGet(accountId);
        account.setAccountStatus(AccountStatus.SUSPENDED);

        accountRepository.save(account);
    }

    @Override
    public void depositAccountBalance(Account account, BigDecimal amount) {
        BigDecimal newAmount = account.getBalance().add(amount);
        account.setBalance(newAmount);

        accountRepository.save(account);
    }

    @Override
    public void withdrawAccountBalance(Account account, BigDecimal amount) {
        BigDecimal newAmount = account.getBalance().subtract(amount);
        account.setBalance(newAmount);

        accountRepository.save(account);
    }
}

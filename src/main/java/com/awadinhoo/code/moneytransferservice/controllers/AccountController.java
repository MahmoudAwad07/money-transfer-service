package com.awadinhoo.code.moneytransferservice.controllers;


import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.dto.AccountRequest;
import com.awadinhoo.code.moneytransferservice.dto.AccountResponse;
import com.awadinhoo.code.moneytransferservice.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/v1/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest) {

        AccountResponse createdAccount = accountService.createAccount(accountRequest);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(){

        List<AccountResponse> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable("accountId") Long accountId){

        AccountResponse account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<AccountResponse>> getAccountsByUserId(@PathVariable("userId") Long userId){

        List<AccountResponse> accounts = accountService.getAccountsByUserId(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PatchMapping("/{accountId}")
    public ResponseEntity<String> suspendAccount(@PathVariable("accountId") Long accountId){

        accountService.suspendAccount(accountId);
        return new ResponseEntity<>(Constants.StatusMessages.ACCOUNT_SUSPENDED_SUCCESSFULLY_MESSAGE + accountId, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteFleet(@PathVariable("accountId") Long accountId) {

        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(Constants.StatusMessages.ACCOUNT_DELETED_SUCCESSFULLY_MESSAGE + accountId, HttpStatus.OK);
    }


}

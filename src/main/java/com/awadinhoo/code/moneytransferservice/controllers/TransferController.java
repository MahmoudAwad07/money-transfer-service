package com.awadinhoo.code.moneytransferservice.controllers;


import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.dto.*;
import com.awadinhoo.code.moneytransferservice.services.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/transfers")
@RestController
@RequiredArgsConstructor
public class TransferController {


    private final TransferService transferService;

    @PostMapping("/deposit")
    public ResponseEntity<TransferResponse> depositCash(@Valid @RequestBody CashDepositRequest cashDepositRequest) {

        UUID transferNumber = transferService.depositCash(cashDepositRequest);
        return new ResponseEntity<>(new TransferResponse(transferNumber, Constants.StatusMessages.CASH_DEPOSITED_SUCCESSFULLY_MESSAGE), HttpStatus.OK);

    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransferResponse> withdrawCash(@Valid @RequestBody CashWithdrawalRequest cashWithdrawalRequest) {

        UUID transferNumber = transferService.withdrawCash(cashWithdrawalRequest);
        return new ResponseEntity<>(new TransferResponse(transferNumber, Constants.StatusMessages.CASH_WITHDRAWN_SUCCESSFULLY_MESSAGE), HttpStatus.OK);
    }


    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transferCash(@Valid @RequestBody CashTransferRequest cashTransferRequest) {

        UUID transferNumber = transferService.transferCash(cashTransferRequest);
        return new ResponseEntity<>(new TransferResponse(transferNumber, Constants.StatusMessages.CASH_TRANSFER_SUCCESSFULLY_MESSAGE), HttpStatus.OK);
    }

    @PostMapping("/withdrawalToExternalAddress")
    public ResponseEntity<TransferResponse> withdrawCashToExternalAddress(@Valid @RequestBody CashWithdrawalExternalAddress cashWithdrawalExternalAddress) {

        UUID transferNumber = transferService.withdrawCashToExternalAddress(cashWithdrawalExternalAddress);
        return new ResponseEntity<>(new TransferResponse(transferNumber, Constants.StatusMessages.CASH_WITHDRAWN_SUCCESSFULLY_MESSAGE), HttpStatus.OK);
    }

    @GetMapping("/transferStatus/{transferNumber}")
    public ResponseEntity<TransferStatusResponse> getTransferStatus(@PathVariable("transferNumber") String transferNumber) {

        String transferStatus = transferService.getTransferStatus(transferNumber);
        return new ResponseEntity<>(new TransferStatusResponse(transferNumber, transferStatus), HttpStatus.OK);
    }
}

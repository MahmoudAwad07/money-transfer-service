package com.awadinhoo.code.moneytransferservice.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TransferMoneyCustomException extends RuntimeException {

    private String transferNumber;
    public TransferMoneyCustomException(String message) {
        super(message);
    }

    public TransferMoneyCustomException(String message, String transferNumber) {
        super(message);
        this.transferNumber = transferNumber;
    }
}

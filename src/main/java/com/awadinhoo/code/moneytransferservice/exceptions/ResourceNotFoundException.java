package com.awadinhoo.code.moneytransferservice.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String transferNumber;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, String transferNumber) {
        super(message);
        this.transferNumber = transferNumber;
    }
}

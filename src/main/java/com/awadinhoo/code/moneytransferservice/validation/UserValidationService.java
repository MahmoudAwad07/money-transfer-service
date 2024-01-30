package com.awadinhoo.code.moneytransferservice.validation;

import com.awadinhoo.code.moneytransferservice.entities.User;

public interface UserValidationService {
    User checkUserIfExistAndGet(Long userId);
    void checkUserIfExistsByEmailOrMobile(String email, String mobileNumber);
    void checkIfAnotherUserExistsByEmailOrMobile(Long userId, String email, String mobileNumber);
}

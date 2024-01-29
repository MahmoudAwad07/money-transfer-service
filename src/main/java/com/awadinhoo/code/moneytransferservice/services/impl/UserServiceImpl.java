package com.awadinhoo.code.moneytransferservice.services.impl;

import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.services.UserService;
import com.awadinhoo.code.moneytransferservice.validation.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserValidationService userValidationService;

    @Override
    public User getUserById(Long userId) {
        return userValidationService.checkUserIfExistAndGet(userId);
    }
}

package com.awadinhoo.code.moneytransferservice.validation.impl;

import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.moneytransferservice.repositories.UserRepository;
import com.awadinhoo.code.moneytransferservice.validation.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserValidationServiceImpl implements UserValidationService {

    private final UserRepository userRepository;

    @Override
    public User checkUserIfExistAndGet(Long userId) {

       return userRepository.findByUserIdAndIsDeleted(userId, Constants.DeletionStatusValues.NOT_DELETED)
               .orElseThrow( () -> new ResourceNotFoundException(Constants.StatusMessages.USER_NOT_FOUND_MESSAGE + userId ));
    }
}

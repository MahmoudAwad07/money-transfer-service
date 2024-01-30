package com.awadinhoo.code.moneytransferservice.validation.impl;

import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.moneytransferservice.exceptions.TransferMoneyCustomException;
import com.awadinhoo.code.moneytransferservice.repositories.UserRepository;
import com.awadinhoo.code.moneytransferservice.validation.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserValidationServiceImpl implements UserValidationService {

    private final UserRepository userRepository;

    @Override
    public User checkUserIfExistAndGet(Long userId) {

        if(userId == null)
            throw new TransferMoneyCustomException(Constants.StatusMessages.USER_ID_MUST_BE_NOT_NULL_MESSAGE);

       return userRepository.findByUserIdAndIsDeleted(userId, Constants.DeletionStatusValues.NOT_DELETED)
               .orElseThrow( () -> new ResourceNotFoundException(Constants.StatusMessages.USER_NOT_FOUND_MESSAGE + userId ));
    }

    @Override
    public void checkUserIfExistsByEmailOrMobile(String email, String mobileNumber) {
        Optional<User> optionalUser = userRepository.findByEmailOrMobileNumberAndIsDeleted(email, mobileNumber, Constants.DeletionStatusValues.NOT_DELETED);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            if(user.getEmail().equalsIgnoreCase(email)){
                throw new TransferMoneyCustomException(Constants.StatusMessages.USER_ALREADY_FOUND_WITH_THIS_EMAIL_MESSAGE + email);
            }

            if(user.getMobileNumber().equals(mobileNumber)){
                throw new TransferMoneyCustomException(Constants.StatusMessages.USER_ALREADY_FOUND_WITH_THIS_MOBILE_NUMBER_MESSAGE + mobileNumber);
            }
        }
    }

    @Override
    public void checkIfAnotherUserExistsByEmailOrMobile(Long userId, String email, String mobileNumber) {

        Optional<User> optionalUser = userRepository.findByEmailOrMobileNumberAndIsDeleted(email, mobileNumber, Constants.DeletionStatusValues.NOT_DELETED);

        if(optionalUser.isPresent() && !optionalUser.get().getUserId().equals(userId)){
            User user = optionalUser.get();

            if(user.getEmail().equalsIgnoreCase(email)){
                throw new TransferMoneyCustomException(Constants.StatusMessages.USER_ALREADY_FOUND_WITH_THIS_EMAIL_MESSAGE + email);
            }

            if(user.getMobileNumber().equals(mobileNumber)){
                throw new TransferMoneyCustomException(Constants.StatusMessages.USER_ALREADY_FOUND_WITH_THIS_MOBILE_NUMBER_MESSAGE + mobileNumber);
            }
        }
    }
}

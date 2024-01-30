package com.awadinhoo.code.moneytransferservice.services.impl;

import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.mappers.UserMapper;
import com.awadinhoo.code.moneytransferservice.records.UserRecord;
import com.awadinhoo.code.moneytransferservice.repositories.UserRepository;
import com.awadinhoo.code.moneytransferservice.services.UserService;
import com.awadinhoo.code.moneytransferservice.validation.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserValidationService userValidationService;
    private final UserMapper userMapper;

    @Override
    public UserRecord registerUser(UserRecord userRecord) {

        userValidationService.checkUserIfExistsByEmailOrMobile(userRecord.email(), userRecord.mobileNumber());
        User userEntity = userMapper.getUserEntityFromUserRecord(userRecord);
        userEntity.setUserId(null);
        userEntity.setCreatedDate(LocalDateTime.now());

        return userMapper.getUserRecordFromEntity(userRepository.save(userEntity));
    }
    @Override
    public UserRecord getUserById(Long userId) {
        return userMapper.getUserRecordFromEntity(userValidationService.checkUserIfExistAndGet(userId));
    }

    @Override
    public List<UserRecord> getAllUsers() {

        return userMapper.getListUserRecordsFromUserEntities(userRepository.
                findAllByIsDeleted(Constants.DeletionStatusValues.NOT_DELETED).orElse(new ArrayList<>()));
    }

    @Override
    public UserRecord updateUser(UserRecord userRecord) {

        userValidationService.checkIfAnotherUserExistsByEmailOrMobile(userRecord.userId(), userRecord.email(), userRecord.mobileNumber());

        User user = userValidationService.checkUserIfExistAndGet(userRecord.userId());
        user.setEmail(userRecord.email());
        user.setMobileNumber(userRecord.mobileNumber());
        user.setFullName(userRecord.fullName());

        return userMapper.getUserRecordFromEntity(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userValidationService.checkUserIfExistAndGet(userId);
        user.setIsDeleted(Constants.DeletionStatusValues.DELETED);
        user.setAccounts(null);

        userRepository.save(user);
    }
}

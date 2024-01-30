package com.awadinhoo.code.moneytransferservice.services;

import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.records.UserRecord;

import java.util.List;


public interface UserService {
    UserRecord registerUser(UserRecord userRecord);
    List<UserRecord> getAllUsers();
    UserRecord updateUser(UserRecord userRecord);
    UserRecord getUserById(Long userId);
    void deleteUser(Long userId);

}

package com.awadinhoo.code.moneytransferservice.controllers;


import com.awadinhoo.code.moneytransferservice.constants.Constants;
import com.awadinhoo.code.moneytransferservice.records.UserRecord;
import com.awadinhoo.code.moneytransferservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRecord> registerUser(@Valid @RequestBody UserRecord userRecord) {

        UserRecord registeredUserRecord = userService.registerUser(userRecord);
        return new ResponseEntity<>(registeredUserRecord, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserRecord>> getAllUsers(){

        List<UserRecord> userRecords = userService.getAllUsers();
        return new ResponseEntity<>(userRecords, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRecord> getUserById(@PathVariable("userId") Long userId){

        UserRecord userRecord = userService.getUserById(userId);
        return new ResponseEntity<>(userRecord, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserRecord> updateUser(@Valid @RequestBody UserRecord userRecord){

        UserRecord updatedUserRecord = userService.updateUser(userRecord);
        return new ResponseEntity<>(updatedUserRecord, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {

        userService.deleteUser(userId);
        return new ResponseEntity<>(Constants.StatusMessages.USER_DELETED_SUCCESSFULLY_MESSAGE + userId, HttpStatus.OK);
    }



}

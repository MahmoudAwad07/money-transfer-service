package com.awadinhoo.code.moneytransferservice.mappers;


import com.awadinhoo.code.moneytransferservice.entities.Account;
import com.awadinhoo.code.moneytransferservice.entities.User;
import com.awadinhoo.code.moneytransferservice.records.AccountRequest;
import com.awadinhoo.code.moneytransferservice.records.AccountResponse;
import com.awadinhoo.code.moneytransferservice.records.UserRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    User getUserEntityFromUserRecord(UserRecord userRecord);
    UserRecord getUserRecordFromEntity(User user);
    List<UserRecord> getListUserRecordsFromUserEntities(List<User> users);

}

package com.awadinhoo.code.moneytransferservice.mappers;


import com.awadinhoo.code.moneytransferservice.records.AccountRequest;
import com.awadinhoo.code.moneytransferservice.records.AccountResponse;
import com.awadinhoo.code.moneytransferservice.entities.Account;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountMapper {

    Account getAccountEntityFromAccountRequest(AccountRequest accountRequest);
    @Mapping(target = "accountNumber", source = "accountNumber", qualifiedByName = "getStringAccountNumber")
    AccountResponse getAccountResponseFromEntity(Account account);
    List<AccountResponse> getListAccountResponseFromAccountEntities(List<Account> accounts);
    @Named("getStringAccountNumber")
    default String getStringAccountNumber(UUID accountNumber) {
        return accountNumber.toString();
    }
}

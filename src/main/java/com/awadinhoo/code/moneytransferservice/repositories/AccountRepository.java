package com.awadinhoo.code.moneytransferservice.repositories;

import com.awadinhoo.code.moneytransferservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account , Long> {

    Optional<List<Account>> findAllByIsDeleted(Integer isDeleted);
    Optional<Account> findByAccountIdAndIsDeleted(Long accountId, Integer isDeleted);
    Optional<Account> findByAccountNumberAndIsDeleted(UUID accountNumber, Integer isDeleted);
    @Query("SELECT acc FROM Account acc WHERE acc.user.userId = :userId AND acc.isDeleted = :isDeleted")
    Optional<List<Account>> findAllByUserIdAndIsDeleted(Long userId, Integer isDeleted);

}

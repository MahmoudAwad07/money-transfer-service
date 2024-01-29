package com.awadinhoo.code.moneytransferservice.repositories;

import com.awadinhoo.code.moneytransferservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserIdAndIsDeleted(Long userId , Integer isDeleted);

}

package com.awadinhoo.code.moneytransferservice.repositories;

import com.awadinhoo.code.moneytransferservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserIdAndIsDeleted(Long userId , Integer isDeleted);
    Optional<User> findByEmailOrMobileNumberAndIsDeleted(String email, String mobileNumber, Integer isDeleted);
    Optional<List<User>> findAllByIsDeleted(Integer isDeleted);

}

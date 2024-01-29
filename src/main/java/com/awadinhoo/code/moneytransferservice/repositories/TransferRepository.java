package com.awadinhoo.code.moneytransferservice.repositories;

import com.awadinhoo.code.moneytransferservice.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long > {


}

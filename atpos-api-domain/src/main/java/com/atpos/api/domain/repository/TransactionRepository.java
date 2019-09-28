package com.atpos.api.domain.repository;

import com.atpos.api.domain.entity.Transaction;
import com.atpos.api.domain.entity.key.TransactionKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, TransactionKey> {

}

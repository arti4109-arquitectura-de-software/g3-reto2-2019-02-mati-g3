package com.atpos.api.business.service;

import com.atpos.api.domain.dto.transaction.TransactionDTO;

public interface TransactionService {

    String createTransaction(TransactionDTO transactionDTO) throws Exception ;
}

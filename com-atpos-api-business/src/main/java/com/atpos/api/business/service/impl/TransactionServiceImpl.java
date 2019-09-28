package com.atpos.api.business.service.impl;

import com.atpos.api.business.builder.ObjectBuilder;
import com.atpos.api.business.service.TransactionService;
import com.atpos.api.domain.dto.transaction.ItemSaleDTO;
import com.atpos.api.domain.dto.transaction.TransactionDTO;
import com.atpos.api.domain.entity.ItemSale;
import com.atpos.api.domain.entity.Transaction;
import com.atpos.api.domain.repository.ItemSaleRepository;
import com.atpos.api.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.atpos.api.commons.costant.Constants.SUCCESS_MESSAGE;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private ObjectBuilder objectBuilder;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    @Override
    public String createTransaction(TransactionDTO transactionDTO) throws Exception{

        Transaction transaction = objectBuilder.map(transactionDTO, Transaction.class);

        transactionRepository.save(transaction);

        if(!CollectionUtils.isEmpty(transactionDTO.getItems())){

            List<ItemSale> items = objectBuilder.mapAll(transactionDTO.getItems(), ItemSale.class);
            itemSaleRepository.saveAll(items);
        }

        System.out.println("createTransaction Executing thread name - " + Thread.currentThread().getName());

        return SUCCESS_MESSAGE;
    }
}

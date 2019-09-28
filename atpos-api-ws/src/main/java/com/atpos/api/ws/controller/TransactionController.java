package com.atpos.api.ws.controller;

import com.atpos.api.business.service.TransactionService;
import com.atpos.api.domain.dto.ResponseDTO;
import com.atpos.api.domain.dto.transaction.TransactionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/v1/transaction", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> createBand(@RequestBody @Valid TransactionDTO transactionDTO) throws Exception {
        return ResponseEntity.ok(
                ResponseDTO.apply(transactionService.createTransaction(transactionDTO)));
    }

}

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
public class StatusController {

    @RequestMapping(value = "/v1/checkStatus", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> createBand() throws Exception {
        return ResponseEntity.ok(
                ResponseDTO.apply("I am OK"));
    }

}

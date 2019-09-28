package com.atpos.api.domain.dto.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    private String invoiceCode;

    private Long companyCode;

    private Long branchCode;

    private Long terminalCode;

    private Long cashierCode;

    private BigDecimal ivaValue;

    private BigDecimal totalValue;

    private String invoiceDate;

    private List<ItemSaleDTO> items;

}

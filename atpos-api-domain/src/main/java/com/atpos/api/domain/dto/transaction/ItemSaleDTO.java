package com.atpos.api.domain.dto.transaction;

import com.atpos.api.domain.entity.key.TransactionKey;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemSaleDTO {

    private Long invoiceCode;

    private Long companyCode;

    private Long branchCode;

    private Long terminalCode;

    private String productCode;

    private Long quantity;

    private String productDescription;

    private BigDecimal ivaValue;

    private BigDecimal totalValue;

}

package com.atpos.api.domain.entity.key;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ItemSaleKey implements Serializable {

    private Long invoiceCode;
    private Long companyCode;
    private Long branchCode;
    private Long terminalCode;
    private String productCode;

}

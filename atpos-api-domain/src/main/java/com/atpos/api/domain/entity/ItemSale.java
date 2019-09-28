package com.atpos.api.domain.entity;

import com.atpos.api.domain.entity.key.ItemSaleKey;
import com.atpos.api.domain.entity.key.TransactionKey;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@IdClass(ItemSaleKey.class)
@Table(name = "item_sale", schema = "atpos")
public class ItemSale {

    @Id
    @Column(name = "invoice_code")
    private Long invoiceCode;

    @Id
    @Column(name = "company_code")
    private Long companyCode;

    @Id
    @Column(name = "branch_code")
    private Long branchCode;

    @Id
    @Column(name = "terminal_code")
    private Long terminalCode;

    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "iva_value")
    private BigDecimal ivaValue;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "start_date", updatable = false)
    private Timestamp startDate;

    @Column(name = "modification_date", insertable = false)
    private Timestamp modificationDate;
}

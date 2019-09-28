package com.atpos.api.domain.entity;

import com.atpos.api.domain.entity.key.TransactionKey;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@IdClass(TransactionKey.class)
@Table(name = "transaction", schema = "atpos")
public class Transaction {

    @Id
    @Column(name = "invoice_code")
    private String invoiceCode;

    @Id
    @Column(name = "company_code")
    private Long companyCode;

    @Id
    @Column(name = "branch_code")
    private Long branchCode;

    @Id
    @Column(name = "terminal_code")
    private Long terminalCode;

    @Column(name = "cashier_code")
    private Long cashierCode;

    @Column(name = "iva_value")
    private BigDecimal ivaValue;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "invoice_date")
    private Date invoiceDate;

    @Column(name = "start_date", updatable = false)
    private Timestamp startDate;

    @Column(name = "modification_date", insertable = false)
    private Timestamp modificationDate;
}

package com.banquito.core.productsaccounts.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT_ACCOUNT")
@Data
@NoArgsConstructor
public class ProductAccount {
    
    @Id
    @Column(name = "ID_PRODUCT_ACCOUNT", nullable = false)
    private String id;
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", length = 500, nullable = false)
    private String description;
    @Column(name = "MINIMUN_BALANCE", precision = 4, scale=2, nullable = false)
    private BigDecimal minimunBalance;
    @Column(name = "PAY_INTEREST", length = 1, nullable = false)
    private String payInterest;
    @Column(name = "ACCEPTS_CHECKS", length = 1, nullable = false)
    private String acceptsChecks;
    @Column(name = "STATE", length = 3, nullable = false)
    private String state;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;
}

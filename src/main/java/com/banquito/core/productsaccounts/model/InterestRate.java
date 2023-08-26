package com.banquito.core.productsaccounts.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INTEREST_RATE")
@Data
@NoArgsConstructor
public class InterestRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_INTEREST_RATE", nullable = false)
    private Integer id;
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;
    @Column(name = "INTEREST_RATE", precision = 4,scale = 4, nullable = false)
    private BigDecimal interestRate;
    @Column(name = "STATE", length = 3, nullable = false)
    private String state;
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", nullable = false)
    private Date start;
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", nullable = true)
    private Date end;

}

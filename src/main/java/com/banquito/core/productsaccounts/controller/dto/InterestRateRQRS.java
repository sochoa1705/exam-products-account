package com.banquito.core.productsaccounts.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterestRateRQRS {
    
    private Integer id;
    private String name;
    private BigDecimal interestRate;
    private String state;
    private Date start;
    private Date end;
}

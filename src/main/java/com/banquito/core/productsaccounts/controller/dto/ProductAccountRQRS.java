package com.banquito.core.productsaccounts.controller.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAccountRQRS {
    
    private String id;
    private String name;
    private String description;
    private BigDecimal minimunBalance;
    private String payInterest;
    private String acceptsChecks;
    private String state;
}

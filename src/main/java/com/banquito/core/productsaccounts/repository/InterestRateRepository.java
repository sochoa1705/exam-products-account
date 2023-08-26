package com.banquito.core.productsaccounts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.productsaccounts.model.InterestRate;

public interface InterestRateRepository extends JpaRepository<InterestRate, Integer>{
    
    List<InterestRate> findByState(String state);
}

package com.banquito.core.productsaccounts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.productsaccounts.model.ProductAccount;

public interface ProductAccountRepository extends JpaRepository<ProductAccount, String>{
    
    List<ProductAccount> findByState(String state);
}

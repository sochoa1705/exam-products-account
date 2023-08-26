package com.banquito.core.productsaccounts.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.productsaccounts.exception.CRUDException;
import com.banquito.core.productsaccounts.model.ProductAccount;
import com.banquito.core.productsaccounts.repository.ProductAccountRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductAccountService {
    
    private final ProductAccountRepository repository;

    public ProductAccountService(ProductAccountRepository repository) {
        this.repository = repository;
    }

    public List<ProductAccount> listAllActives() {
        log.info("Obtaining all active Products Accounts records");
        List<ProductAccount> rates = this.repository.findByState("ACT");
        log.debug("Returning {} Products Accounts records", rates.size());
        return rates;
    }

    public ProductAccount obtainById(String id) {
        log.info("Looking Product Account by id: {}", id);
        Optional<ProductAccount> productAccountOpt = this.repository.findById(id);
        if (!productAccountOpt.isPresent()) {
            throw new CRUDException(404, "Product Account with id: {" + id + "} does not exist");
        } else {
            return productAccountOpt.get();
        }
    }

    public void create(ProductAccount productAccount) throws CRUDException {
        try {
            log.info("Creating Product Account with the following info: {}", productAccount);
            productAccount.setCreationDate(new Date());
            this.repository.save(productAccount);
            log.debug("Product Account created with the following info: {}", productAccount);
        } catch (Exception e) {
            log.error("Error in Product Account creation: {}, with data: {}", e.getMessage(), productAccount);
            throw new CRUDException(510, "Product Account cannot be created, error:" + e.getMessage(), e);
        }
    }
}

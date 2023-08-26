package com.banquito.core.productsaccounts.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.productsaccounts.controller.dto.ProductAccountRQRS;
import com.banquito.core.productsaccounts.controller.mapper.ProductAccountMapper;
import com.banquito.core.productsaccounts.exception.CRUDException;
import com.banquito.core.productsaccounts.model.ProductAccount;
import com.banquito.core.productsaccounts.service.ProductAccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/productsaccounts")
public class ProductAccountController {
    
    private final ProductAccountService service;

    public ProductAccountController(ProductAccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductAccountRQRS>> obtainAll() {
        log.info("Going to return all active products accounts");
        List<ProductAccount> productAccounts = this.service.listAllActives();
        log.info("Returning {} active products accounts", productAccounts.size());
        return ResponseEntity.ok(ProductAccountMapper.mapToList(productAccounts)); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAccountRQRS> obtainByCode(@PathVariable(name = "id") String id) {
        log.info("Going to find product account by id: {}", id);
        ProductAccount productAccount = this.service.obtainById(id);
        if (productAccount!=null) {
            return ResponseEntity.ok(ProductAccountMapper.mapToProductAccountRQRS(productAccount));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductAccountRQRS productAccount) {
        try {
            log.info("Going to create a product account with info: {}", productAccount);
            this.service.create(ProductAccountMapper.mapToProductAccount(productAccount));
            return ResponseEntity.ok().build();
        } catch (CRUDException e) {
            log.error("Error at create product account: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }
}

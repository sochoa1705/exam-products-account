package com.banquito.core.productsaccounts.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.productsaccounts.controller.dto.InterestRateRQRS;
import com.banquito.core.productsaccounts.controller.mapper.InterestRateMapper;
import com.banquito.core.productsaccounts.exception.CRUDException;
import com.banquito.core.productsaccounts.model.InterestRate;
import com.banquito.core.productsaccounts.service.InterestRateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/interestrates")
public class InterestRateController {
    
    private final InterestRateService service;

    public InterestRateController(InterestRateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InterestRateRQRS>> obtainAll() {
        log.info("Going to return all active interest rates");
        List<InterestRate> interestRates = this.service.listAllActives();
        log.info("Returning {} active interest rates", interestRates.size());
        return ResponseEntity.ok(InterestRateMapper.mapToList(interestRates)); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestRateRQRS> obtainByCode(@PathVariable(name = "id") String id) {
        log.info("Going to find interest rate by id: {}", id);
        InterestRate interestRate = this.service.obtainById(Integer.parseInt(id));
        if (interestRate!=null) {
            return ResponseEntity.ok(InterestRateMapper.mapToInterestRateRQRS(interestRate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody InterestRateRQRS interestrate) {
        try {
            log.info("Going to create a interest rate with info: {}", interestrate);
            this.service.create(InterestRateMapper.mapToInterestRate(interestrate));
            return ResponseEntity.ok().build();
        } catch (CRUDException e) {
            log.error("Error at create interest rate: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<InterestRateRQRS> update(@PathVariable(name="id") String id, @RequestBody InterestRateRQRS interestrate) {
        try {
            this.service.update(Integer.parseInt(id), InterestRateMapper.mapToInterestRate(interestrate));
            return ResponseEntity.ok(InterestRateMapper.mapToInterestRateRQRS(this.service.obtainById(Integer.parseInt(id))));
        } catch (CRUDException e){
            log.error("Error at update interest rate: {}", e.getMessage(), e);
            return ResponseEntity.status(e.getErrorCode()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id") String id) {
        try {
            this.service.inactivate(Integer.parseInt(id));
            return ResponseEntity.ok().build();
        } catch (CRUDException e){
            log.error("Error at delete interest rate: {}", e.getMessage(), e);
            return ResponseEntity.status(e.getErrorCode()).build();
        }
    }
}

package com.banquito.core.productsaccounts.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.productsaccounts.exception.CRUDException;
import com.banquito.core.productsaccounts.model.InterestRate;
import com.banquito.core.productsaccounts.repository.InterestRateRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InterestRateService {

    private final InterestRateRepository repository;

    public InterestRateService(InterestRateRepository repository) {
        this.repository = repository;
    }

    public List<InterestRate> listAllActives() {
        log.info("Obtaining all active interest rate records");
        List<InterestRate> rates = this.repository.findByState("ACT");
        log.debug("Returning {} interest rates records", rates.size());
        return rates;
    }

    public InterestRate obtainById(Integer id) {
        log.info("Looking interest rate by id: {}", id);
        Optional<InterestRate> interestRateOpt = this.repository.findById(id);
        if (!interestRateOpt.isPresent()) {
            throw new CRUDException(404, "Interest Rate with id: {" + id + "} does not exist");
        } else {
            return interestRateOpt.get();
        }
    }

    public void create(InterestRate interestRate) throws CRUDException {
        try {
            log.info("Creating interest rate with the following info: {}", interestRate);
            this.repository.save(interestRate);
            log.debug("Interest rate created with the following info: {}", interestRate);
        } catch (Exception e) {
            log.error("Error in interest rate creation: {}, with data: {}", e.getMessage(), interestRate);
            throw new CRUDException(510, "Interest Rate cannot be created, error:" + e.getMessage(), e);
        }
    }

    public void update(Integer id, InterestRate interestRate) throws CRUDException {
        try {
            log.info("Going to update Interest Rate with id: {} ", id);
            log.debug("Going to update Interest Rate with id: {} with the following data {}", id, interestRate);
            Optional<InterestRate> interestRateOpt = this.repository.findById(id);
            if (!interestRateOpt.isPresent()) {
                throw new CRUDException(404, "Interest Rate with id: {" + id + "} does not exist");
            }
            InterestRate interestRateTmp = interestRateOpt.get();
            interestRateTmp.setName(interestRate.getName());
            interestRateTmp.setInterestRate(interestRate.getInterestRate());
            this.repository.save(interestRateTmp);
            log.debug("Interest Rate with id: {} has been updated with the following info {}", id, interestRateTmp);
        } catch (Exception e) {
            log.error("Error when try to update Interest Rate: {}, with the following info: {}", e.getMessage(),
                    interestRate);
            throw new CRUDException(520, "Interest Rate cannot be updated, error:" + e.getMessage(), e);
        }
    }

    public void inactivate(Integer id) {
        try {
            log.info("Going to inactivate Interest Rate with id: {} ", id);
            Optional<InterestRate> interestRateOpt = this.repository.findById(id);
            if (!interestRateOpt.isPresent()) {
                throw new CRUDException(404, "Interest Rate with id: {" + id + "} does not exist");
            }
            InterestRate interestRateTmp = interestRateOpt.get();
            interestRateTmp.setState("INA");
            interestRateTmp.setEnd(new Date());
            this.repository.save(interestRateTmp);
            log.debug("Interest Rate with id: {} has been inactivated with the following info {}", id, interestRateTmp);
        } catch (Exception e) {
            log.error("Error when try to inactivate Interest Rate: {}, with id: {}", e.getMessage(), id);
            throw new CRUDException(530, "Interest Rate cannot be inactivated, error:" + e.getMessage(), e);
        }
    }
}

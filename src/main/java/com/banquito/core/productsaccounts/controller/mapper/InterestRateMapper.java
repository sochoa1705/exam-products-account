package com.banquito.core.productsaccounts.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import com.banquito.core.productsaccounts.controller.dto.InterestRateRQRS;
import com.banquito.core.productsaccounts.model.InterestRate;

public class InterestRateMapper {
    
    public static InterestRateRQRS mapToInterestRateRQRS(InterestRate interestRate) {
        return InterestRateRQRS.builder()
            .id(interestRate.getId())
            .interestRate(interestRate.getInterestRate())
            .end(interestRate.getEnd())
            .start(interestRate.getStart())
            .state(interestRate.getState())
            .name(interestRate.getName()).build();
    }

    public static List<InterestRateRQRS> mapToList(List<InterestRate> interestRatees) {
        List<InterestRateRQRS> interestRateesRQRS = new ArrayList<>();
        if (interestRatees!=null) {
            for (InterestRate interestRate : interestRatees) {
                interestRateesRQRS.add(mapToInterestRateRQRS(interestRate));
            }
        }
        return interestRateesRQRS;
    }

    public static InterestRate mapToInterestRate(InterestRateRQRS interestRateRQRS) {
        InterestRate interestRate = new InterestRate();
        interestRate.setId(interestRateRQRS.getId());
        interestRate.setInterestRate(interestRateRQRS.getInterestRate());
        interestRate.setName(interestRateRQRS.getName());
        interestRate.setEnd(interestRateRQRS.getEnd());
        interestRate.setStart(interestRateRQRS.getStart());
        interestRate.setState(interestRateRQRS.getState());
        return interestRate;
    }
}

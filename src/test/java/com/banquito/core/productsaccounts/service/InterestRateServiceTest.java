package com.banquito.core.productsaccounts.service;

import com.banquito.core.productsaccounts.exception.CRUDException;
import com.banquito.core.productsaccounts.model.InterestRate;
import com.banquito.core.productsaccounts.repository.InterestRateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterestRateServiceTest {
    @Mock
    private InterestRateRepository interestRateRepository;
    @InjectMocks
    private InterestRateService interestRateService;

    @Test
    public void testObtainInterestRateById() throws CRUDException {

        Integer id = 1;
        InterestRate expectedInterestRate = new InterestRate();
        expectedInterestRate.setId(id);

        when(interestRateRepository.findById(id)).thenReturn(Optional.of(expectedInterestRate));

        InterestRate result = interestRateService.obtainById(id);

        assertEquals(expectedInterestRate, result);
        verify(interestRateRepository, times(1)).findById(id);
    }

    @Test(expected = CRUDException.class)
    public void testObtainInterestRateByIdNotFound() throws CRUDException {
        
        Integer id = 2;

        when(interestRateRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        interestRateService.obtainById(id);
    }
}

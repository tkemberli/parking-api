package com.tk.parkingapi;

import com.tk.parkingapi.service.billing.BillingService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * Tests the {@link BillingService} ability to correctly calculate prices
 */

@SpringBootTest
public class BillingServiceTests {

    @Autowired
    private  BillingService billingService;

    // TODO: soft-code?
    @Test
    public void oneHourShouldBe1(){
        val entryDate = LocalDateTime.now();
        val exitDate = entryDate.plusHours(1);
        double oneHourValue = 10.0d;
        double expectedBill =  oneHourValue * 1;
        
        val simpleBill = billingService.calculateBill(entryDate, exitDate);
        Assertions.assertEquals(simpleBill, expectedBill);
    }

    @Test
    public void fiveHoursShouldBe15(){
        val entryDate = LocalDateTime.now();
        val exitDate = entryDate.plusHours(5);
        double fiveHourValue = 15.0d;
        double expectedBill = fiveHourValue * 5;

                val simpleBill = billingService.calculateBill(entryDate, exitDate);
        Assertions.assertEquals(simpleBill, expectedBill);
    }

    @Test
    public void tenHoursShouldBe18(){
        val entryDate = LocalDateTime.now();
        val exitDate = entryDate.plusHours(10);
        double tenHourValue = 18.0d;
        double expectedBill = tenHourValue * 10;

                val simpleBill = billingService.calculateBill(entryDate, exitDate);
        Assertions.assertEquals(simpleBill, expectedBill);
    }

    @Test
    public void oneDayShouldBe20(){
        val entryDate = LocalDateTime.now();
        val exitDate = entryDate.plusHours(24);
        double oneDayValue = 20.0d;
        double expectedBill = oneDayValue * 24;

                val simpleBill = billingService.calculateBill(entryDate, exitDate);
        Assertions.assertEquals(simpleBill, expectedBill);
    }
}

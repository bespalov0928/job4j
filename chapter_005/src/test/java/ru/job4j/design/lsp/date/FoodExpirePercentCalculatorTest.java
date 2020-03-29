package ru.job4j.design.lsp.date;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class FoodExpirePercentCalculatorTest {

    @Test
    public void whenTodayIsCreatedDateThanCalculateShouldReturn0() {
        FoodExpirePercentCalculator calculator = new FoodExpirePercentCalculator(LocalDate.now(), LocalDate.now().plusDays(10));
        assertEquals(0, calculator.calculate());
    }

    @Test
    public void whenTodayIsHalfOfExpirationThanCalculateShouldReturn50() {
        FoodExpirePercentCalculator calculator = new FoodExpirePercentCalculator(
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(5));
        assertEquals(50, calculator.calculate());
    }

    @Test
    public void whenTodayIsAfterExpireDateThanCalculateShouldReturn100() {
        FoodExpirePercentCalculator calculator = new FoodExpirePercentCalculator(
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(1));
        assertEquals(100, calculator.calculate());
    }
}
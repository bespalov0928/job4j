package ru.job4j.design.lsp.date;

import java.time.LocalDate;
import java.time.Period;

public class FoodExpirePercentCalculator {
    private final LocalDate createDate;
    private final LocalDate expireDate;

    public FoodExpirePercentCalculator(LocalDate createDate, LocalDate expireDate) {
        this.createDate = createDate;
        this.expireDate = expireDate;
    }

    public int calculate() {
        var totalDaysToExpire = Period.between(createDate, expireDate).getDays();
        var currentDaysToExpire = Period.between(LocalDate.now(), expireDate).getDays();
        var result = (100 * currentDaysToExpire) / totalDaysToExpire;
        return result <= 0 ? 100 : 100 - result;
    }
}

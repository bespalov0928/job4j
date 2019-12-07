package ru.job4j.calculator;

import ru.job4j.calculator.Fit;
import org.junit.Assert;
import org.junit.Test;

public class FitTest {
    @Test
    public void testManWeight() {
        double in = 178;
        double expected = 89.69;
        double result = Fit.manWeight(in);

        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void testWomanWeight() {
        double in = 165;
        double expected = 63.24;
        double result = Fit.womanWeight(in);

        Assert.assertEquals(expected, result, 0.01);
    }
}

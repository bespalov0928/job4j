package ru.job4j.condition;

import job4j.condition.SqArea;
import org.junit.Assert;
import org.junit.Test;

public class SqAreaTest {
    @Test
    public void testAreaOne() {
        int p = 4;
        int k = 1;

        double expected = 1;
        double result = SqArea.square(p, k);

        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void testAreaTwo() {
        int p = 6;
        int k = 2;

        double expected = 2;
        double result = SqArea.square(p, k);

        Assert.assertEquals(expected, result, 0.01);
    }
}

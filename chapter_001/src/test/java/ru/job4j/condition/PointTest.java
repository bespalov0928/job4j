package ru.job4j.condition;

import ru.job4j.condition.Point;
import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void testDistance() {
        int x1 = 0;
        int y1 = 0;
        int x2 = 2;
        int y2 = 0;
        double expected = 2;
        double result = Point.distance(x1, y1, x2, y2);

        Assert.assertEquals(expected, result, 0.01);
    }
}

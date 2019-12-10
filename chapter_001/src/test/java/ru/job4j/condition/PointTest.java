package ru.job4j.condition;

import org.junit.Ignore;
import ru.job4j.condition.Point;
import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void testDistance() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double result = a.distance(b);
        double expected = 2;

        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void testDistance3D() {
        Point a = new Point(-2, 2, 4);
        Point b = new Point(2, -1, 1);
        double result = a.distance3d(b);
        double expected = 5.83;

        Assert.assertEquals(expected, result, 0.01);
    }
}

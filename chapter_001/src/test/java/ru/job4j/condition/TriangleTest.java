package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenExist() {
        Point a = new Point(2, 2);
        Point b = new Point(4, 5);
        Point c = new Point(7, 1);
        Triangle triangle = new Triangle(a, b, c);
        boolean result = triangle.exist(a.distance(b), a.distance(c), b.distance(c));
        assertThat(result, is(true));
    }

    @Test
    public void whenNotExist() {
        Point a = new Point(2, 2);
        Point b = new Point(2, 2);
        Point c = new Point(7, 1);
        Triangle triangle = new Triangle(a, b, c);
        boolean result = triangle.exist(a.distance(b), a.distance(c), b.distance(c));
        assertThat(result, is(false));
    }

    @Test
    public void testPeriod() {
        Point a = new Point(2, 2);
        Point b = new Point(4, 5);
        Point c = new Point(7, 1);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.period(a.distance(b), a.distance(c), b.distance(c));
        double expected = 6.8;
        assertEquals(expected, result, 0.1);
    }

    @Test
    public void testArea() {
        Point a = new Point(2, 2);
        Point b = new Point(4, 5);
        Point c = new Point(7, 1);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 8.5;
        assertEquals(expected, result, 0.1);
    }
}

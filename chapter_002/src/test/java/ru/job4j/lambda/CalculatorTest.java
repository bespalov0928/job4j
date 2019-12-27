package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void whenAdd1Until3() {
        Calculator calc = new Calculator();
        List<Double> buffer = new ArrayList<>();
        calc.multiple(
                0, 3, 1,
                (value, index) -> (double) value + index,
                buffer::add
        );
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }

    @Test
    public void whenLinearFunctionThenLinearResults() {
        Calculator calc = new Calculator();
        List<Double> result = calc.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticThenQuadraticResults() {
        Calculator calc = new Calculator();
        List<Double> result = calc.diapason(1, 4, x -> Math.pow(x, 2));
        List<Double> expected = Arrays.asList(1D, 4D, 9D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicThenLogarithmicResults() {
        Calculator calc = new Calculator();
        List<Double> result = calc.diapason(4, 7, x -> (double) Math.round(Math.log(x) / Math.log(2)));
        List<Double> expected = Arrays.asList(2D, 2D, 3D);
        assertThat(result, is(expected));
    }
}

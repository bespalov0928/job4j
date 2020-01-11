package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixToListTest {

    @Test
    public void matrixToListTest() {
        List<Integer> result = MatrixToList.convert(new Integer[][]{{1, 2}, {3, 4}});
        assertThat(result, is(List.of(1, 2, 3, 4)));
    }
}

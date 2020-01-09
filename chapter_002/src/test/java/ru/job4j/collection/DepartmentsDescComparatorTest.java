package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class DepartmentsDescComparatorTest {
    @Test
    public void compare() {
        int rsl = new DepartmentsDescComparator().compare(
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"
        );
        assertThat(rsl, greaterThan(0));
    }
}

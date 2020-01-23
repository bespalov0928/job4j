package ru.job4j.stream;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GroupTest {

    @Test
    public void testEmpty() {
        Map<String, Set<String>> result = Group.sections(Collections.emptyList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testNotEmptyGroupingPresent() {
        Student ivanov = new Student("Ivanov", Set.of("physics", "mathematics"));
        Student petrov = new Student("Petrov", Set.of("astronomy", "mathematics"));
        Map<String, Set<String>> result = Group.sections(List.of(ivanov, petrov));
        Map<String, Set<String>> expected =
                Map.of("physics", Set.of("Ivanov"),
                        "mathematics", Set.of("Ivanov", "Petrov"),
                        "astronomy", Set.of("Petrov"));
        assertThat(result, is(expected));
    }

    @Test
    public void testNotEmptyGroupingNotPresent() {
        Student ivanov = new Student("Ivanov", Set.of("physics"));
        Student petrov = new Student("Petrov", Set.of("astronomy"));
        Map<String, Set<String>> result = Group.sections(List.of(ivanov, petrov));
        Map<String, Set<String>> expected =
                Map.of("physics", Set.of("Ivanov"),
                        "astronomy", Set.of("Petrov"));
        assertThat(result, is(expected));
    }
}

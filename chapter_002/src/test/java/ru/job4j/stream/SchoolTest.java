package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    List<School.Student> students = new ArrayList<>();

    @Before
    public void init() {
        students.add(new School.Student(10, "Petrov"));
        students.add(new School.Student(50, "Ivanov"));
        students.add(new School.Student(70, "Sidorov"));
        students.add(new School.Student(90, "Pratsun"));
    }

    @Test
    public void scoreIsLessThan50Test() {
        List<School.Student> result = School.collect(students, (student) -> student.score <= 50);
        assertThat(result, is(Arrays.asList(
                new School.Student(10, "Petrov"),
                new School.Student(50, "Ivanov")
        )));
    }

    @Test
    public void scoreIsBetween50And70Test() {
        List<School.Student> result = School.collect(students, (student) -> student.score > 50 && student.score <= 70);
        assertThat(result, is(Collections.singletonList(new School.Student(70, "Sidorov"))));
    }

    @Test
    public void scoreIsBetween70And100Test() {
        List<School.Student> result = School.collect(students, (student) -> student.score > 70 && student.score <= 100);
        assertThat(result, is(Collections.singletonList(new School.Student(90, "Pratsun"))));
    }

    @Test
    public void listToMapTest() {
        Map<String, School.Student> result = School.listToMap(students);
        Map<String, School.Student> expected = new HashMap<>();
        for (School.Student student : students) {
            expected.put(student.getSurname(), student);
        }
        assertThat(result, is(expected));
    }

    @Test
    public void levelOfTest() {
        List<School.Student> result = School.levelOf(students, 50);
        assertThat(result, is(Arrays.asList(
                new School.Student(90, "Pratsun"),
                new School.Student(70, "Sidorov")
        )));
    }
}

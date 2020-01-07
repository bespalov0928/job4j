package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    List<School.Student> students = new ArrayList<>();

    @Before
    public void init() {
        students.add(new School.Student(10));
        students.add(new School.Student(50));
        students.add(new School.Student(70));
        students.add(new School.Student(90));
    }

    @Test
    public void scoreIsLessThan50Test() {
        List<School.Student> result = School.collect(students, (student) -> student.score <= 50);
        assertThat(result, is(Arrays.asList(new School.Student(10), new School.Student(50))));
    }

    @Test
    public void scoreIsBetween50And70Test() {
        List<School.Student> result = School.collect(students, (student) -> student.score > 50 && student.score <= 70);
        assertThat(result, is(Collections.singletonList(new School.Student(70))));
    }

    @Test
    public void scoreIsBetween70And100Test() {
        List<School.Student> result = School.collect(students, (student) -> student.score > 70 && student.score <= 100);
        assertThat(result, is(Collections.singletonList(new School.Student(90))));
    }
}

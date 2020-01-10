package ru.job4j.stream;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class School {
    public static class Student implements Comparable<Student> {
        public int score;
        private String surname;

        public Student(int score, String surname) {
            this.score = score;
            this.surname = surname;
        }

        public String getSurname() {
            return surname;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return score == student.score && surname.equals(student.surname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(score, surname);
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(o.score, this.score);
        }
    }

    public static List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static Map<String, Student> listToMap(List<Student> students) {
        return students.stream()
                .distinct()
                .collect(Collectors.toMap(Student::getSurname, Function.identity()));
    }

    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted()
                .flatMap(Stream::ofNullable)
                .takeWhile(student -> student.score > bound)
                .collect(Collectors.toList());
    }
}

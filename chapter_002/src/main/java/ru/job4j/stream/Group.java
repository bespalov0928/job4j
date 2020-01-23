package ru.job4j.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Group {
    public static Map<String, Set<String>> sections(List<Student> students) {
        return students.stream()
                .map(Student::studentToHolderList)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Holder::getValue,
                        Collectors.mapping(Holder::getKey, Collectors.toSet())
                        )
                );
    }
}

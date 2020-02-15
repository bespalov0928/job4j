package ru.job4j.disjointgroups;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DisjointGroups {

    private static List<Set<String>> merge(Set<String> lines) {
        List<Set<String>> groupsLines = new ArrayList<>();
        var numbersGroups = new HashMap<String, Integer>();
        var mergedGroups = new HashMap<Integer, Integer>();

        for (var line : lines) {
            Set<String> numbers = parseLine(line);
            var newNumbers = new ArrayList<String>();
            var groupsWithSameNumbers = new TreeSet<Integer>();
            for (var number : numbers) {
                var numberGroup = numbersGroups.get(number);
                if (numberGroup == null) {
                    newNumbers.add(number);
                } else {
                    while (mergedGroups.containsKey(numberGroup)) {
                        numberGroup = mergedGroups.get(numberGroup);
                    }
                    groupsWithSameNumbers.add(numberGroup);
                }
            }
            int groupNumber;
            if (groupsWithSameNumbers.isEmpty()) {
                groupsLines.add(new HashSet<>());
                groupNumber = groupsLines.size() - 1;
            } else {
                groupNumber = groupsWithSameNumbers.first();
            }

            newNumbers.forEach(n -> numbersGroups.put(n, groupNumber));

            for (var group : groupsWithSameNumbers) {
                if (group != groupNumber) {
                    mergedGroups.put(group, groupNumber);
                    groupsLines.get(groupNumber).addAll(groupsLines.get(group));
                    groupsLines.set(group, null);
                }
            }
            groupsLines.get(groupNumber).add(line);
        }
        groupsLines.removeAll(Collections.singleton(null));
        groupsLines = groupsLines.stream().filter(g -> g.size() > 1).collect(Collectors.toList());
        groupsLines.sort((o1, o2) -> o2.size() - o1.size());
        return groupsLines;
    }

    private static Set<String> parseLine(String line) {
        return Arrays.stream(line.split(";"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }

    private static Set<String> parseFile() {
        Set<String> set = new HashSet<>();
        URL resource = DisjointGroups.class.getResource("/lng.csv");
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            set = lines.collect(Collectors.toSet());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return set;
    }

    private static void writeToFile(List<Set<String>> data) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("out.txt"));
            pw.println("Groups Count: " + data.size());
            var count = 1;
            for (var line : data) {
                pw.println("Group " + count++);
                pw.println(line);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var result = merge(parseFile());
        writeToFile(result);
    }
}

package ru.job4j.analize;

import java.io.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ServerAvailabilityAnalyzer {
    private static final Set<String> UNAVAILABLE_STATUSES = Set.of("400", "500");

    private static class Record {
        private String status;
        private String time;

        public Record(String status, String time) {
            this.status = status;
            this.time = time;
        }
    }

    public void unavailable(String source, String target) {
        try {
            List<Record> recordList = parseSourceFile(source);
            writeTargetFile(recordList, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Record> parseSourceFile(String source) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
        List<Record> recordList = bufferedReader.lines().map(line -> {
            String[] record = line.split(" ");
            return new Record(record[0], record[1]);
        }).collect(Collectors.toList());
        bufferedReader.close();
        return recordList;
    }

    private void writeTargetFile(List<Record> records, String target) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(target));
        String startTime = "";
        for (var record : records) {
            if (startTime.isEmpty() && UNAVAILABLE_STATUSES.contains(record.status)) {
                startTime = record.time;
            } else if (!startTime.isEmpty() && !UNAVAILABLE_STATUSES.contains(record.status)) {
                pw.println(startTime + ";" + record.time);
                startTime = "";
            }
        }
        pw.close();
    }
}

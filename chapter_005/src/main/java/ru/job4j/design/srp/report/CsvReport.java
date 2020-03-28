package ru.job4j.design.srp.report;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CsvReport extends AbstractReport {

    public CsvReport(List<ReportField<?>> fields) {
        super(fields);
    }

    @Override
    public Collector<CharSequence, ?, String> collector() {
        return Collectors.joining(";", "", System.lineSeparator());
    }
}

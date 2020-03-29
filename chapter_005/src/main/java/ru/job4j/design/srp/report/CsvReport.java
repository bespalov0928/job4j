package ru.job4j.design.srp.report;

import java.util.List;
import java.util.stream.Collectors;

public class CsvReport extends AbstractReport {

    public CsvReport(List<ReportField<?>> fields) {
        super(fields);
        setCollector(FIELDS_COLLECTOR, Collectors.joining(";", "", System.lineSeparator()));
        setCollector(HEADER_COLLECTOR, Collectors.joining(";", "", System.lineSeparator()));
    }
}

package ru.job4j.design.srp.report.header;

import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.collector.ReportFieldsCollector;

import java.util.List;
import java.util.stream.Collectors;

public class CsvReportHeader implements ReportHeader {
    private final List<ReportField<?>> fields;

    public CsvReportHeader(List<ReportField<?>> fields) {
        this.fields = fields;
    }

    @Override
    public String generate() {
        return new ReportFieldsCollector(fields)
                .collect(ReportField::getName, Collectors.joining(";", "", System.lineSeparator()));
    }
}

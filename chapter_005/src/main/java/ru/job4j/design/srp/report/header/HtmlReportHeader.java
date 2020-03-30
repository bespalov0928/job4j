package ru.job4j.design.srp.report.header;

import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.collector.ReportFieldsCollector;

import java.util.List;
import java.util.stream.Collectors;

public class HtmlReportHeader implements ReportHeader {
    private final static String HEADER = "<html><body><table>";
    private final List<ReportField<?>> fields;

    public HtmlReportHeader(List<ReportField<?>> fields) {
        this.fields = fields;
    }

    @Override
    public String generate() {
        return HEADER + new ReportFieldsCollector(fields).collect(
                f -> String.join("", "<th>", f.getName(), "</th>"),
                Collectors.joining("", "<tr>", "</tr>")
        );
    }
}

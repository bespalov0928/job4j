package ru.job4j.design.srp.report.header;

import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.collector.ReportFieldsCollector;

import java.util.List;
import java.util.stream.Collectors;

public class HtmlReportHeader implements ReportHeader {
    private final static String HEADER = "<html><body><table>";
    private final static String TH = "<th>%s</th>";
    private final static String TR_OPEN = "<tr>";
    private final static String TR_CLOSE = "</tr>";

    private final List<ReportField<?>> fields;

    public HtmlReportHeader(List<ReportField<?>> fields) {
        this.fields = fields;
    }

    @Override
    public String generate() {
        return HEADER + new ReportFieldsCollector(fields).collect(
                f -> String.format(TH, f.getName()),
                Collectors.joining("", TR_OPEN, TR_CLOSE)
        );
    }
}

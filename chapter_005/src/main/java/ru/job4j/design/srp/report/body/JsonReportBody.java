package ru.job4j.design.srp.report.body;

import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.collector.ReportFieldsCollector;

import java.util.List;
import java.util.stream.Collectors;

public class JsonReportBody implements ReportBody {
    private final static String PROPERTY = "\"%s\":\"%s\"";
    private final static String BRACE_OPEN = "{";
    private final static String BRACE_CLOSE = "}";

    private final ReportFieldsCollector collector;

    public JsonReportBody(List<ReportField<?>> fields) {
        this.collector = new ReportFieldsCollector(fields);
    }

    @Override
    public String generate(List<Employee> employees) {
        return employees.stream()
                .map(e -> collector.collect(
                        f -> String.format(PROPERTY, f.getName().toLowerCase(), f.getValue(e)),
                        Collectors.joining(",", BRACE_OPEN, BRACE_CLOSE)
                        )
                ).collect(Collectors.joining(","));
    }
}

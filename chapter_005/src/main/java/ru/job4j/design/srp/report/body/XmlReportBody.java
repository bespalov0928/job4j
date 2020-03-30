package ru.job4j.design.srp.report.body;

import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.collector.ReportFieldsCollector;

import java.util.List;
import java.util.stream.Collectors;

public class XmlReportBody implements ReportBody {
    private final List<ReportField<?>> fields;

    public XmlReportBody(List<ReportField<?>> fields) {
        this.fields = fields;
    }

    @Override
    public String generate(List<Employee> employees) {
        var collector = new ReportFieldsCollector(fields);
        return employees.stream()
                .map(e -> collector.collect(
                        f -> String
                                .join(
                                        "",
                                        String.format("<%s>", f.getName().toLowerCase()),
                                        f.getValue(e),
                                        String.format("</%s>", f.getName().toLowerCase())
                                ),
                        Collectors.joining("", "<employee>", "</employee>")
                        )
                ).collect(Collectors.joining());
    }
}

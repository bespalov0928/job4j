package ru.job4j.design.srp.report.body;

import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.collector.ReportFieldsCollector;

import java.util.List;
import java.util.stream.Collectors;

public class HtmlReportBody implements ReportBody {
    private final List<ReportField<?>> fields;

    public HtmlReportBody(List<ReportField<?>> fields) {
        this.fields = fields;
    }

    @Override
    public String generate(List<Employee> employees) {
        var collector = new ReportFieldsCollector(fields);
        return employees.stream()
                .map(employee -> collector.collect(
                        f -> String.join("", "<td>", f.getValue(employee), "</td>"),
                        Collectors.joining("", "<tr>", "</tr>")
                        )
                ).collect(Collectors.joining());
    }
}

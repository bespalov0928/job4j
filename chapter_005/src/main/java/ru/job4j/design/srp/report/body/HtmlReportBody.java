package ru.job4j.design.srp.report.body;

import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.collector.ReportFieldsCollector;

import java.util.List;
import java.util.stream.Collectors;

public class HtmlReportBody implements ReportBody {
    private final static String TD = "<td>%s</td>";
    private final static String TR_OPEN = "<tr>";
    private final static String TR_CLOSE = "</tr>";

    private final ReportFieldsCollector collector;

    public HtmlReportBody(List<ReportField<?>> fields) {
        this.collector = new ReportFieldsCollector(fields);
    }

    @Override
    public String generate(List<Employee> employees) {
        return employees.stream()
                .map(employee -> collector
                        .collect(
                                f -> String.format(TD, f.getValue(employee)),
                                Collectors.joining("", TR_OPEN, TR_CLOSE)
                        )
                ).collect(Collectors.joining());
    }
}

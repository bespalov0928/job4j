package ru.job4j.design.srp.report.body;

import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.collector.ReportFieldsCollector;

import java.util.List;
import java.util.stream.Collectors;

public class XmlReportBody implements ReportBody {
    private final static String PROPERTY = "<%s>%s</%s>";
    private final static String TAG_OPEN = "<employee>";
    private final static String TAG_CLOSE = "</employee>";

    private final ReportFieldsCollector collector;

    public XmlReportBody(List<ReportField<?>> fields) {
        this.collector = new ReportFieldsCollector(fields);
    }

    @Override
    public String generate(List<Employee> employees) {
        return employees.stream()
                .map(e -> collector.
                        collect(
                                f -> String.format(
                                        PROPERTY,
                                        f.getName().toLowerCase(),
                                        f.getValue(e),
                                        f.getName().toLowerCase()
                                ),
                                Collectors.joining("", TAG_OPEN, TAG_CLOSE)
                        )
                ).collect(Collectors.joining());
    }
}

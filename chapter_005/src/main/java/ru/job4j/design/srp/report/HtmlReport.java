package ru.job4j.design.srp.report;

import ru.job4j.design.srp.collector.CollectorMap;
import ru.job4j.design.srp.entity.Employee;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HtmlReport extends AbstractReport {

    public HtmlReport(List<ReportField<?>> fields) {
        super(fields);
        setCollector(CollectorMap.FIELDS_COLLECTOR, collector());
        setCollector(CollectorMap.HEADER_COLLECTOR, collector());
    }

    @Override
    protected String header() {
        return "<html><body><table>" + super.header();
    }

    @Override
    protected String footer() {
        return "</table></body></html>";
    }

    @Override
    protected String mapper(String data) {
        return String.join("", "<td>", data, "</td>");
    }

    @Override
    protected String mapper(ReportField<?> f, Employee e) {
        return mapper(f.getValue(e));
    }

    private Collector<CharSequence, ?, String> collector() {
        return Collectors.joining("", "<tr>", "</tr>");
    }
}

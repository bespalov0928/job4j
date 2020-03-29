package ru.job4j.design.srp.report;

import ru.job4j.design.srp.collector.CollectorMap;
import ru.job4j.design.srp.entity.Employee;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class AbstractReport implements Report {

    private final StringBuilder report = new StringBuilder();
    private final List<ReportField<?>> fields;
    private final CollectorMap collectors = new CollectorMap();

    protected AbstractReport(List<ReportField<?>> fields) {
        this.fields = fields;
        collectors.put(CollectorMap.HEADER_COLLECTOR, Collectors.joining());
        collectors.put(CollectorMap.BODY_COLLECTOR, Collectors.joining());
        collectors.put(CollectorMap.FIELDS_COLLECTOR, Collectors.joining());
    }

    @Override
    public String generate(List<Employee> employees) {
        report.append(header());
        report.append(body(employees));
        report.append(footer());
        return report.toString();
    }

    public void setCollector(String key, Collector<CharSequence, ?, String> collector) {
        collectors.put(key, collector);
    }

    protected String header() {
        return fields.stream().map(f -> mapper(f.getName())).collect(collectors.get(CollectorMap.HEADER_COLLECTOR));
    }

    protected String body(List<Employee> employees) {
        return employees.stream()
                .map(this::mapFields)
                .collect(collectors.get(CollectorMap.BODY_COLLECTOR));
    }

    protected String footer() {
        return "";
    }

    protected String mapFields(Employee e) {
        return fields.stream()
                .map(f -> mapper(f, e))
                .collect(collectors.get(CollectorMap.FIELDS_COLLECTOR));
    }

    protected String mapper(String data) {
        return data;
    }

    protected String mapper(ReportField<?> f, Employee e) {
        return f.getValue(e);
    }
}

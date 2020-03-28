package ru.job4j.design.srp.report;

import ru.job4j.design.srp.entity.Employee;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class AbstractReport implements Report {
    protected final StringBuilder report = new StringBuilder();

    private final List<ReportField<?>> fields;

    protected AbstractReport(List<ReportField<?>> fields) {
        this.fields = fields;
    }

    @Override
    public String generate(List<Employee> employees) {
        setReportHeader();
        setReportBody(employees);
        setReportFooter();
        return report.toString();
    }

    private void setReportBody(List<Employee> employees) {
        report.append(fields.stream().map(f -> mapper(f.getName())).collect(collector()));
        report.append(employees.stream().map(e -> fields.stream()
                .map(f -> mapper(f.getValue(e)))
                .collect(collector()))
                .collect(Collectors.joining()));
    }

    protected void setReportHeader() {

    }

    protected void setReportFooter() {

    }

    protected String mapper(String data) {
        return data;
    }

    protected abstract Collector<CharSequence, ?, String> collector();
}

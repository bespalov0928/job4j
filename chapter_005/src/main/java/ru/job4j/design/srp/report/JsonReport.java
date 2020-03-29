package ru.job4j.design.srp.report;

import ru.job4j.design.srp.collector.CollectorMap;
import ru.job4j.design.srp.entity.Employee;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class JsonReport extends AbstractReport {

    public JsonReport(List<ReportField<?>> fields) {
        super(fields);
        setCollector(CollectorMap.FIELDS_COLLECTOR, Collectors.joining(",", "{", "}"));
        setCollector(CollectorMap.BODY_COLLECTOR, Collectors.joining(","));
    }

    @Override
    protected String header() {
        return "{\"report\":[";
    }

    @Override
    protected String footer() {
        return "]}";
    }

    @Override
    protected String mapper(ReportField<?> f, Employee e) {
        return String.format("\"%s\":\"%s\"", f.getName().toLowerCase(), f.getValue(e));
    }
}

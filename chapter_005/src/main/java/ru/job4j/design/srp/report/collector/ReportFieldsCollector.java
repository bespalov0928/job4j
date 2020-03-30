package ru.job4j.design.srp.report.collector;

import ru.job4j.design.srp.report.ReportField;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;

public class ReportFieldsCollector {
    private final List<ReportField<?>> fields;

    public ReportFieldsCollector(List<ReportField<?>> fields) {
        this.fields = fields;
    }

    public String collect(Function<ReportField<?>, String> mapper, Collector<CharSequence, ?, String> collector) {
        return fields.stream().map(mapper).collect(collector);
    }
}

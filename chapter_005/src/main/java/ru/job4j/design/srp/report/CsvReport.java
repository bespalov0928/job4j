package ru.job4j.design.srp.report;

import ru.job4j.design.srp.collector.CollectorMap;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CsvReport extends AbstractReport {

    public CsvReport(List<ReportField<?>> fields) {
        super(fields);
        setCollector(CollectorMap.FIELDS_COLLECTOR, collector());
        setCollector(CollectorMap.HEADER_COLLECTOR, collector());
    }

    private Collector<CharSequence, ?, String> collector() {
        return Collectors.joining(";", "", System.lineSeparator());
    }
}

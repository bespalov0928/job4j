package ru.job4j.design.srp.report;

import ru.job4j.design.srp.entity.Employee;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class XmlReport extends AbstractReport {

    public XmlReport(List<ReportField<?>> fields) {
        super(fields);
        setCollector(FIELDS_COLLECTOR, Collectors.joining("", "<employee>", "</employee>"));
    }

    @Override
    protected String header() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><employees>";
    }

    @Override
    protected String footer() {
        return "</employees></report>";
    }

    @Override
    protected String mapper(ReportField<?> f, Employee e) {
        var fieldName = f.getName().toLowerCase();
        return String.join("", String.format("<%s>", fieldName), f.getValue(e), String.format("</%s>", fieldName));
    }
}

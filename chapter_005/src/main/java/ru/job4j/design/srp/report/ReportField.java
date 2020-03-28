package ru.job4j.design.srp.report;

import ru.job4j.design.srp.entity.Employee;

import java.util.function.Function;

public class ReportField<T> {
    private final String fieldName;
    private final Function<Employee, T> fieldValue;
    private Function<T, String> formatValue = String::valueOf;

    public ReportField(String fieldName, Function<Employee, T> fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ReportField(String fieldName, Function<Employee, T> fieldValue, Function<T, String> formatValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.formatValue = formatValue;
    }

    public String getName() {
        return fieldName;
    }

    public String getValue(Employee employee) {
        return formatValue.apply(fieldValue.apply(employee));
    }
}

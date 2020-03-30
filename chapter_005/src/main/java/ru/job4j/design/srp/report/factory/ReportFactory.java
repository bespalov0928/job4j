package ru.job4j.design.srp.report.factory;

import ru.job4j.design.srp.report.Report;
import ru.job4j.design.srp.report.ReportType;

public interface ReportFactory {
    Report build(ReportType type);
}

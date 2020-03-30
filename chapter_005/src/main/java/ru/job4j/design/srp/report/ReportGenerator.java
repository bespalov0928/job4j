package ru.job4j.design.srp.report;

import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.report.body.ReportBody;
import ru.job4j.design.srp.report.footer.ReportFooter;
import ru.job4j.design.srp.report.header.ReportHeader;

import java.util.List;

public class ReportGenerator implements Report {
    private final ReportHeader header;
    private final ReportBody body;
    private final ReportFooter footer;

    private final StringBuilder report = new StringBuilder();

    public ReportGenerator(ReportHeader header, ReportBody body, ReportFooter footer) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    @Override
    public String generate(List<Employee> employees) {
        report.append(header.generate());
        report.append(body.generate(employees));
        report.append(footer.generate());
        return report.toString();
    }
}

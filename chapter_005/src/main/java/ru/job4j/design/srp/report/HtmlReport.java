package ru.job4j.design.srp.report;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HtmlReport extends AbstractReport {

    public HtmlReport(List<ReportField<?>> fields) {
        super(fields);
    }

    @Override
    protected void setReportHeader() {
        report.append("<html><body><table>");
    }

    @Override
    protected void setReportFooter() {
        report.append("</table></body></html>");
    }

    @Override
    protected Collector<CharSequence, ?, String> collector() {
        return Collectors.joining("", "<tr>", "</tr>");
    }

    @Override
    protected String mapper(String data) {
        return String.join("", "<td>", data, "</td>");
    }
}

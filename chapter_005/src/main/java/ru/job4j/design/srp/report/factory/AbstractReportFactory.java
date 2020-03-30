package ru.job4j.design.srp.report.factory;

import ru.job4j.design.srp.report.Report;
import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.report.ReportTemplate;
import ru.job4j.design.srp.report.ReportType;
import ru.job4j.design.srp.report.body.CsvReportBody;
import ru.job4j.design.srp.report.body.HtmlReportBody;
import ru.job4j.design.srp.report.body.JsonReportBody;
import ru.job4j.design.srp.report.body.XmlReportBody;
import ru.job4j.design.srp.report.header.CsvReportHeader;
import ru.job4j.design.srp.report.header.HtmlReportHeader;

import java.util.List;

public class AbstractReportFactory implements ReportFactory {
    private final List<ReportField<?>> fields;

    public AbstractReportFactory(List<ReportField<?>> fields) {
        this.fields = fields;
    }

    @Override
    public Report build(ReportType type) {
        Report report;
        switch (type) {
            case JSON:
                report = new ReportTemplate(
                        () -> "{\"report\":[",
                        new JsonReportBody(fields),
                        () -> "]}");
                break;
            case XML:
                report = new ReportTemplate(
                        () -> "<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><employees>",
                        new XmlReportBody(fields),
                        () -> "</employees></report>");
                break;
            case HTML:
                report = new ReportTemplate(
                        new HtmlReportHeader(fields),
                        new HtmlReportBody(fields),
                        () -> "</table></body></html>");
                break;
            default:
                report = new ReportTemplate(
                        new CsvReportHeader(fields),
                        new CsvReportBody(fields),
                        () -> "");
        }
        return report;
    }
}

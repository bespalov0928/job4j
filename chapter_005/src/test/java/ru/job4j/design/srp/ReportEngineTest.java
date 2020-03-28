package ru.job4j.design.srp;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.design.srp.comparator.SalaryComparator;
import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.report.CsvReport;
import ru.job4j.design.srp.report.HtmlReport;
import ru.job4j.design.srp.report.ReportEngine;
import ru.job4j.design.srp.report.ReportField;
import ru.job4j.design.srp.service.SortedStoreService;
import ru.job4j.design.srp.service.StoreService;
import ru.job4j.design.srp.store.ListStore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {
    private StoreService storeService = new SortedStoreService(new ListStore());
    private DecimalFormat format = new DecimalFormat("####,###,###.00");
    List<ReportField<?>> fields = new ArrayList<>();
    private ReportEngine reportEngine = new ReportEngine(storeService, new CsvReport(fields));

    @Before
    public void setUp() {
        fields.add(new ReportField<>("Name", Employee::getName));
        fields.add(new ReportField<>("Salary", Employee::getSalary, format::format));
    }

    @After
    public void tearDown() {
        fields.clear();
    }

    @Test
    public void generateCsvReportSortedByNameAscWithFilteredSalary() {

        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 1000);
        Employee second = new Employee("Dmitry", now, now, 2000);
        storeService.add(first);
        storeService.add(second);

        reportEngine.setReport(new CsvReport(fields));

        StringBuilder expect = new StringBuilder()
                .append("Name;Salary")
                .append(System.lineSeparator())
                .append(second.getName()).append(";")
                .append(format.format(second.getSalary()))
                .append(System.lineSeparator())
                .append(first.getName()).append(";")
                .append(format.format(first.getSalary()))
                .append(System.lineSeparator());
        assertThat(reportEngine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void generateHtmlReportSortedByNameAscWithFilteredSalary() {
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 1000);
        Employee second = new Employee("Dmitry", now, now, 2000);

        reportEngine.setReport(new HtmlReport(fields));

        storeService.add(first);
        storeService.add(second);

        StringBuilder expect = new StringBuilder()
                .append("<html><body><table>")
                .append("<tr><td>Name</td><td>Salary</td></tr>")
                .append("<tr><td>").append(second.getName()).append("</td><td>")
                .append(format.format(second.getSalary())).append("</td></tr>")
                .append("<tr><td>").append(first.getName()).append("</td><td>")
                .append(format.format(first.getSalary())).append("</td></tr>")
                .append("</table></body></html>");
        assertThat(reportEngine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void generateHtmlReportSortedBySalaryDescWithFilteredSalary() {
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 1000);
        Employee second = new Employee("Dmitry", now, now, 2000);

        reportEngine.setReport(new HtmlReport(fields));

        storeService.add(first);
        storeService.add(second);

        StringBuilder expect = new StringBuilder()
                .append("<html><body><table>")
                .append("<tr><td>Name</td><td>Salary</td></tr>")
                .append("<tr><td>").append(second.getName()).append("</td><td>")
                .append(format.format(second.getSalary())).append("</td></tr>")
                .append("<tr><td>").append(first.getName()).append("</td><td>")
                .append(format.format(first.getSalary())).append("</td></tr>")
                .append("</table></body></html>");
        assertThat(reportEngine.generate(em -> true, new SalaryComparator().reversed()), is(expect.toString()));
    }
}
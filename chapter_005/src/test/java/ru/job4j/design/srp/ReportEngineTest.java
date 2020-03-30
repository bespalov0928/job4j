package ru.job4j.design.srp;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.design.srp.comparator.SalaryComparator;
import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.report.*;
import ru.job4j.design.srp.report.factory.AbstractReportFactory;
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
    private AbstractReportFactory reportFactory = new AbstractReportFactory(fields);
    private ReportEngine reportEngine = new ReportEngine(storeService, reportFactory.build(ReportType.CSV));

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

        reportEngine.setReport(reportFactory.build(ReportType.CSV));

        String expect = "Name;Salary"
                + System.lineSeparator()
                + second.getName() + ";"
                + format.format(second.getSalary())
                + System.lineSeparator()
                + first.getName() + ";"
                + format.format(first.getSalary())
                + System.lineSeparator();
        assertThat(reportEngine.generate(em -> true), is(expect));
    }

    @Test
    public void generateHtmlReportSortedByNameAscWithFilteredSalary() {
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 1000);
        Employee second = new Employee("Dmitry", now, now, 2000);

        reportEngine.setReport(reportFactory.build(ReportType.HTML));

        storeService.add(first);
        storeService.add(second);

        String expect = "<html><body><table>"
                + "<tr><th>Name</th><th>Salary</th></tr>"
                + "<tr><td>" + second.getName() + "</td><td>"
                + format.format(second.getSalary()) + "</td></tr>"
                + "<tr><td>" + first.getName() + "</td><td>"
                + format.format(first.getSalary()) + "</td></tr>"
                + "</table></body></html>";
        assertThat(reportEngine.generate(em -> true), is(expect));
    }

    @Test
    public void generateHtmlReportSortedBySalaryDescWithFilteredSalary() {
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 1000);
        Employee second = new Employee("Dmitry", now, now, 2000);

        reportEngine.setReport(reportFactory.build(ReportType.HTML));

        storeService.add(first);
        storeService.add(second);

        String expect = "<html><body><table>"
                + "<tr><th>Name</th><th>Salary</th></tr>"
                + "<tr><td>" + second.getName() + "</td><td>"
                + format.format(second.getSalary()) + "</td></tr>"
                + "<tr><td>" + first.getName() + "</td><td>"
                + format.format(first.getSalary()) + "</td></tr>"
                + "</table></body></html>";
        assertThat(reportEngine.generate(em -> true, new SalaryComparator().reversed()), is(expect));
    }

    @Test
    public void generateXmlAndJsonReport() {
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 1000);
        Employee second = new Employee("Dmitry", now, now, 2000);

        reportEngine.setReport(reportFactory.build(ReportType.XML));

        storeService.add(first);
        storeService.add(second);
        var expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><report><employees><employee><name>"
                + second.getName() + "</name><salary>"
                + format.format(second.getSalary()) + "</salary></employee><employee><name>"
                + first.getName() + "</name><salary>"
                + format.format(first.getSalary()) + "</salary></employee></employees></report>";

        assertThat(reportEngine.generate(em -> true, new SalaryComparator().reversed()), is(expected));

        reportEngine.setReport(reportFactory.build(ReportType.JSON));

        expected = "{\"report\":[{\"name\":\""
                + second.getName() + "\",\"salary\":\""
                + format.format(second.getSalary()) + "\"},{\"name\":\""
                + first.getName() + "\",\"salary\":\""
                + format.format(first.getSalary()) + "\"}]}";

        assertThat(reportEngine.generate(em -> true, new SalaryComparator().reversed()), is(expected));
    }
}
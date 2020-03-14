package ru.job4j.magnet.converter;

import org.junit.Test;
import ru.job4j.magnet.Magnet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class XSQTConverterTest {

    @Test
    public void whenCOnverterShouldProduceProperResult() {
        var xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<entries>\n"
                + "    <entry>\n"
                + "        <field>1</field>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <field>2</field>\n"
                + "    </entry>\n"
                + "</entries>\n";
        var expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><entry field=\"1\"/><entry field=\"2\"/></entries>";
        var result = new ByteArrayOutputStream();

        XSQTConverter.convert(
                new ByteArrayInputStream(xmlData.getBytes()),
                result,
                new File(Magnet.class.getClassLoader().getResource("schema.xsl").getPath()));

        assertThat(result.toString(), is(expected));
    }
}

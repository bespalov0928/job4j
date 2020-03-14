package ru.job4j.magnet.handler;

import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SaxParserHandlerTest {

    @Test
    public void whenResultIs3ThanParserShouldPrint3() {
        var source = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><entry field=\"1\"/><entry field=\"2\"/></entries>";
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            var result = new ByteArrayOutputStream();

            parser.parse(
                    new ByteArrayInputStream(source.getBytes()),
                    new SaxParserHandler(new PrintStream(result)));

            assertThat(Integer.parseInt(result.toString()), is(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

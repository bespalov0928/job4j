package ru.job4j.magnet.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.PrintStream;

public class SaxParserHandler extends DefaultHandler {
    private int result;
    private PrintStream outputStream;

    public SaxParserHandler(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("entry")) {
            String field = attributes.getValue("field");
            result = result + Integer.parseInt(field);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        outputStream.print(result);
        outputStream.close();
    }
}

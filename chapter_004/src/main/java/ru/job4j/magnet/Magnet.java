package ru.job4j.magnet;

import ru.job4j.jdbc.connection.ConnectionManagerImpl;
import ru.job4j.magnet.converter.XSQTConverter;
import ru.job4j.magnet.handler.SaxParserHandler;
import ru.job4j.magnet.store.OutputStreamXmlStore;
import ru.job4j.magnet.store.StoreSql;
import ru.job4j.properties.FileProperties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Magnet {
    private static final String ENTRIES_TO_XML_FILENAME = "magnet.xml";
    private static final String XML_TO_XSLT_FILENAME = "xslt_magnet.xml";
    private static final String XSL_SCHEMA_FILENAME = "schema.xsl";
    private static final String PROPERTIES_FILENAME = "sqlite.properties";

    public static void main(String[] args) throws FileNotFoundException {
        var connectionManager = new ConnectionManagerImpl(new FileProperties(PROPERTIES_FILENAME));
        var sqlStore = new StoreSql(connectionManager.getConnection());
        sqlStore.generate(1000000);

        String tempDir = System.getProperty("java.io.tmpdir");

        File source = new File(tempDir + ENTRIES_TO_XML_FILENAME);
        var xmlStore = new OutputStreamXmlStore(new FileOutputStream(source));
        xmlStore.store(sqlStore.load());

        try {
            File dest = new File(tempDir + XML_TO_XSLT_FILENAME);

            XSQTConverter.convert(
                    new FileInputStream(source),
                    new FileOutputStream(dest),
                    new File(Magnet.class.getClassLoader().getResource(XSL_SCHEMA_FILENAME).getPath()));

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(dest, new SaxParserHandler(System.out));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

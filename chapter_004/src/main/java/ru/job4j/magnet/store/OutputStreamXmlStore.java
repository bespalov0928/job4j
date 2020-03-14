package ru.job4j.magnet.store;

import ru.job4j.magnet.entity.Entries;
import ru.job4j.magnet.entity.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class OutputStreamXmlStore implements XmlStore {
    private OutputStream outputStream;

    public OutputStreamXmlStore(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void store(List<Entry> entries) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(new Entries(entries), outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            closeStream();
        }
    }

    private void closeStream() {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

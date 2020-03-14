package ru.job4j.magnet.store;

import org.junit.Test;
import ru.job4j.magnet.entity.Entry;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OutputStreamXmlStoreTest {

    @Test
    public void whenSave2EntriesThanStoreShouldGenerateCorrectXml() {
        var actualXML = new ByteArrayOutputStream();
        var store = new OutputStreamXmlStore(actualXML);
        store.store(List.of(new Entry(1), new Entry(2)));
        String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<entries>\n"
                + "    <entry>\n"
                + "        <field>1</field>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <field>2</field>\n"
                + "    </entry>\n"
                + "</entries>\n";
        assertThat(new String(actualXML.toByteArray()), is(expectedXML));
    }
}

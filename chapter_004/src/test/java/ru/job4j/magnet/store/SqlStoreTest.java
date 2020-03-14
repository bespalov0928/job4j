package ru.job4j.magnet.store;

import org.junit.Test;
import ru.job4j.jdbc.connection.ConnectionManagerImpl;
import ru.job4j.jdbc.connection.IConnectionManager;
import ru.job4j.magnet.entity.Entry;
import ru.job4j.properties.FileProperties;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqlStoreTest {

    @Test
    public void whenGenerateTwoEntriesLoadShouldReturnListOfTwoEntries() {
        IConnectionManager manager = new ConnectionManagerImpl(new FileProperties("test.properties"));
        var store = new StoreSql(manager.getConnection());
        store.generate(2);
        var result = store.load();
        assertThat(result, is(List.of(new Entry(1), new Entry(2))));
    }
}

package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileDataProviderImplTest {

    @Test
    public void whenFilePresentThanShouldReturnFileContent() {
        String data = new FileDataProviderImpl().getData("names.txt");
        assertThat(data, is("test"));
    }
}
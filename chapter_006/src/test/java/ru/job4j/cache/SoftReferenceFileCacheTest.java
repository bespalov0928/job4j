package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SoftReferenceFileCacheTest {
    private static final String FILENAME = "test.txt";

    @Test
    public void whenGetFileDataFirstTimeThanDataShouldBeGetFromFile() {
        FileDataProvider provider = mock(FileDataProvider.class);
        var cache = new SoftReferenceFileCache(provider);

        cache.getData(FILENAME);

        verify(provider, times(1)).getData(anyString());
    }

    @Test
    public void whenGetFileDataSecondTimeThanDataShouldBeGetFromCache() {
        FileDataProvider provider = mock(FileDataProvider.class);
        when(provider.getData(anyString())).thenReturn("");
        var cache = new SoftReferenceFileCache(provider);

        cache.getData(FILENAME);
        cache.getData(FILENAME);

        verify(provider, times(1)).getData(anyString());
    }

    @Test
    public void whenGetFileDataThanCorrectDataShouldBeReturned() {
        String expected = "Test data.";
        FileDataProvider provider = filename -> expected;
        var cache = new SoftReferenceFileCache(provider);

        assertThat(cache.getData(FILENAME), is(expected));
        assertThat(cache.getData(FILENAME), is(expected));
    }

    @Test
    public void whenGetFileDataAfterGCThanDataShouldBeGetFromFile() {
        FileDataProvider provider = mock(FileDataProvider.class);
        var cache = new SoftReferenceFileCache(provider);

        cache.getData(FILENAME);
        GcUtils.tryToAllocateAllAvailableMemory();
        cache.getData(FILENAME);

        verify(provider, times(2)).getData(anyString());
    }
}
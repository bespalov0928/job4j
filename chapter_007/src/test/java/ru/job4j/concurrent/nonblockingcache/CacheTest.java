package ru.job4j.concurrent.nonblockingcache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenTryingToUpdateModelAndVersionsAreNotEqualThanExceptionShouldBeThrown() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        var cache = new Cache();
        cache.add(new Base(1, 1));

        Thread thread = new Thread(() -> cache.update(new Base(1, 1)));
        thread.start();
        thread.join();

        thread = new Thread(() -> {
            try {
                cache.update(new Base(1, 1));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });
        thread.start();
        thread.join();

        assertThat(ex.get().getMessage(), is("Update is not performed: different versions!"));
    }
}
package ru.job4j.analize;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizerTest {

    @Test
    public void whenOneChangedOneDeletedOneAdded() {
        List<Analizer.User> previous = new ArrayList<>();
        List<Analizer.User> current = new ArrayList<>();
        previous.add(new Analizer.User(1, "1"));
        previous.add(new Analizer.User(2, "2"));
        previous.add(new Analizer.User(3, "3"));

        current.add(new Analizer.User(1, "11"));
        current.add(new Analizer.User(3, "3"));
        current.add(new Analizer.User(4, "4"));

        Analizer.Info info = new Analizer().diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenAllDeleted() {
        List<Analizer.User> previous = new ArrayList<>();
        List<Analizer.User> current = new ArrayList<>();
        previous.add(new Analizer.User(1, "1"));
        previous.add(new Analizer.User(2, "2"));
        previous.add(new Analizer.User(3, "3"));

        Analizer.Info info = new Analizer().diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(3));
    }

    @Test
    public void whenAllChanged() {
        List<Analizer.User> previous = new ArrayList<>();
        List<Analizer.User> current = new ArrayList<>();
        previous.add(new Analizer.User(1, "1"));
        previous.add(new Analizer.User(2, "2"));
        previous.add(new Analizer.User(3, "3"));

        current.add(new Analizer.User(1, "2"));
        current.add(new Analizer.User(2, "3"));
        current.add(new Analizer.User(3, "4"));

        Analizer.Info info = new Analizer().diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(3));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenAllAdded() {
        List<Analizer.User> previous = new ArrayList<>();
        List<Analizer.User> current = new ArrayList<>();
        previous.add(new Analizer.User(1, "1"));
        previous.add(new Analizer.User(2, "2"));
        previous.add(new Analizer.User(3, "3"));

        current.add(new Analizer.User(4, "2"));
        current.add(new Analizer.User(5, "3"));
        current.add(new Analizer.User(6, "4"));

        Analizer.Info info = new Analizer().diff(previous, current);
        assertThat(info.getAdded(), is(3));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(3));
    }
}

package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.collection.*;

import java.util.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    @Test
    public void whenAsc() {
        Set<User> users = new TreeSet<>();
        users.add(new User("Petr", 32));
        users.add(new User("Ivan", 31));
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("Ivan", 31)));
        assertThat(it.next(), is(new User("Petr", 32)));
    }

    @Test
    public void whenNamesEqualAsc() {
        Set<User> users = new TreeSet<>();
        users.add(new User("Ivan", 32));
        users.add(new User("Ivan", 11));
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("Ivan", 11)));
        assertThat(it.next(), is(new User("Ivan", 32)));
    }

    @Test
    public void whenComparePertVSIvan() {
        int rsl = new User("Petr", 32)
                .compareTo(
                        new User("Ivan", 31)
                );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenUserByNameAsc() {
        List<User> users = new ArrayList<>();
        users.add(new User("Ivan", 32));
        users.add(new User("Denis", 11));
        Collections.sort(users, new UserByNameAscComparator());
        assertThat(users, is(Arrays.asList(new User("Denis", 11), new User("Ivan", 32))));
    }

    @Test
    public void whenUserByNameDesc() {
        List<User> users = new ArrayList<>();
        users.add(new User("Denis", 32));
        users.add(new User("Ivan", 11));
        Collections.sort(users, new UserByNameDescComparator());
        assertThat(users, is(Arrays.asList(new User("Ivan", 11), new User("Denis", 32))));
    }

    @Test
    public void whenUserByAgeAsc() {
        List<User> users = new ArrayList<>();
        users.add(new User("Ivan", 11));
        users.add(new User("Denis", 32));
        Collections.sort(users, new UserByAgeAscComparator());
        assertThat(users, is(Arrays.asList(new User("Ivan", 11), new User("Denis", 32))));
    }

    @Test
    public void whenUserByAgeDesc() {
        List<User> users = new ArrayList<>();
        users.add(new User("Denis", 11));
        users.add(new User("Ivan", 32));
        Collections.sort(users, new UserByAgeDescComparator());
        assertThat(users, is(Arrays.asList(new User("Ivan", 32), new User("Denis", 11))));
    }

    @Test
    public void whenComparatorByNameAndAgeNameEqual() {
        Comparator<User> cmpNameAge = new UserByNameAscComparator().thenComparing(new UserByAgeAscComparator());
        int rsl = cmpNameAge.compare(
                new User("Test", 0),
                new User("Test", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByNameAndAge() {
        Comparator<User> cmpNameAge = new UserByNameAscComparator().thenComparing(new UserByAgeAscComparator());
        int rsl = cmpNameAge.compare(
                new User("Test", 0),
                new User("Asc", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByNameDescAndAgeNameEqual() {
        Comparator<User> cmpNameAge = new UserByNameDescComparator().thenComparing(new UserByAgeDescComparator());
        int rsl = cmpNameAge.compare(
                new User("Test", 1),
                new User("Test", 0)
        );
        assertThat(rsl, lessThan(0));
    }
}

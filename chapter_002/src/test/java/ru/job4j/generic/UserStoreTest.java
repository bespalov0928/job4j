package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    UserStore userStore;

    @Before
    public void setUp() {
        userStore = new UserStore(3);
    }

    @Test
    public void whenPresentFindByIdShouldReturnUser() {
        User user = new User("1");
        userStore.add(user);
        User result = userStore.findById("1");
        assertThat(result, is(user));
    }

    @Test
    public void whenAbsentFindByIdShouldReturnNull() {
        User user = new User("1");
        userStore.add(user);
        User result = userStore.findById("2");
        assertNull(result);
    }

    @Test
    public void whenReplacedShouldReturnTrue() {
        User user = new User("1");
        userStore.add(user);
        User user2 = new User("2");
        boolean result = userStore.replace("1", user2);
        assertTrue(result);
    }

    @Test
    public void whenNotReplacedShouldReturnFalse() {
        User user = new User("1");
        userStore.add(user);
        User user2 = new User("2");
        boolean result = userStore.replace("3", user2);
        assertFalse(result);
    }

    @Test
    public void whenReplacedShouldFindUserById() {
        User user = new User("1");
        userStore.add(user);
        User user2 = new User("2");
        userStore.replace("1", user2);
        User result = userStore.findById("2");
        assertThat(result, is(user2));
    }

    @Test
    public void whenDeletedShouldReturnTrue() {
        User user = new User("1");
        userStore.add(user);
        boolean result = userStore.delete("1");
        assertTrue(result);
    }

    @Test
    public void whenNotDeletedShouldReturnFalse() {
        User user = new User("1");
        userStore.add(user);
        boolean result = userStore.delete("2");
        assertFalse(result);
    }

    @Test
    public void whenDeletedShouldNotFindUserById() {
        User user = new User("1");
        userStore.add(user);
        userStore.delete("1");
        User result = userStore.findById("1");
        assertNull(result);
    }
}

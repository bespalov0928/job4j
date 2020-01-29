package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {
    Store<Role> roleStore;

    @Before
    public void setUp() {
        roleStore = new RoleStore(3);
    }

    @Test
    public void whenPresentFindByIdShouldReturnUser() {
        Role role = new Role("1");
        roleStore.add(role);
        Role result = roleStore.findById("1");
        assertThat(result, is(role));
    }

    @Test
    public void whenAbsentFindByIdShouldReturnNull() {
        Role role = new Role("1");
        roleStore.add(role);
        Role result = roleStore.findById("2");
        assertNull(result);
    }

    @Test
    public void whenReplacedShouldReturnTrue() {
        Role role = new Role("1");
        roleStore.add(role);
        Role role2 = new Role("2");
        boolean result = roleStore.replace("1", role2);
        assertTrue(result);
    }

    @Test
    public void whenNotReplacedShouldReturnFalse() {
        Role role = new Role("1");
        roleStore.add(role);
        Role role2 = new Role("2");
        boolean result = roleStore.replace("3", role2);
        assertFalse(result);
    }

    @Test
    public void whenReplacedShouldFindUserById() {
        Role role = new Role("1");
        roleStore.add(role);
        Role role2 = new Role("2");
        roleStore.replace("1", role2);
        Role result = roleStore.findById("2");
        assertThat(result, is(role2));
    }

    @Test
    public void whenDeletedShouldReturnTrue() {
        Role role = new Role("1");
        roleStore.add(role);
        boolean result = roleStore.delete("1");
        assertTrue(result);
    }

    @Test
    public void whenNotDeletedShouldReturnFalse() {
        Role role = new Role("1");
        roleStore.add(role);
        boolean result = roleStore.delete("2");
        assertFalse(result);
    }

    @Test
    public void whenDeletedShouldNotFindUserById() {
        Role role = new Role("1");
        roleStore.add(role);
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertNull(result);
    }
}

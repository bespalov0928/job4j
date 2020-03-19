package ru.job4j.tracker.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.jdbc.connection.ConnectionManagerImpl;
import ru.job4j.jdbc.connection.ConnectionRollback;
import ru.job4j.properties.FileProperties;
import ru.job4j.tracker.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class SqlItemRepositoryIntegrationTest {
    private Connection connection;
    private IItemRepository repository;

    @Before
    public void setUp() throws SQLException {
        connection = ConnectionRollback.create(
                new ConnectionManagerImpl(new FileProperties("prod.properties")).getConnection());
        repository = new SqlItemRepository(connection);
    }

    @After
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void whenRepositoryIsEmptyThanFindAllShouldReturnEmptyList() {
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    public void whenDeleteItemWhichIsNotPresentThanDeleteShouldReturnFalse() {
        assertFalse(repository.delete("1"));
    }

    @Test
    public void whenThereIsNoItemsWithGivenNameThanFindByNameShouldReturnEmptyList() {
        assertTrue(repository.findByName("No Such Name").isEmpty());
    }

    @Test
    public void whenAddNewItemThanIdShouldNotBeEqualsToPreviousItem() {
        Item first = new Item("test1");
        first = repository.add(first);
        Item second = new Item("test2");
        second = repository.add(second);
        assertNotEquals(first.getId(), second.getId());
    }

    @Test
    public void whenItemsArePresentThanFindAllShouldReturnListOfAllItems() {
        repository.add(new Item("Test"));
        repository.add(new Item("Test"));
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void whenItemsWithGivenNameArePresentThanFindByNameShouldReturnListOfAllItems() {
        Item first = new Item("Test");
        first = repository.add(first);
        Item second = new Item("Test");
        second = repository.add(second);
        List<Item> expected = List.of(first, second);
        assertThat(repository.findByName("Test"), is(expected));
    }

    @Test
    public void whenAddNewItemThanIdShouldBeGeneratedAutomatically() {
        Item item = new Item("test1");
        assertEquals("", item.getId());
        item = repository.add(item);
        assertNotEquals("", item.getId());
    }

    @Test
    public void whenItemPresentThanFindByIdShouldReturnCorrectItem() {
        Item expected = new Item("testFindById");
        expected = repository.add(expected);
        Item result = repository.findById(expected.getId());
        assertThat(expected, is(result));
    }

    @Test
    public void whenItemNotPresentThanFindByIdShouldReturnNull() {
        Item item = new Item("testFindById");
        item = repository.add(item);
        Item result = repository.findById(item.getId() + "1");
        assertNull(result);
    }

    @Test
    public void whenItemIsPresentThanReplaceShouldReturnTrue() {
        Item item = new Item("testReplace");
        item = repository.add(item);
        Item replacement = new Item("Replacement");
        assertTrue(repository.replace(item.getId(), replacement));
        assertEquals("Replacement", repository.findById(item.getId()).getName());
    }

    @Test
    public void whenDeleteItemWhichIsPresentThanDeleteShouldReturnTrueAndFindByIdShouldReturnNull() {
        Item item = new Item("testReplace");
        item = repository.add(item);
        assertTrue(repository.delete(item.getId()));
        assertNull(repository.findById(item.getId()));
    }
}

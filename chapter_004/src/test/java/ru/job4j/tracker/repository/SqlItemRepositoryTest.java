package ru.job4j.tracker.repository;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.job4j.tracker.Item;
import ru.job4j.jdbc.connection.ConnectionManagerImpl;
import ru.job4j.jdbc.connection.IConnectionManager;
import ru.job4j.properties.FileProperties;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SqlItemRepositoryTest {
    private static IConnectionManager connectionManager;
    private static IItemRepository repository;

    @BeforeClass
    public static void beforeClass() throws Exception {
        connectionManager = new ConnectionManagerImpl(new FileProperties("test.properties"));
        repository = new SqlItemRepository(connectionManager.getConnection());
    }

    @AfterClass
    public static void afterClass() throws Exception {
        try {
            ((ConnectionManagerImpl) connectionManager).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aWhenRepositoryIsEmptyThanFindAllShouldReturnEmptyList() {
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    public void bWhenDeleteItemWhichIsNotPresentThanDeleteShouldReturnFalse() {
        assertFalse(repository.delete("1"));
    }

    @Test
    public void cWhenAddNewItemThanIdShouldBeEqualsToPreviousItem() {
        Item first = new Item("test1");
        first = repository.add(first);
        Item second = new Item("test2");
        second = repository.add(second);
        assertNotEquals(first.getId(), second.getId());
    }

    @Test
    public void dWhenItemsArePresentThanFindAllShouldReturnListOfAllItems() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void eWhenThereIsNoItemsWithGivenNameThanFindByNameShouldReturnEmptyList() {
        assertTrue(repository.findByName("No Such Name").isEmpty());
    }

    @Test
    public void fWhenItemsWithGivenNameArePresentThanFindByNameShouldReturnListOfAllItems() {
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
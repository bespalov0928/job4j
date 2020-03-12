package ru.job4j.connection;

import org.junit.Test;
import ru.job4j.jdbc.connection.ConnectionManagerImpl;
import ru.job4j.properties.FileProperties;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class ConnectionManagerImplTest {

    @Test
    public void testWhenConnectionIsEstablished() {
        var result = false;
        try (var connectionManager = new ConnectionManagerImpl(new FileProperties("test.properties"))) {
            result = connectionManager.getConnection().isValid(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    @Test(expected = IllegalStateException.class)
    public void testWhenPropertiesAreNotValidThanExceptionShouldBeThrown() throws Exception {
        try (var connectionManager = new ConnectionManagerImpl(new FileProperties("dbmanagertest.properties"))) {
            connectionManager.getConnection();
        }
    }

    @Test
    public void testWhenConnectionIsClosingAutomatically() {
        Connection connection = null;
        try (var connectionManager = new ConnectionManagerImpl(new FileProperties("test.properties"))) {
            connection = connectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertTrue(connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

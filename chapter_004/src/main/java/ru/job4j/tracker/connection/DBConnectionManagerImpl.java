package ru.job4j.tracker.connection;

import ru.job4j.tracker.properties.IProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionManagerImpl implements IDBConnectionManager, AutoCloseable {
    private final static String CONNECTION_URL_PROPERTY = "url";
    private final static String CONNECTION_USER_PROPERTY = "username";
    private final static String CONNECTION_PASSWORD_PROPERTY = "password";

    private IProperties properties;
    private Connection connection;

    public DBConnectionManagerImpl(IProperties properties) {
        this.properties = properties;
    }

    @Override
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                    properties.getValue(CONNECTION_URL_PROPERTY),
                    properties.getValue(CONNECTION_USER_PROPERTY),
                    properties.getValue(CONNECTION_PASSWORD_PROPERTY)
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

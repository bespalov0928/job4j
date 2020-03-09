package ru.job4j.tracker.connection;

import java.sql.Connection;

public interface IDBConnectionManager {
    Connection getConnection();
}

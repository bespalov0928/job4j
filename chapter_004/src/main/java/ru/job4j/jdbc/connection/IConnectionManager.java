package ru.job4j.jdbc.connection;

import java.sql.Connection;

public interface IConnectionManager {
    Connection getConnection();
}

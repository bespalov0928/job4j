package ru.job4j.magnet.store;

import ru.job4j.jdbc.executor.QueryExecutor;
import ru.job4j.magnet.entity.Entry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreSql {
    private final static String DROP_SQL = "DROP TABLE IF EXISTS `entry`;";
    private final static String CREATE_SQL = "CREATE TABLE IF NOT EXISTS `entry` (`value` int(11) NOT NULL);";
    private final static String INSERT_SQL = "INSERT INTO `entry` (`value`) VALUES(?);";
    private final static String SELECT_SQL = "SELECT `value` FROM `entry`;";

    private Connection connection;

    public StoreSql(Connection connection) {
        this.connection = connection;
        executeQuery(DROP_SQL);
        executeQuery(CREATE_SQL);
    }

    public void generate(int size) {
        setAutoCommit(false);
        try (var statement = connection.prepareStatement(INSERT_SQL)) {
            for (int value = 1; value <= size; value++) {
                statement.setInt(1, value);
                statement.addBatch();
            }
            statement.executeBatch();
            commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAutoCommit(true);
    }

    public List<Entry> load() {
        var list = new ArrayList<Entry>();
        try (var statement = connection.prepareStatement(SELECT_SQL)) {
            var result = QueryExecutor.executeQuery(statement);
            while (result.next()) {
                list.add(new Entry(result.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void executeQuery(String query) {
        try (var statement = connection.prepareStatement(query)) {
            QueryExecutor.execute(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAutoCommit(boolean value) {
        try {
            connection.setAutoCommit(value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void commit() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}

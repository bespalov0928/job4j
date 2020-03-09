package ru.job4j.tracker.repository;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.executor.QueryExecutor;
import ru.job4j.tracker.parser.ResultSetParser;

import java.sql.*;
import java.util.List;

public class SqlItemRepository implements IItemRepository {
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS item ( "
                    + "id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                    + "name varchar(255) NOT NULL);";

    private static final String INSERT_SQL = "INSERT INTO item (name) VALUES (?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM item WHERE id = ?";
    private static final String SELECT_BY_NAME_SQL = "SELECT * FROM item WHERE name = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM item";
    private static final String UPDATE_BY_ID_SQL = "UPDATE item SET name = ? WHERE id = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM item WHERE id = ?";

    private Connection connection;

    public SqlItemRepository(Connection connection) {
        this.connection = connection;
        QueryExecutor.execute(prepareStatement(CREATE_TABLE_SQL));
    }

    @Override
    public Item add(Item item) {
        var preparedStatement = prepareStatement(INSERT_SQL, item.getName());
        QueryExecutor.executeUpdate(preparedStatement);
        try {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(Integer.toString(resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item findById(String id) {
        var resultSet = QueryExecutor.executeQuery(prepareStatement(SELECT_BY_ID_SQL, id));
        var list = ResultSetParser.parseToList(resultSet);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public boolean replace(String id, Item item) {
        var preparedStatement = prepareStatement(UPDATE_BY_ID_SQL, item.getName(), id);
        var result = QueryExecutor.executeUpdate(preparedStatement) > 0;
        if (result) {
            item.setId(id);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        var preparedStatement = prepareStatement(DELETE_BY_ID_SQL, id);
        return QueryExecutor.executeUpdate(preparedStatement) > 0;
    }

    @Override
    public List<Item> findAll() {
        var resultSet = QueryExecutor.executeQuery(prepareStatement(SELECT_ALL_SQL));
        return ResultSetParser.parseToList(resultSet);
    }

    @Override
    public List<Item> findByName(String key) {
        var resultSet = QueryExecutor.executeQuery(prepareStatement(SELECT_BY_NAME_SQL, key));
        return ResultSetParser.parseToList(resultSet);
    }

    private PreparedStatement prepareStatement(String query, String... values) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
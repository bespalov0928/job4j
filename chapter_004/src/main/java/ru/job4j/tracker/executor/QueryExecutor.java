package ru.job4j.tracker.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {
    private static final Logger LOG = LogManager.getLogger(QueryExecutor.class.getName());

    private interface ThrowingFunction<T, R> {
        R apply(T t) throws Exception;
    }

    public static boolean execute(PreparedStatement preparedStatement) {
        return execute(preparedStatement, PreparedStatement::execute);
    }

    public static ResultSet executeQuery(PreparedStatement preparedStatement) {
        return execute(preparedStatement, PreparedStatement::executeQuery);
    }

    public static int executeUpdate(PreparedStatement preparedStatement) {
        return execute(preparedStatement, PreparedStatement::executeUpdate);
    }

    private static <R> R execute(
            PreparedStatement preparedStatement,
            ThrowingFunction<PreparedStatement, R> function) {
        R result = null;
        try {
            result = function.apply(preparedStatement);
        } catch (SQLException e) {
            LOG.error(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

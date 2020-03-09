package ru.job4j.tracker.parser;

import ru.job4j.tracker.Item;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultSetParser {
    public static List<Item> parseToList(ResultSet resultSet) {
        List<Item> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getString("id"));
                result.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

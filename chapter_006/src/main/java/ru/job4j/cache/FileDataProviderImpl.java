package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileDataProviderImpl implements FileDataProvider {
    @Override
    public String getData(String filename) {
        var data = "";
        var resource = FileDataProvider.class.getClassLoader().getResourceAsStream(filename);
        try (var bf = new BufferedReader(new InputStreamReader(resource))) {
            data = bf.lines().reduce("", String::concat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

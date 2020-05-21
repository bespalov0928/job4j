package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileDataProviderImpl implements FileDataProvider {
    @Override
    public String getData(String filename) {
        var data = "";
        var resource = FileDataProvider.class.getClassLoader().getResourceAsStream(filename);
        if (resource != null) {
            var bf = new BufferedReader(new InputStreamReader(resource));
            data = bf.lines().reduce("", String::concat);
        }
        return data;
    }
}

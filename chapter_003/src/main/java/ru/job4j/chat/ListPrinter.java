package ru.job4j.chat;

import java.util.ArrayList;
import java.util.List;

public class ListPrinter implements Printer {
    List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    @Override
    public void print(String output) {
        list.add(output);
    }
}

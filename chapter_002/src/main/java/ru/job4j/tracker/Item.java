package ru.job4j.tracker;

import java.util.Objects;

public class Item implements Comparable {
    private String id = "";
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = super.equals(obj);
        if (obj instanceof Item) {
            Item item = (Item) obj;
            if (this.name.equals(item.name) && this.id.equals(item.id)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Object o) {

        return this.name.compareTo(((Item) o).getName());
    }
}

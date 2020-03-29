package ru.job4j.design.lsp.food;

import java.time.LocalDate;
import java.util.Date;

public class Food {
    private String name;
    private LocalDate createDate;
    private LocalDate expireDate;
    private double price;

    public Food(String name, LocalDate createDate, LocalDate expireDate, double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public double getPrice() {
        return price;
    }
}

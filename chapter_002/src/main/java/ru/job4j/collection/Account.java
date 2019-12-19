package ru.job4j.collection;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private double amount;
    private String requisites;

    public Account(double amount, String requisites) {
        this.amount = amount;
        this.requisites = requisites;
    }

    public String getRequisites() {
        return requisites;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, requisites);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Account account = (Account) obj;
        return amount == account.amount
                && requisites.equals(account.requisites);
    }
}

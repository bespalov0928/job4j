package ru.job4j.collection;

import java.util.Objects;

public class User implements Comparable<User> {

    private int age;
    private String name;

    public String getPassport() {
        return passport;
    }

    private String passport;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, String passport) {
        this.name = name;
        this.age = age;
        this.passport = passport;
    }

    @Override
    public int compareTo(User o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
            result = Integer.compare(this.age, o.age);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age
                && Objects.equals(name, user.name)
                && Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, passport);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

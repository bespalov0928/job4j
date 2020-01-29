package ru.job4j.generic;

import java.util.Iterator;

public class UserStore extends AbstractStore<User> implements Store<User> {

    public UserStore(int length) {
        super(length);
    }

    @Override
    public void add(User model) {
        super.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return super.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }

    @Override
    public User findById(String id) {
        return super.findById(id);
    }
}

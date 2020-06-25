package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    private static class User {
        private final int id;
        private final int amount;

        private User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public int getAmount() {
            return amount;
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
            return id == user.id && amount == user.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, amount);
        }
    }

    public synchronized boolean add(User user) {
        users.put(user.id, user);
        return true;
    }

    public synchronized boolean update(User user) {
        users.put(user.id, user);
        return true;
    }

    public synchronized boolean delete(User user) {
        users.remove(user.id);
        return true;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        var fromUser = users.get(fromId);
        var toUser = users.get(toId);
        users.put(fromUser.id, new User(fromUser.id, fromUser.amount - amount));
        users.put(toUser.id, new User(toUser.id, toUser.amount - amount));
    }
}

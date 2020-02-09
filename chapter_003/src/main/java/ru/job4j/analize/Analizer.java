package ru.job4j.analize;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Analizer {

    public Info diff(List<User> previous, List<User> current) {
        int deleted = 0, changed = 0;
        Map<Integer, User> map = current.stream().collect(Collectors.toMap(User::getId, Function.identity()));

        for (User prevUser : previous) {
            User currUser = map.get(prevUser.id);
            if (currUser == null) {
                deleted++;
                continue;
            }
            if (!prevUser.equals(currUser)) {
                changed++;
            }
            map.remove(prevUser.id);
        }

        return new Info(map.size(), changed, deleted);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
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
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}

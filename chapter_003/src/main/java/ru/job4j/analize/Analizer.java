package ru.job4j.analize;

import java.util.*;

public class Analizer {

    public Info diff(List<User> previous, List<User> current) {
        int deleted = 0, changed = 0;
        var temp = new ArrayList<>(current);

        for (User prevUser : previous) {
            Optional<User> currUser = current.stream().filter(u -> u.id == prevUser.id).findFirst();
            if (currUser.isEmpty()) {
                deleted++;
                continue;
            }
            User user = currUser.get();
            if (!prevUser.equals(user)) {
                changed++;
            }
            temp.remove(user);
        }

        return new Info(temp.size(), changed, deleted);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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

package ru.job4j.tracker.repository;

import static org.hibernate.testing.transaction.TransactionUtil.*;
import ru.job4j.tracker.model.Item;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

public class JpaItemRepository implements IItemRepository {
    private final EntityManagerFactory entityManagerFactory;

    public JpaItemRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ru.job4j.tracker.jpa");
    }

    @Override
    public Item add(Item item) {
        doInJPA(() -> entityManagerFactory, e -> {
            e.persist(item);
        });
        return item;
    }

    @Override
    public Item findById(long id) {
        return doInJPA(() -> entityManagerFactory, e -> {
            return e.find(Item.class, id);
        });
    }

    @Override
    public boolean replace(long id, Item item) {
        return doInJPA(() -> entityManagerFactory, e -> {
            var result = false;
            var found = e.find(Item.class, id);
            if (Objects.nonNull(found)) {
                result = true;
                found.setName(item.getName());
                e.persist(found);
            }
            return result;
        });
    }

    @Override
    public boolean delete(long id) {
        return doInJPA(() -> entityManagerFactory, e -> {
            var result = false;
            var found = e.find(Item.class, id);
            if (Objects.nonNull(found)) {
                result = true;
                e.remove(found);
            }
            return result;
        });
    }

    @Override
    public List<Item> findAll() {
        return doInJPA(() -> entityManagerFactory, e -> {
            return e.createQuery("from Item").getResultList();
        });
    }

    @Override
    public List<Item> findByName(String key) {
        return doInJPA(() -> entityManagerFactory, e -> {
            return e.createQuery("from Item where name like concat('%',:name,'%')")
                    .setParameter("name", key)
                    .getResultList();
        });
    }
}

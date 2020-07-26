package ru.job4j.tracker.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.model.Item;

import java.util.List;
import java.util.Objects;

public class HibernateItemRepository implements IItemRepository {
    private final SessionFactory sessionFactory;
    public HibernateItemRepository() {
        sessionFactory = new MetadataSources(new StandardServiceRegistryBuilder().configure().build())
                .buildMetadata().buildSessionFactory();
    }

    @Override
    public Item add(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public Item findById(long id) {
        var session = sessionFactory.openSession();
        session.beginTransaction();
        var item = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(long id, Item item) {
        var result = false;
        var session = sessionFactory.openSession();
        session.beginTransaction();
        var found = session.get(Item.class, id);
        if (Objects.nonNull(found)) {
            result = true;
            found.setName(item.getName());
            session.update(found);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public boolean delete(long id) {
        var result = false;
        var session = sessionFactory.openSession();
        session.beginTransaction();
        var item = session.get(Item.class, id);
        if (Objects.nonNull(item)) {
            session.delete(item);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        var query = session.createQuery("from Item where name like concat('%',:name,'%')");
        query.setParameter("name", key);
        List result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}

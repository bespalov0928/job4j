package ru.job4j.concurrent.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public void add(Base model) {
        cache.put(model.id, model);
    }

    public void update(Base model) {
        cache.computeIfPresent(model.id, (k, v) -> {
            if (v.version != model.version) {
                throw new OptimisticException("Update is not performed: different versions!");
            }
            model.version++;
            return model;
        });
    }

    public void delete(Base model) {
        cache.remove(model.id);
    }
}

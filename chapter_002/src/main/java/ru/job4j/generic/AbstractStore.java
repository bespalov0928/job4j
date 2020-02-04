package ru.job4j.generic;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> items;

    public AbstractStore(int length) {
        this.items = new SimpleArray<>(length);
    }

    @Override
    public void add(T model) {
        items.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = getIndexById(id);
        if (index > -1) {
            items.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = getIndexById(id);
        if (index > -1) {
            items.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T el : items) {
            if (el.getId().equals(id)) {
                result = el;
                break;
            }
        }
        return result;
    }

    private int getIndexById(String id) {
        int result = -1;
        T user = findById(id);
        if (user != null) {
            result = items.indexOf(user);
        }
        return result;
    }
}

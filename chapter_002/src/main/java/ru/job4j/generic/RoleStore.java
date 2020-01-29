package ru.job4j.generic;

public class RoleStore extends AbstractStore<Role> implements Store<Role> {
    public RoleStore(int length) {
        super(length);
    }

    @Override
    public void add(Role model) {
        super.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return super.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }

    @Override
    public Role findById(String id) {
        return super.findById(id);
    }
}

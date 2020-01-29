package ru.job4j.generic;

public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = super.equals(obj);
        if (!result && obj instanceof String) {
            Base base = (Base) obj;
            result = this.id.equals(base.id);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

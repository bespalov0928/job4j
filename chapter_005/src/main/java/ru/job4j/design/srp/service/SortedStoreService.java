package ru.job4j.design.srp.service;

import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SortedStoreService implements StoreService {
    private final static Comparator<Employee> COMPARATOR = Comparator.comparing(Employee::getName);

    private final Store store;

    public SortedStoreService(Store store) {
        this.store = store;
    }

    @Override
    public void add(Employee employee) {
        store.add(employee);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return find(filter, COMPARATOR);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter, Comparator<Employee> comparator) {
        return find(filter, comparator.thenComparing(COMPARATOR));
    }

    private List<Employee> find(Predicate<Employee> filter, Comparator<Employee> comparator) {
        return store.findBy(filter)
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}

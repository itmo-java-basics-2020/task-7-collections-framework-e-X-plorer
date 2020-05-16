package ru.ifmo.collections;

import java.util.*;

/**
 * Represents sorted set of unique values.
 * create() returns a SortedSet instance with natural ordering. (i.e. from smallest to largest in case of integer numbers)
 * from() is used to create a SortedSet instance with given Comparator.
 * Instances of a class can be created using only these two methods above.
 * <p>
 * This class should not be abstract and should be capable of adding/removing single/multiple elements.
 * It has two more methods: getSorted() and getReversed() which return an array of set contents in forward and reverse order respectively.
 * <p>
 * NB! This class must have only map(s) as an internal data structure(s).
 *
 * @param <T> set contents type
 */
public class SortedSet<T> extends AbstractSet<T> {
    private static final Object PRESENT = new Object();
    private final TreeMap<T, Object> contents;

    private SortedSet() {
        contents = new TreeMap<>();
    }

    private SortedSet(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator was null.");
        }

        contents = new TreeMap<>(comparator);
    }

    @Override
    public Iterator<T> iterator() {
        return contents.keySet().iterator();
    }

    @Override
    public int size() {
        return contents.size();
    }

    @Override
    public boolean add(T item) {
        return contents.put(item, PRESENT) == null;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Null collection is not allowed.");
        }

        boolean modified = false;
        for (var item : collection) {
            modified = (contents.put(item, PRESENT) == null) || modified;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        return contents.remove(o) != null;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Null collection is not allowed.");
        }

        boolean modified = false;
        for (var item : collection) {
            modified = (contents.remove(item) != null) || modified;
        }
        return modified;
    }

    public static <T> SortedSet<T> create() {
        return new SortedSet<>();
    }

    public static <T> SortedSet<T> from(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Null comparator is not allowed.");
        }

        return new SortedSet<>(comparator);
    }

    public List<T> getSorted() {
        return new ArrayList<>(contents.keySet());
    }

    public List<T> getReversed() {
        return new ArrayList<>(contents.descendingKeySet());
    }
}
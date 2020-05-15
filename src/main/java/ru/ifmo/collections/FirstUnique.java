package ru.ifmo.collections;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Design a class which contains integers and returns first unique integer (in order of addition).
 * FirstUniqueTest can be used as an example.
 */
public class FirstUnique {
    private static final int NO_UNIQUE_VALUES = -1;
    private Map<Integer, Uniqueness> uniqueNumbers;
    private Queue<Integer> queue;

    public FirstUnique(int[] numbers) {
        uniqueNumbers = new HashMap<>();
        queue = new LinkedList<>();
        for (var number : numbers) {
            add(number);
        }
    }

    public int showFirstUnique() {
        return queue.peek() == null ? NO_UNIQUE_VALUES : queue.peek();
    }

    public void add(int value) {
        if (uniqueNumbers.putIfAbsent(value, Uniqueness.UNIQUE) == null) {
            queue.add(value);
        } else {
            uniqueNumbers.put(value, Uniqueness.NOT_UNIQUE);
            while (uniqueNumbers.get(queue.peek()) == Uniqueness.NOT_UNIQUE) {
                queue.poll();
            }
        }
    }

    private enum Uniqueness {
        UNIQUE,
        NOT_UNIQUE
    }
}

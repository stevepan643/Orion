package com.steve.orion.util.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public class ObjectPool<T> {
    private final Deque<T> pool = new ArrayDeque<>();
    private final Supplier<T> factory;

    public ObjectPool(Supplier<T> factory) {
        this.factory = factory;
    }

    public T get() {
        return pool.isEmpty() ? factory.get() : pool.pop();
    }

    public void release(T object) {
        pool.push(object);
    }
}

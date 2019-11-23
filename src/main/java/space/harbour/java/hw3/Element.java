package main.java.space.harbour.java.hw3;

import java.util.Map;

public final class Element<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    public Element(final K k, final V v) {
        this.key = k;
        this.value = v;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(final V v) {
        this.value = v;
        return this.value;
    }
}

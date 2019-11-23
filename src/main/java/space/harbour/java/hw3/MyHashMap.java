package main.java.space.harbour.java.hw3;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public final class MyHashMap<K, V> implements Map<K, V> {

    private int size = 0;
    private final int bucketSize = 10;
    private Object[] buckets = new Object[bucketSize];

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(final Object key) {
        int hash = Math.abs(key.hashCode() % bucketSize);
        if (buckets[hash] == null) {
            return false;
        }

        if (buckets[hash] instanceof Element) {
            Element<K, V> element = ((Element<K, V>) buckets[hash]);

            if (element.getKey().equals(key)) {
                return true;
            }
        } else if (buckets[hash] instanceof LinkedList) {
            LinkedList<Element<K, V>> linkedList =
                    ((LinkedList<Element<K, V>>) buckets[hash]);

            for (Element<K, V> element : linkedList) {
                if (element.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(final Object value) {
        for (int i = 0; i < bucketSize; i++) {
            if (buckets[i] != null) {
                if (buckets[i] instanceof Element) {
                    Element<K, V> element = ((Element<K, V>) buckets[i]);

                    if (element.getValue().equals(value)) {
                        return true;
                    }
                } else if (buckets[i] instanceof LinkedList) {
                    LinkedList<Element<K, V>> linkedList =
                            ((LinkedList<Element<K, V>>) buckets[i]);

                    for (Element<K, V> element : linkedList) {
                        if (element.getValue().equals(value)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(final Object key) {
        int hash = Math.abs(key.hashCode() % bucketSize);

        if (buckets[hash] != null) {
            if (buckets[hash] instanceof Element) {
                Element<K, V> element = (Element<K, V>) buckets[hash];

                if (element.getKey().equals(key)) {
                    return element.getValue();
                }
            } else if (buckets[hash] instanceof LinkedList) {
                LinkedList<Element<K, V>> linkedList
                        = (LinkedList<Element<K, V>>) buckets[hash];

                for (Element<K, V> element : linkedList) {
                    if (element.getKey().equals(key)) {
                        return element.getValue();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public V put(final K key, final V value) {
        int hash = Math.abs(key.hashCode() % bucketSize);

        if (buckets[hash] == null) {
            buckets[hash] = new Element<K, V>(key, value);
        } else if (buckets[hash] instanceof Element) {
            Element<K, V> element = (Element<K, V>) buckets[hash];
            LinkedList<Element<K, V>> linkedList
                    = new LinkedList<Element<K, V>>();
            linkedList.add(element);
            linkedList.add(new Element<K, V>(key, value));
            buckets[hash] = linkedList;
        } else if (buckets[hash] instanceof LinkedList) {
            ((LinkedList<Element<K, V>>)
                    buckets[hash]).add(new Element<K, V>(key, value));
        }
        size++;
        return value;
    }

    @Override
    public V remove(final Object key) {
        int hash = Math.abs(key.hashCode() % bucketSize);

        if (buckets[hash] == null) {
            return null;
        }

        if (buckets[hash] instanceof Element) {
            Element<K, V> element = (Element<K, V>) buckets[hash];

            if (element.getKey().equals(key)) {
                V val = element.getValue();
                buckets[hash] = null;
                size--;
                return val;
            }
        } else if (buckets[hash] instanceof LinkedList) {
            LinkedList<Element<K, V>> linkedList
                    = (LinkedList<Element<K, V>>) buckets[hash];
            for (Element<K, V> element : linkedList) {
                if (element.getKey().equals(key)) {
                    V val = element.getValue();
                    linkedList.remove(element);
                    size--;

                    if (linkedList.size() == 1) {
                        buckets[hash] = linkedList.getFirst();
                    }
                    return val;
                }
            }
        }

        return null;
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        //
    }

    @Override
    public void clear() {
        buckets = new Object[bucketSize];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();

        for (int i = 0; i < bucketSize; i++) {
            if (buckets[i] != null) {
                if (buckets[i] instanceof Element) {
                    set.add(((Element<K, V>) buckets[i]).getKey());
                } else if (buckets[i] instanceof LinkedList) {
                    for (Element<K, V> element
                    : ((LinkedList<Element<K, V>>) buckets[i])) {
                        set.add(element.getKey());
                    }
                }
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> collection = new LinkedList<V>();

        for (int i = 0; i < bucketSize; i++) {
            if (buckets[i] != null) {
                if (buckets[i] instanceof Element) {
                    collection.add(((Element<K, V>) buckets[i]).getValue());
                } else if (buckets[i] instanceof LinkedList) {
                    for (Element<K, V> element
                            : ((LinkedList<Element<K, V>>) buckets[i])) {
                        collection.add(element.getValue());
                    }
                }
            }
        }

        return collection;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();

        for (int i = 0; i < bucketSize; i++) {
            if (buckets[i] != null) {
                if (buckets[i] instanceof Element) {
                    set.add((Element<K, V>) buckets[i]);
                } else if (buckets[i] instanceof LinkedList) {
                    for (Element<K, V> e
                            : ((LinkedList<Element<K, V>>) buckets[i])) {
                        set.add(e);
                    }
                }
            }
        }
        return set;
    }
}

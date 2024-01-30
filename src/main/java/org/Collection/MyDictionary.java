package org.Collection;

import org.exceptions.EmptyCollectionError;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<T, V> implements MyIDictionary<T, V> {
    Map<T, V> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<>();
    }

    public void setContent(Map<T, V> map) {
        dictionary = map;
    }
    public Map<T, V> getMap() {
        return dictionary;
    }
    @Override
    public void add(T key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public V lookup(T key) throws EmptyCollectionError {
        if (dictionary.isEmpty())
            throw new EmptyCollectionError("Dictionary is empty");
        return dictionary.get(key);
    }

    @Override
    public void remove(T key) throws EmptyCollectionError {
        if (dictionary.isEmpty())
            throw new EmptyCollectionError("Dictionary is empty");
        dictionary.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean isDefined(T id) {
//        return dictionary.get(id) != null;
        return dictionary.containsKey(id);
    }

    @Override
    public void update(T key, V value) throws EmptyCollectionError {
        if (dictionary.isEmpty())
            throw new EmptyCollectionError("Dictionary is empty");
        dictionary.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(T el: dictionary.keySet())
            s.append(el.toString()).append("->").append(dictionary.get(el).toString()).append("\n");
        return s.toString();
    }

    @Override
    public MyIDictionary<T, V> deepCopy() {
        MyIDictionary<T, V> newDict = new MyDictionary<>();
        for(T key: dictionary.keySet())
            newDict.add(key, dictionary.get(key));
        return newDict;
    }

}

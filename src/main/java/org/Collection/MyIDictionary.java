package org.Collection;

import org.exceptions.EmptyCollectionError;

import java.util.Map;

public interface MyIDictionary<T,V> {
    public Map<T, V> getMap();
    public void setContent(Map<T, V> map); // setMap
    void add(T key, V value);
    V lookup(T key) throws EmptyCollectionError;
    void remove(T key) throws EmptyCollectionError;
    boolean isEmpty();
    int size();
    void update(T key, V value) throws EmptyCollectionError;

    boolean isDefined(T id);

    MyIDictionary<T, V> deepCopy() ;

}

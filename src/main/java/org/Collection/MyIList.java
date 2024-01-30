package org.Collection;

import org.exceptions.EmptyCollectionError;

import java.util.List;

public interface MyIList<T> {
    void add(T v);
    T get(int index);
    int size();
    void remove(int index) throws EmptyCollectionError;
    void remove(T v) throws EmptyCollectionError;
    void clear();
    boolean isEmpty();

    List<T> getList();


}

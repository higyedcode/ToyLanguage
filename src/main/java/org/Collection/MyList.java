package org.Collection;

import org.exceptions.EmptyCollectionError;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    List<T> list;

    public MyList(List<T> list) {
        this.list = list;
    }
    public MyList ()
    {
        list = new ArrayList<>();
    }
    @Override
    public void add(T v) {
        list.add(v);
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void remove(int index) throws EmptyCollectionError {
        if (list.isEmpty())
            throw new EmptyCollectionError("List is empty");
        list.remove(index);
    }

    @Override
    public void remove(T v) throws EmptyCollectionError {
        if (list.isEmpty())
            throw new EmptyCollectionError("List is empty");
        list.remove(v);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(T el : list)
            s.append(el.toString()).append("\n");
        return s.toString();

    }

}

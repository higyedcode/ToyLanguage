package org.Collection;

import org.exceptions.EmptyCollectionError;

import java.util.Stack;

public interface MyIStack<T> {
    T pop() throws EmptyCollectionError;
    void push(T v);
    boolean isEmpty();

    int size();
    Stack<T> getStack();
}

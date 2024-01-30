package org.Collection;

import org.exceptions.EmptyCollectionError;

import java.util.Stack;


public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public T pop() throws EmptyCollectionError {
        if (stack.empty())
            throw new EmptyCollectionError("Stack is empty");
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        for (T el : stack) {

            s.insert(0,"\n").insert(0,el.toString());

        }
        return s.toString();
    }

    public Stack<T> getStack() {
        return stack;
    }
    public int size()
    {
        return stack.size();
    }
}

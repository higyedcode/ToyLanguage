package org.model.statements;

import org.Collection.MyIStack;
import org.Collection.MyStack;
import org.exceptions.EmptyCollectionError;

public class HeapAddrGenerator {
    private Integer address = 1;
    private static MyIStack<Integer> freeAddresses = new MyStack<>();


    public Integer getFreeAddress() {
        try {
            return freeAddresses.pop();
        } catch (EmptyCollectionError e) {
            return address++;
        }
//
//        if (freeAddresses.isEmpty()) {
//            return address++;
//        } else {
//            return freeAddresses.pop();
//        }
    }
}

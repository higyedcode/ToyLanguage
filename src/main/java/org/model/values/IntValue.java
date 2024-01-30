package org.model.values;

import org.model.types.IntType;
import org.model.types.Type;

public class IntValue implements Value {
    int val;

    public IntValue(int v) {
        val = v;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object obj) {
        return val == ((IntValue)obj).getVal();
    }

    @Override
    public Value deepcopy() {
        return new IntValue(val);
    }
}

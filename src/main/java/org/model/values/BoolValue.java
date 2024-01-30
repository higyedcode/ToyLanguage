package org.model.values;

import org.model.types.BoolType;
import org.model.types.Type;

public class BoolValue implements Value {
    boolean val;

    public BoolValue(boolean v) {
        val = v;
    }

    public boolean getVal() {
        return val;
    }

    @Override
    public String toString() {
        return Boolean.toString(val);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object obj) {
        return val == ((BoolValue)obj).getVal();
    }

    @Override
    public Value deepcopy() {
        return new BoolValue(val);
    }
}

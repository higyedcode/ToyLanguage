package org.model.types;

import org.model.values.IntValue;
import org.model.values.Value;

public class IntType implements Type{
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    @Override
    public Type deepcopy() {
        return new IntType();
    }

}

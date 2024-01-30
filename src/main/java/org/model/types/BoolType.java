package org.model.types;

import org.model.values.*;


public class BoolType implements Type{
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public Type deepcopy() {
        return new BoolType();
    }

}

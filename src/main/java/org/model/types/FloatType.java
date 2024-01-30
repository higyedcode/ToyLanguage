package org.model.types;

import org.model.values.FloatValue;
import org.model.values.Value;

public class FloatType implements Type{
    @Override
    public boolean equals(Object obj) {
        return obj instanceof FloatType;
    }

    @Override
    public String toString() {
        return "float";
    }

    @Override
    public Value defaultValue() {
        return new FloatValue(0);
    }

    @Override
    public Type deepcopy() {
        return new FloatType();
    }

}

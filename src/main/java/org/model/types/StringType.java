package org.model.types;

import org.model.values.StringValue;
import org.model.values.Value;

public class StringType implements Type{
    public boolean equals(Object another) {
        return another instanceof StringType;
    }

    public String toString() {
        return "string";
    }

    @Override
    public Value defaultValue() {
        return new StringValue(" ");
    }

    @Override
    public Type deepcopy() {
        return new StringType();
    }

}

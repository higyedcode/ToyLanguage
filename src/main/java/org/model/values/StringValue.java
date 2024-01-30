package org.model.values;


import org.model.types.StringType;
import org.model.types.Type;

import java.util.Objects;

public class StringValue implements Value{
    private String val;

    public StringValue(String v) { val = v; }

    public String getVal() { return val; }

    @Override
    public String toString() { return val; }

    @Override
    public int hashCode() {
        // you need this function for the HashMap to work properly when doing get and containsKey
        return Objects.hash(val);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringValue)
            return ((StringValue) obj).getVal().equals(val);
        else
            return false;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public StringValue deepcopy() {
        return new StringValue(val);
    }

}

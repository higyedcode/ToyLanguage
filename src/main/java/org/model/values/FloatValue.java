package org.model.values;

import org.model.types.FloatType;
import org.model.types.Type;

public class FloatValue implements Value{
    float val;
    @Override
    public String toString() {
        return Float.toString(val);
    }
    @Override
    public Type getType() {
        return new FloatType();
    }
    public FloatValue(float v) {
        val = v;
    }
    public float getVal() {
        return val;
    }
    @Override
    public boolean equals(Object obj) {
        return val == ((FloatValue)obj).getVal();
    }

    @Override
    public Value deepcopy() {
        return new FloatValue(val);
    }


}

package org.model.values;

import org.model.types.CharType;
import org.model.types.Type;

public class CharValue implements Value{
    char val;

    public CharValue(char v) {
        val = v;
    }

    public char getVal() {
        return val;
    }

    @Override
    public String toString() {
        return Character.toString(val);
    }

    @Override
    public Type getType() {
        return new CharType();
    }
    @Override
    public boolean equals(Object obj) {
        return val == ((CharValue)obj).getVal();
    }

    @Override
    public Value deepcopy() {
        return new CharValue(val);
    }
}

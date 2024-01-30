package org.model.types;

import org.model.values.CharValue;
import org.model.values.Value;

public class CharType  implements Type  {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof CharType;
    }

    @Override
    public String toString() {
        return "char";
    }

    @Override
    public Value defaultValue() {
        return new CharValue(' ');
    }

    @Override
    public Type deepcopy() {
        return new CharType();
    }

}

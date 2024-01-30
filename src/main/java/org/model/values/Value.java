package org.model.values;

import org.model.types.Type;

public interface Value {
    Type getType();
    Value deepcopy();
}

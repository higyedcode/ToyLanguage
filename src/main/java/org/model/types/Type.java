package org.model.types;

import org.model.values.Value;

public interface Type {
    Value defaultValue();
    Type deepcopy();
}

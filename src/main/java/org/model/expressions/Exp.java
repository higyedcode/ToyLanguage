package org.model.expressions;

import org.Collection.MyIDictionary;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.types.Type;
import org.model.values.StringValue;
import org.model.values.Value;

public interface Exp {
    Value eval(MyIDictionary<StringValue, Value> tbl, MyIDictionary<Integer, Value> heap) throws  ZeroDivisionError, ImproperTypeError, EmptyCollectionError;
    Exp deepcopy();

    Type typecheck(MyIDictionary<String,Type> typeEnv) throws EmptyCollectionError, ImproperTypeError;
}

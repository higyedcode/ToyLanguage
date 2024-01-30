package org.model.expressions;

import org.Collection.MyIDictionary;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.types.Type;
import org.model.values.StringValue;
import org.model.values.Value;

public class ValueExp implements Exp{
    Value e;

    public ValueExp(Value val) {
        this.e = val;
    }

    @Override
    public Value eval(MyIDictionary<StringValue, Value> tbl, MyIDictionary<Integer, Value> heap) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public Exp deepcopy() {
        Value eCopy = e.deepcopy();
        return new ValueExp(eCopy);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        return e.getType();
    }
}

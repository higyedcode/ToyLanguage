package org.model.expressions;

import org.Collection.MyIDictionary;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.types.Type;
import org.model.values.StringValue;
import org.model.values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyIDictionary<StringValue, Value> tbl, MyIDictionary<Integer, Value> heap) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError {
        return tbl.lookup(new StringValue(id));
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Exp deepcopy() {
        String idCopy = new String(id);
        return new VarExp(idCopy);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        return typeEnv.lookup(id);
    }
}

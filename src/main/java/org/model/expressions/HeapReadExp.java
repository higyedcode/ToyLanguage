package org.model.expressions;

import org.Collection.MyIDictionary;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.types.RefType;
import org.model.types.Type;
import org.model.values.RefValue;
import org.model.values.StringValue;
import org.model.values.Value;

public class HeapReadExp implements Exp{
    Exp e;
    public HeapReadExp(Exp e) {
        this.e = e;
    }
    @Override
    public Value eval(MyIDictionary<StringValue, Value> tbl, MyIDictionary<Integer, Value> heap) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError {

        if(e.eval(tbl, heap).getType() instanceof RefType)
        {
            RefValue ref = (RefValue) e.eval(tbl, heap);
            if(heap.isDefined(ref.getAddress()))
            {
                return heap.lookup(ref.getAddress());
            }
            else
            {
                throw new EmptyCollectionError("The address is not defined in the heap");
            }
        }
        else
        {
            throw new ImproperTypeError("The expression is not a reference type");
        }
    }

    @Override
    public String toString() {
        return "rH(" + e.toString() + ")";
    }

    @Override
    public Exp deepcopy() {
        return new HeapReadExp(e.deepcopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type typ = e.typecheck(typeEnv);
        if(typ instanceof RefType)
        {
            RefType reft = (RefType) typ;
            return reft.getInner();
        }
        else
        {
            throw new ImproperTypeError("The expression is not a reference type");
        }
    }
}

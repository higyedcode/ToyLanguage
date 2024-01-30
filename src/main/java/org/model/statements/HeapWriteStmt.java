package org.model.statements;

import org.Collection.MyIDictionary;
import org.exceptions.DeclarationError;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.PrgState;
import org.model.expressions.Exp;
import org.model.types.RefType;
import org.model.types.Type;
import org.model.values.RefValue;
import org.model.values.StringValue;
import org.model.values.Value;

import java.io.IOException;

public class HeapWriteStmt implements IStmt{
    StringValue varName; // contains heap address
    Exp e;

    public HeapWriteStmt(StringValue varName, Exp e) {
        this.varName = varName;
        this.e = e;
    }
    @Override
    public String toString() {
        return "wH(" + varName.toString() + ", " + e.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError, DeclarationError, IOException {
        MyIDictionary<StringValue, Value> symTable = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();
        if(symTable.isDefined(varName))
        {
            Value heapAddress = symTable.lookup(varName);
            if(heapAddress.getType() instanceof RefType)
            {
                RefValue ref = (RefValue) heapAddress;

                if(heap.isDefined(ref.getAddress()))
                {
                    Value heapVal = heap.lookup(ref.getAddress());
                    if(heapVal.getType().equals(ref.getLocationType()))
                    {
                        heap.update(ref.getAddress(), e.eval(symTable, heap));
                    }
                    else
                    {
                        throw new ImproperTypeError("The expression type doesn't match the reference type");
                    }
                }
                else
                {
                    throw new EmptyCollectionError("The address is not defined in the heap");
                }

            }
            else
            {
                throw new ImproperTypeError("The variable doesn't store a heap reference!");
            }
        }
        else
        {
            throw new DeclarationError("The variable is not defined");
        }
        return null;

    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        return new HeapWriteStmt(varName.deepcopy(), e.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type var = typeEnv.lookup(varName.getVal());
        Type exp = e.typecheck(typeEnv);
        if(var.equals(new RefType(exp)))
        {
            return typeEnv;
        }
        else
        {
            throw new ImproperTypeError("The variable is not a reference type");
        }
    }
}

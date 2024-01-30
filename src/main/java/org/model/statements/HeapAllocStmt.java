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

public class HeapAllocStmt implements IStmt{
    StringValue varName;
    Exp expression;
    public HeapAllocStmt(StringValue varName, Exp exp) {
        this.varName = varName;
        this.expression = exp;
    }

    @Override
    public String toString() {
        return "new(" + varName.toString() + " , " + expression.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError, DeclarationError, IOException {
        MyIDictionary<Integer, Value> heap = state.getHeap();
        MyIDictionary<StringValue, Value> symTable = state.getSymTable();

        if(symTable.isDefined(varName))
        {
            Value var = symTable.lookup(varName);
            if(var.getType() instanceof RefType)
            {
                Value exp_val = expression.eval(symTable, heap);
                if(exp_val.getType().equals(((RefType) var.getType()).getInner()))
                {
                    Integer heapAddress = state.getNewAddress();
                    heap.add(heapAddress, exp_val);
                    RefValue oldRef = (RefValue) var;
                    symTable.update(varName, new RefValue(heapAddress, oldRef.getLocationType()));
                }
                else
                {
                    throw new ImproperTypeError("The reference type doesn't match the expression type");
                }
            }
            else {
                throw new ImproperTypeError("Variable " + varName + " is not a reference type");
            }
        }
        else {
            throw new DeclarationError("Variable " + varName + " not declared");
        }
        return null;
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        return new HeapAllocStmt(varName.deepcopy(), expression.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type var = typeEnv.lookup(varName.getVal());
        Type exp = expression.typecheck(typeEnv);
        if(var.equals(new RefType(exp)))
        {
            return typeEnv;
        }
        else
        {
            throw new ImproperTypeError("The reference type doesn't match the expression type");
        }
    }
}

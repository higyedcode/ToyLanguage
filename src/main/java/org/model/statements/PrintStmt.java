package org.model.statements;

import org.Collection.MyIDictionary;
import org.Collection.MyIStack;
import org.Collection.MyList;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.PrgState;
import org.model.expressions.Exp;
import org.model.types.Type;
import org.model.values.StringValue;
import org.model.values.Value;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError {
        MyIDictionary<StringValue, Value> symTbl = state.getSymTable();
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<Integer, Value> heap = state.getHeap();
        MyList<Value> out = (MyList<Value>) state.getOut();


        out.add(exp.eval(symTbl, heap));
        return null;


    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";

    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        Exp expCopy = exp.deepcopy();
        return new PrintStmt(expCopy);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}

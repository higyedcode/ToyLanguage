package org.model.statements;

import org.Collection.MyIDictionary;
import org.Collection.MyIStack;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.PrgState;
import org.model.expressions.Exp;
import org.model.types.BoolType;
import org.model.types.Type;
import org.model.values.BoolValue;
import org.model.values.StringValue;
import org.model.values.Value;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString() {
        return "if(" + exp.toString() + ") then(" + thenS.toString() + ") else(" + elseS.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ImproperTypeError, ZeroDivisionError, EmptyCollectionError {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<StringValue, Value> symTbl = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();
        Value val = exp.eval(symTbl, heap);
        if (val.getType().equals(new BoolType())) {
            BoolValue boolVal = (BoolValue) val;
            if (boolVal.getVal()) {
                stk.push(thenS);
            } else {
                stk.push(elseS);
            }
        } else {
            throw new ImproperTypeError("If statement: expression is not a boolean");
        }
        return null;
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        Exp expCopy = exp.deepcopy();
        IStmt thenSCopy = thenS.deepCopy(thenS);
        IStmt elseSCopy = elseS.deepCopy(elseS);


        return new IfStmt(expCopy, thenSCopy, elseSCopy);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.deepCopy());
            elseS.typecheck(typeEnv.deepCopy());
            return typeEnv;
        } else {
            throw new ImproperTypeError("If statement: expression is not a boolean");
        }
    }
}

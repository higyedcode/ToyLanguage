package org.model.statements;

import org.Collection.MyIDictionary;
import org.Collection.MyIStack;
import org.exceptions.DeclarationError;
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

import java.io.IOException;

public class WhileStmt implements IStmt{
    private IStmt stmt;
    private Exp exp;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "( while(" + exp.toString() + ") " + stmt.toString() + " )";
    }

    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError, DeclarationError, IOException {
        MyIDictionary<StringValue, Value> symTable = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();
        MyIStack<IStmt> stk = state.getStk();
        if(exp.eval(symTable, heap).getType() instanceof  BoolType)
        {
            BoolValue val = (BoolValue) exp.eval(symTable, heap);
            if(val.getVal())
            {

                stk.push(this);
                stk.push(stmt);

            }
        }
        else
        {
            throw new ImproperTypeError("While condition is not a boolean!");
        }
        return null;
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv);
            return typeEnv;
        } else {
            throw new ImproperTypeError("While statement: expression is not a boolean");
        }
    }
}

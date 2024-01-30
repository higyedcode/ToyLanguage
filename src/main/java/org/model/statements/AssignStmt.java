package org.model.statements;

import org.Collection.MyIDictionary;
import org.Collection.MyIStack;
import org.exceptions.*;
import org.model.PrgState;
import org.model.expressions.Exp;
import org.model.values.StringValue;
import org.model.values.Value;
import org.model.types.Type;

public class AssignStmt implements IStmt{
    StringValue id;
    Exp exp;

    public AssignStmt(StringValue id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError, DeclarationError {
        MyIDictionary<StringValue, Value> symTbl = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();
        MyIStack<IStmt> stk = state.getStk();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl, heap);
            Type typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new ImproperTypeError("declared type of variable" + id + " and type of the assigned expression do not match");
        }
        else
            throw new DeclarationError("the used variable " + id + " was not declared before");
        return null;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        StringValue idCopy = new StringValue(id.getVal());
        Exp expCopy = exp.deepcopy();
        return new AssignStmt(idCopy, expCopy);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type typevar = typeEnv.lookup(id.getVal());
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new ImproperTypeError("Assignment: right hand side and left hand side have different types ");
    }
}

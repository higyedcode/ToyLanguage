package org.model.statements;

import org.Collection.MyIDictionary;
import org.Collection.MyStack;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.model.PrgState;
import org.model.types.Type;

public class ForkStmt implements IStmt{
    IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        return new PrgState(new MyStack<>(), state.getSymTable().deepCopy(), state.getOut(), state.getFileTable(), state.getHeap(), state.getHeapAddressGenerator(), this.stmt);

    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        stmt.typecheck(typeEnv);
        return typeEnv;
    }
}

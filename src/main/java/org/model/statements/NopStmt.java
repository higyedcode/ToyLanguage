package org.model.statements;

import org.Collection.MyIDictionary;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.model.PrgState;
import org.model.types.Type;

public class NopStmt implements IStmt{
    @Override
    public String toString() {
        return "nop";
    }

    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        return new NopStmt();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        return null;
    }
}

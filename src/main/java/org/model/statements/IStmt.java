package org.model.statements;

import org.Collection.MyIDictionary;
import org.exceptions.*;
import org.model.PrgState;
import org.model.types.Type;

import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError, DeclarationError, IOException;
    IStmt deepCopy(IStmt statement);

    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError;
}

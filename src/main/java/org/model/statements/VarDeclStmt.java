package org.model.statements;

import org.Collection.MyIDictionary;
import org.exceptions.DeclarationError;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.model.PrgState;
import org.model.types.*;
import org.model.values.*;

public class VarDeclStmt implements IStmt {
    StringValue name;
    Type type;

    public VarDeclStmt(StringValue name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws DeclarationError {
        MyIDictionary<StringValue, Value> symTbl = state.getSymTable();
        if (symTbl.isDefined(name)) {
            throw new DeclarationError("Variable " + name + " already declared");
        } else {
            symTbl.add(name, type.defaultValue());
        }

        return null;
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        StringValue valCopy = new StringValue(name.getVal());
        Type typeCopy = type.deepcopy();
        return new VarDeclStmt(valCopy, typeCopy);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        typeEnv.add(name.getVal(), type);
        return typeEnv;
    }

}

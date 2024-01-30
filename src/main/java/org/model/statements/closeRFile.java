package org.model.statements;

import org.Collection.MyIDictionary;
import org.Collection.MyIStack;
import org.exceptions.DeclarationError;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.PrgState;
import org.model.expressions.Exp;
import org.model.types.Type;
import org.model.values.StringValue;
import org.model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt{
    Exp exp;
    public closeRFile(Exp exp)
    {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError, DeclarationError, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<StringValue, Value> symTbl = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        Value val = exp.eval(symTbl, heap);
        if(val instanceof StringValue)
        {
            if(fileTable.isDefined((StringValue)val)) {
                BufferedReader reader = fileTable.lookup((StringValue) val);
                reader.close();
                fileTable.remove((StringValue) val);
            }
            else
                throw new DeclarationError("No filename like this loaded into the program!");

        }
        else
            throw new ImproperTypeError("Filename should be string!");

        return null;
    }

    @Override
    public String toString() {
        return "closing the file [" + exp.toString() + "]";
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        Exp expCopy = exp.deepcopy();

        return new closeRFile(expCopy);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}

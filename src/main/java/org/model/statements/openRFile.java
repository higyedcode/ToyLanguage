package org.model.statements;

import org.exceptions.DeclarationError;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.PrgState;
import org.model.expressions.Exp;
import org.Collection.*;
import org.model.types.Type;
import org.model.values.StringValue;
import org.model.values.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements IStmt{

    public openRFile(Exp exp)
    {
        this.exp = exp;
    }
    Exp exp;
    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError, DeclarationError, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<StringValue, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fTbl = state.getFileTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        if(exp.eval(symTbl, heap) instanceof StringValue)
        {
            StringValue filePath = (StringValue)exp.eval(symTbl, heap);
            if (!fTbl.isDefined(filePath))
            {
                BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
                fTbl.add(filePath, reader);
            }
            else {
                throw new DeclarationError("The fileName is already defined!");
            }

        }
        else
        {
            throw new ImproperTypeError("The name of the file is not a string!");
        }

        return null;


    }

    @Override
    public String toString() {
        return "opening the file [" + exp.toString() + "]";
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        Exp expCopy = exp.deepcopy();
        return new openRFile(expCopy);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}

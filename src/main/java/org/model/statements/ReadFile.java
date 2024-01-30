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
import org.model.types.IntType;
import org.model.types.StringType;
import org.model.values.IntValue;
import org.model.values.StringValue;
import org.model.values.Value;

import java.io.BufferedReader;

import java.io.IOException;

public class ReadFile implements IStmt{
    Exp exp;
    StringValue varname;

    public ReadFile(Exp exp, StringValue name)
    {
        this.exp = exp;
        this.varname = name;
    }

    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError, DeclarationError, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary <StringValue, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fTbl = state.getFileTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        if(symTbl.isDefined(varname))
        {
            Value val = symTbl.lookup(varname);
            if(val instanceof IntValue)
            {
                Value expVal = exp.eval(symTbl, heap);
                if(expVal instanceof StringValue)
                {
                    if(fTbl.isDefined((StringValue)expVal))
                    {
                        BufferedReader reader = fTbl.lookup((StringValue)expVal);
                        String valueReadString = reader.readLine();
                        int valueRead ;
                        if (valueReadString == null)
                            valueRead = 0;
                        else
                            valueRead = Integer.parseInt(valueReadString);

                        symTbl.add(varname, new IntValue(valueRead));



                    }
                    else {
                        throw new DeclarationError("There is no reader loaded with that filename!");
                    }


                }
                else{
                    throw new ImproperTypeError("The fileName is not a string!");
                }

            }
            else
            {
                throw new ImproperTypeError("The type of the variable is not int!");
            }
        }
        else {
            throw new DeclarationError("The variable doesn't exist!");
        }
        return null;
    }

    @Override
    public String toString() {
        return "reading from the file [" + exp.toString() + "] into the variable [" + varname.toString() + "]";
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        Exp expCopy = exp.deepcopy();
        StringValue varnameCopy = new StringValue(varname.getVal());
        return new ReadFile(expCopy, varnameCopy);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type expType = exp.typecheck(typeEnv);
        Type varType = typeEnv.lookup(varname.getVal());
        if(expType.equals(new StringType()))
        {
            if(varType.equals(new IntType()))
            {
                return typeEnv;
            }
            else
            {
                throw new ImproperTypeError("The variable is not an int!");
            }
        }
        else
        {
            throw new ImproperTypeError("The expression is not a string!");
        }
    }
}

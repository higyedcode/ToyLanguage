package org.model.statements;

import org.Collection.MyIDictionary;
import org.Collection.MyIStack;
import org.exceptions.*;
import org.model.PrgState;
import org.model.types.Type;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;
    public CompStmt (IStmt first, IStmt second){
        this.first = first;
        this.second = second;
    }
    public IStmt getFirst(){
        return this.first;
    };
    public IStmt getSecond()
    {
        return this.second;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        IStmt f= this.first;
        IStmt s = this.second;
        while (s instanceof CompStmt)
        {
            builder.append(f.toString()).append("\n");
            f =  ((CompStmt) s).getFirst();
            s = ((CompStmt)s).getSecond();

        }
        builder.append(f.toString()).append("\n");
        builder.append(s.toString()).append("\n");
        return builder.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy(IStmt statement) {
        IStmt st1 = first.deepCopy(first);
        IStmt st2 = second.deepCopy(second);
        return new CompStmt(st1, st2);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        return second.typecheck(first.typecheck(typeEnv));
    }
}

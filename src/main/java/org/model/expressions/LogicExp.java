package org.model.expressions;

import org.Collection.MyIDictionary;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.types.BoolType;
import org.model.types.Type;
import org.model.values.BoolValue;
import org.model.values.StringValue;
import org.model.values.Value;

public class LogicExp implements Exp{
    Exp e1; Exp e2;
    int op; //1-and, 2-or,3-not

    public LogicExp(Exp e1, Exp e2, int op)
    {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;

    }

    @Override
    public Value eval(MyIDictionary<StringValue, Value> tbl, MyIDictionary<Integer, Value> heap) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl, heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == 1) return new BoolValue(n1&&n2);
                if(op == 2) return new BoolValue(n1||n2);
                if(op == 3) return new BoolValue(!n1);
            }
            else
                throw new ImproperTypeError("second operand is not a boolean");
        }
        else
            throw new ImproperTypeError("first operand is not a boolean");
        return null;
    }

    @Override
    public String toString() {
        return switch (op) {
            case 1 -> e1.toString() + " && " + e2.toString();
            case 2 -> e1.toString() + " || " + e2.toString();
            case 3 -> "!" + e1.toString();
            default -> "Invalid operator";
        };

    }

    @Override
    public Exp deepcopy() {
        Exp e1  = this.e1.deepcopy();
        Exp e2 = this.e2.deepcopy();
        int op2 = this.op;

        return new LogicExp(e1, e2, op2);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type type1, type2;
        type1 = e1.typecheck(typeEnv);
        type2 = e2.typecheck(typeEnv);
        if(type1.equals(new BoolType()))
            if(type2.equals(new BoolType()))
                return new BoolType();
            else
                throw new ImproperTypeError("second operand is not a boolean");
        else
            throw new ImproperTypeError("first operand is not a boolean");
    }
}

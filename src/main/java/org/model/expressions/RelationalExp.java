package org.model.expressions;

import org.Collection.MyIDictionary;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.types.BoolType;
import org.model.types.IntType;
import org.model.types.Type;
import org.model.values.BoolValue;
import org.model.values.IntValue;
import org.model.values.StringValue;
import org.model.values.Value;

public class RelationalExp implements Exp{

    Exp e1; Exp e2;
    int op; //1 <, 2 <=, 3 ==, 4 !=, 5->, 6 >=

    public RelationalExp(Exp e1, Exp e2, int op)
    {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDictionary<StringValue, Value> tbl, MyIDictionary<Integer, Value> heap) throws ZeroDivisionError, ImproperTypeError, EmptyCollectionError {
        if(e1.eval(tbl, heap) instanceof IntValue)
        {
            int val1 = ((IntValue) e1.eval(tbl, heap)).getVal();
            if(e2.eval(tbl, heap) instanceof IntValue)
            {
                int val2 = ((IntValue) e2.eval(tbl, heap)).getVal();
                return switch (op) {
                    case 1 -> new BoolValue(val1 < val2);
                    case 2 -> new BoolValue(val1 <= val2);
                    case 3 -> new BoolValue(val1 == val2);
                    case 4 -> new BoolValue(val1 != val2);
                    case 5 -> new BoolValue(val1 > val2);
                    case 6 -> new BoolValue(val1 >= val2);
                    default -> throw new ImproperTypeError("Invalid operator");
                };

            }
            else {
                throw new ImproperTypeError("second operand is not an integer");
            }
        }
        else {
            throw new ImproperTypeError("first operand is not an integer");
        }
    }

    @Override
    public String toString(){
        return switch (op)
        {
            case 1 -> e1.toString() + " < " + e2.toString();
            case 2 -> e1.toString() + " <= " + e2.toString();
            case 3 -> e1.toString() + " == " + e2.toString();
            case 4 -> e1.toString() + " != " + e2.toString();
            case 5 -> e1.toString() + " > " + e2.toString();
            case 6 -> e1.toString() + " >= " + e2.toString();
            default -> "Error in the operator!";
        };

    }

    @Override
    public Exp deepcopy() {
        Exp e1  = this.e1.deepcopy();
        Exp e2 = this.e2.deepcopy();
        int op2 = this.op;

        return new RelationalExp(e1, e2, op2);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws EmptyCollectionError, ImproperTypeError {
        Type type1, type2;
        type1 = e1.typecheck(typeEnv);
        type2 = e2.typecheck(typeEnv);
        if(type1.equals(new IntType()))
        {
            if(type2.equals(new IntType()))
            {
                return new BoolType();
            }
            else
            {
                throw new ImproperTypeError("second operand is not an integer");
            }
        }
        else
        {
            throw new ImproperTypeError("first operand is not an integer");
        }
    }
}

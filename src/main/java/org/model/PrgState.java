package org.model;

import org.Collection.*;
import org.exceptions.DeclarationError;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.statements.IStmt;
import org.model.statements.*;
import org.model.values.*;


import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<StringValue, Value> symTable;
    MyIDictionary<Integer, Value> heap;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    IStmt originalProgram;

    private static int idCounter = 0;
    private int id;

    HeapAddrGenerator heapAddrGenerator = new HeapAddrGenerator();

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<StringValue, Value> symtbl, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> ft, MyIDictionary<Integer, Value> heap, HeapAddrGenerator hgen,IStmt prg)
    {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        this.heap = heap;
        this.id = getNextId();
        this.heapAddrGenerator = hgen;
        if (prg != null)
        {
            originalProgram = prg.deepCopy(prg);
            exeStack.push(prg);
        }
    }

    public PrgState(IStmt prg)
    {
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        heap = new MyDictionary<>();
        out = new MyList<>();
        fileTable = new MyDictionary<>();
        this.id = getNextId();
        if (prg != null)
        {
            originalProgram = prg.deepCopy(prg);
            exeStack.push(prg);
        }
    }
    // the "syncronized" keyword is used to prevent multiple threads from accessing the same method at the same time.
    // and it is static because we want to increment the idCounter for all instances of PrgState
    private static synchronized int getNextId() {
        return idCounter++;
    }

    public static void resetIdCounter() {
        idCounter = 0;
    }


    public int getId()
    {
        return id;
    }
    public Integer getNewAddress() {
        return heapAddrGenerator.getFreeAddress();
    }
    public HeapAddrGenerator getHeapAddressGenerator() {
        return heapAddrGenerator;
    }
    public MyIDictionary<Integer, Value> getHeap() {
        return heap;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable()
    {
        return fileTable;
    }
    public void setFileTable(MyIDictionary<StringValue, BufferedReader> ft)
    {
        fileTable = ft;
    }
    public MyIStack<IStmt> getStk() {
        return exeStack;
    }

    public MyIDictionary<StringValue, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setStk(MyIStack<IStmt> stk) {
        exeStack = stk;
    }

    public void setSymTable(MyIDictionary<StringValue, Value> symtbl) {
        symTable = symtbl;
    }

    public void setOut(MyIList<Value> ot) {
        out = ot;
    }

    public void setOriginalProgram(IStmt prg) {
        originalProgram = prg;
    }

    public Boolean isNotCompleted()
    {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws EmptyCollectionError, ZeroDivisionError, ImproperTypeError, DeclarationError, IOException {

        if(this.exeStack.isEmpty())
            throw new EmptyCollectionError("Program State Stack is empty!");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString() {
        return "----------------------------- Thread ID: "+ Integer.toString(id) +" -----------------------------------\n"
                + "ExeStack:\n" + exeStack.toString() + "\nSymTable:\n" + symTable.toString() + "\nHeap:\n" + heap.toString() + "\nOut:\n" + out.toString() +"\nFileTable:\n" + fileTable.toString() +  "\n----------------------------------------------------------------\n";
    }

}

package org.repository;

import org.Collection.MyList;
import org.model.PrgState;

import java.io.*;

public class Repository implements IRepository{
    MyList<PrgState> prgList;
    String logFilePath;
    public Repository(PrgState state,String logFilePath)
    {
        prgList = new MyList<PrgState>();
        prgList.add(state);
        this.logFilePath = logFilePath;
    }


//    @Override
//    public PrgState getCrtPrg() {
//        return prgList.get(0);
//    }

    @Override
    public void addPrgState(PrgState prg) {
        prgList.add(prg);
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws IOException {

        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.print(prg.toString());
        logFile.close();
    }

    @Override
    public void setPrgList(MyList<PrgState> list) {
        this.prgList = list;
    }

    @Override
    public MyList<PrgState> getPrgList() {
        return prgList;
    }
}

package org.repository;

import org.Collection.MyList;
import org.model.PrgState;

import java.io.IOException;

public interface IRepository {
    void addPrgState(PrgState prg);
//    PrgState getCrtPrg();
// no longer using get current program state because we are implementing threads.


    void logPrgStateExec(PrgState prg) throws IOException;
    void setPrgList(MyList<PrgState> list);
    MyList<PrgState> getPrgList();
}

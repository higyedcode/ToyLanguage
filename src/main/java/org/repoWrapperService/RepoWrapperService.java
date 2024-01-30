package org.repoWrapperService;

import org.Collection.MyList;
import org.exceptions.DeclarationError;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.PrgState;
import org.repository.IRepository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;


public class RepoWrapperService {
    IRepository repo;


    public RepoWrapperService(IRepository repo) {
        this.repo = repo;
    }
    public MyList<PrgState> getPrgList() {
        return repo.getPrgList();
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList, ExecutorService executor) throws EmptyCollectionError, ZeroDivisionError, ImproperTypeError, DeclarationError, IOException, InterruptedException {



        // RUN concurrently one step for each of the existing PrgStates
        //    - prepare the list of callables
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        //    - start the execution of the callables
        // it returns the list of new created PrgStates (namely threads)
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());
        // add the new created threads to the list of existing threads
        prgList.addAll(newPrgList);

        for (PrgState prg : prgList) {
            // before the execution, print the PrgState List into the log file
            repo.logPrgStateExec(prg);
        }

        MyList<PrgState> updatedPrgList = new MyList<>(prgList);
        repo.setPrgList(updatedPrgList);
    }

    public void setPrgList(MyList<PrgState> prgList) {
        repo.setPrgList(prgList);
    }

}



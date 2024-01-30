package org.controller;

import org.Collection.*;
import org.exceptions.*;
import org.model.PrgState;
import org.repository.IRepository;
import org.model.values.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.List;

public class Controller {
    IRepository repo;
    boolean debugFlag;

    ExecutorService executor;

    public  Controller(IRepository repo, boolean debugFlag )
    {
        this.repo = repo;
        this.debugFlag = debugFlag;
    }

    public void addPrgState(PrgState state)
    {
        repo.addPrgState(state);

    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapTableAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()) || heapTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues)
    {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws EmptyCollectionError, ZeroDivisionError, ImproperTypeError, DeclarationError, IOException, InterruptedException {

//        for (PrgState prg : prgList) {
//            // before the execution, print the PrgState List into the log file
//            repo.logPrgStateExec(prg);
//        }
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
        // after the execution, print the PrgState List into the log file
        for (PrgState prg : prgList) {
            repo.logPrgStateExec(prg);
        }
        // save the current programs in the repository
        MyList<PrgState> updatedPrgList = new MyList<>(prgList);
        repo.setPrgList(updatedPrgList);
    }



    public void allStep() throws EmptyCollectionError, ZeroDivisionError, ImproperTypeError, DeclarationError, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        // remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList().getList());
        while(prgList.size() > 0){
            //HERE you can call conservativeGarbageCollector
            // call it once, with the symtableargs being the sum of all symtableargs from all PrgStates
            // with the heap being the first one, cause they are shared.

//            prgList.forEach(prg -> {
//                prg.getHeap().setContent(
//                        safeGarbageCollector(
//                                getAddrFromSymTable(prg.getSymTable().getMap().values()),
//                                getAddrFromSymTable(prg.getHeap().getMap().values()),
//                                prg.getHeap().getMap()
//                        ));
//
//            });
            Collection<Value> allSymTableValues = prgList.stream()
                    .map(p -> p.getSymTable().getMap().values())
                    .reduce(new ArrayList<>(), (l1, l2) -> {
                        l1.addAll(l2);
                        return l1;
                    });

            prgList.get(0).getHeap().setContent(
                    safeGarbageCollector(
                            getAddrFromSymTable(allSymTableValues),
                            getAddrFromSymTable(prgList.get(0).getHeap().getMap().values()),
                            prgList.get(0).getHeap().getMap()
                    ));

            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList = removeCompletedPrg(repo.getPrgList().getList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repo.setPrgList(new MyList<>(prgList));

    }

}

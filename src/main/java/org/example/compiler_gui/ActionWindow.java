package org.example.compiler_gui;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.Collection.MyIList;
import org.Collection.MyIStack;
import org.Collection.MyList;
import org.model.PrgState;
import org.model.statements.CompStmt;
import org.model.statements.IStmt;
import org.model.values.IntValue;
import org.model.values.RefValue;
import org.model.values.StringValue;
import org.model.values.Value;
import org.repoWrapperService.RepoWrapperService;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;


public class ActionWindow implements Initializable {

    public ListView exeStack;
    public ListView<Integer> PrgStateIDs;
    public TableView<TableEntry> heapTable;

    
    public TableView<TableEntry> symTable;

    public ListView<String> fileTable;
    public Button RunOneStepBtn;
    public ListView<String> outTable;
    public TableColumn addressColumn;
    public TableColumn ValueColumn;
    public TableColumn symVarName;
    public TableColumn symValue;
    @FXML
    private TextField nrPrgStatesTxt;

    private RepoWrapperService repoWrapperService;
    int currentIndex;

    ExecutorService executor;

    public ActionWindow() {
       System.out.println("ActionWindow constructor called");
    }


    public void displayCurrentProgState(PrgState selectedPrgState, Boolean OneStep)
    {
        this.nrPrgStatesTxt.setText(Integer.toString(repoWrapperService.getPrgList().size()));

        // display the execution stack
        this.exeStack.getItems().clear();
        MyIStack<IStmt> exeStack = selectedPrgState.getStk();
        ObservableList<String> exeStackData = this.exeStack.getItems();
        exeStackData.add(exeStack.toString());
        this.exeStack.setItems(exeStackData);



        // add an item to the heap table
        this.heapTable.getItems().clear();
        Map<Integer, Value> heap = selectedPrgState.getHeap().getMap();
        //heap.put(1, new IntValue(1));

        ObservableList<TableEntry> heapTableData = this.heapTable.getItems();
        for(Integer key : heap.keySet())
        {
            heapTableData.add(new TableEntry(key.toString(), heap.get(key).toString()));
        }
        this.heapTable.setItems(heapTableData);

        // add an item to the symbol table
        this.symTable.getItems().clear();
        Map<StringValue, Value> symTbl = selectedPrgState.getSymTable().getMap();
//        symTbl.put(new StringValue("1"), new IntValue(1));
//        symTbl.put(new StringValue("2"), new IntValue(2));
        ObservableList<TableEntry> symTableData = this.symTable.getItems();
        for(StringValue key : symTbl.keySet())
        {
            symTableData.add(new TableEntry(key.toString(), symTbl.get(key).toString()));
        }
        this.symTable.setItems(symTableData);

        // add an item to the file table
        this.fileTable.getItems().clear();

        Map<StringValue, BufferedReader> fileTable = selectedPrgState.getFileTable().getMap();
//        fileTable.put(new StringValue("1"), null);
        ObservableList<String> fileTableData = this.fileTable.getItems();
        fileTableData.add(fileTable.toString());
        this.fileTable.setItems(fileTableData);

        // add an item to the out table
        this.outTable.getItems().clear();
        MyIList<Value> out = selectedPrgState.getOut();
//        out.add(new IntValue(1));
        ObservableList<String> outTableData = this.outTable.getItems();
        outTableData.add(out.toString());
        this.outTable.setItems(outTableData);

        if(OneStep) {
            // deselect whatever selection on PrgStateIDs
            //this.PrgStateIDs.getSelectionModel().clearSelection();
            // display the program state id
            this.PrgStateIDs.getItems().clear();
            ObservableList<Integer> PrgStateIDsData = this.PrgStateIDs.getItems();
            for (PrgState prg : this.repoWrapperService.getPrgList().getList()) {
                //if the prg id is not already in the list, add it
                if(!PrgStateIDsData.contains(prg.getId()))
                    PrgStateIDsData.add(prg.getId());
            }
            this.PrgStateIDs.setItems(PrgStateIDsData);
            //this.PrgStateIDs.getSelectionModel().selectFirst();

        }

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        executor = Executors.newFixedThreadPool(2);

        addressColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("name"));
        ValueColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("value"));
        symVarName.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("name"));
        symValue.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("value"));


        this.PrgStateIDs.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            // get the index of the selected item
            if(this.PrgStateIDs.getSelectionModel().getSelectedIndex() != -1)
            {int index = this.PrgStateIDs.getSelectionModel().getSelectedIndex();
                this.currentIndex = this.PrgStateIDs.getItems().get(index);
            PrgState selectedPrgState = this.repoWrapperService.getPrgList().getList().get(index);
            displayCurrentProgState(selectedPrgState, false);
            }
        });

        this.RunOneStepBtn.setOnAction(actionEvent -> {
            try {


                List<PrgState> prgList = this.repoWrapperService.getPrgList().getList();
                prgList = this.repoWrapperService.removeCompletedPrg(prgList);
                this.repoWrapperService.setPrgList(new MyList<>(prgList));



                if(prgList.size() > 0)
                {
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

                    this.PrgStateIDs.getSelectionModel().clearSelection();
                    this.repoWrapperService.oneStepForAllPrg(this.repoWrapperService.getPrgList().getList(), executor);

                    //remove the completed programs

                    if(prgList.size() == 1 && (prgList.get(0).getStk().size() == 0))
                    {
                        if(this.currentIndex != -1)
                        {
                            int index = this.PrgStateIDs.getItems().indexOf(this.currentIndex);
                            displayCurrentProgState(prgList.get(index), true);
                            // get the index of the item that has the integer value written on it  of currentIndex
                            this.PrgStateIDs.getSelectionModel().select(index);

                        }
                        else {
                            displayCurrentProgState(prgList.get(0), true);
                            this.PrgStateIDs.getSelectionModel().selectFirst();
                        }
                    }
                    prgList = this.repoWrapperService.removeCompletedPrg(prgList);
                    this.repoWrapperService.setPrgList(new MyList<>(prgList));

                    if (prgList.size() > 0)
                    {
                        if (this.currentIndex != -1) {
                            int index = this.PrgStateIDs.getItems().indexOf(this.currentIndex);
                            displayCurrentProgState(prgList.get(index), true);
                            // get the index of the item that has the integer value written on it  of currentIndex
                            this.PrgStateIDs.getSelectionModel().select(index);

                        } else {
                            displayCurrentProgState(prgList.get(0), true);
                            this.PrgStateIDs.getSelectionModel().selectFirst();
                        }
                    }
                    else{
                        executor.shutdown();
                    }

                }



            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }


        });
    }

    @FXML
    public void update(RepoWrapperService repoWrapperService, int id)
    {
        this.repoWrapperService = repoWrapperService;
        List<PrgState> prgList= repoWrapperService.getPrgList().getList();
        this.nrPrgStatesTxt.setText(Integer.toString(prgList.size()));

        for (PrgState prg : prgList) {
            this.PrgStateIDs.getItems().add(prg.getId());
        }
        this.exeStack.getItems().add(prgList.get(0).getStk().toString());
    }


}

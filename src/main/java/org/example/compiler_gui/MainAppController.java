package org.example.compiler_gui;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.Collection.MyDictionary;
import org.model.PrgState;
import org.model.expressions.*;
import org.model.statements.*;
import org.model.types.*;
import org.model.values.BoolValue;
import org.model.values.IntValue;
import org.model.values.StringValue;
import org.repoWrapperService.RepoWrapperService;
import org.repository.IRepository;
import org.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainAppController {
    public ListView<String> availablePrgStates;

    // when one of the items of the ListView is created print something
    @FXML
    public void initialize() {
//        HashMap<Integer, RepoWrapperService> menu = InterpreterLogic();
//        for(RepoWrapperService service: menu.values())
//        {
//            availablePrgStates.getItems().add(service.getPrgList().getList().get(0).getStk().toString());
//        }
        ArrayList<IStmt> programs = loadPrograms();
        for(IStmt stmt: programs)
        {
            availablePrgStates.getItems().add(stmt.toString());
        }
        // create action on clicking a selected item
        availablePrgStates.onMouseClickedProperty().set(mouseEvent -> {
            int index = availablePrgStates.getSelectionModel().getSelectedIndex();
            try {
                IStmt currentProgram = programs.get(index);
                MyDictionary<String, Type> typeEnv = new MyDictionary<String, Type>();
                try {
                    currentProgram.typecheck(typeEnv);
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    return;
                }
                PrgState.resetIdCounter();

                PrgState prg = new PrgState(currentProgram);
                IRepository repo = new Repository(prg,"src\\main\\java\\org\\logs\\log" + (index + 1) + ".txt");
                RepoWrapperService service = new RepoWrapperService(repo);

                actionWrapper(service, index);
                availablePrgStates.getSelectionModel().clearSelection();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        );


    }

    public void actionWrapper(RepoWrapperService repoWrapperService, int id) throws IOException {

        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("action-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        ActionWindow actionWindow = fxmlLoader.getController();
        actionWindow.update(repoWrapperService, id);
        primaryStage.setTitle("Simulation Window");
        primaryStage.setScene(scene);

        primaryStage.show();




    }

    public HashMap<Integer, RepoWrapperService> InterpreterLogic()
    {
        IStmt ex1 = new CompStmt(new VarDeclStmt(new StringValue("v"), new BoolType()),
                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new BoolValue(true))), new CompStmt(new VarDeclStmt(new StringValue("x"),new IntType()),
                        new NopStmt()
                )));
        MyDictionary<String, Type> typeEnv = new MyDictionary<String, Type>();
        try {
            ex1.typecheck(typeEnv);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg1 = new PrgState(ex1);
        IRepository repo1 = new Repository(prg1,"src\\logs\\log1.txt");
        RepoWrapperService service1 = new RepoWrapperService(repo1);

        IStmt ex2 = new CompStmt(new VarDeclStmt(new StringValue("a"), new IntType()),
                new CompStmt(new VarDeclStmt(new StringValue("b"), new IntType()),
                        new CompStmt(new AssignStmt(new StringValue("a"), new ArithExp(new ValueExp(new IntValue(2)), new
                                ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)),
                                new CompStmt(new AssignStmt(new StringValue("b"), new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        MyDictionary<String, Type> typeEnv2 = new MyDictionary<String, Type>();
        try {
            ex2.typecheck(typeEnv2);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg2 = new PrgState(ex2);
        IRepository repo2 = new Repository(prg2,"src\\logs\\log2.txt");
        RepoWrapperService service2 = new RepoWrapperService(repo2);

        IStmt ex3 = new CompStmt(new VarDeclStmt(new StringValue("a"), new BoolType()),
                new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
                        new CompStmt(new AssignStmt(new StringValue("a"), new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt(new StringValue("v"), new ValueExp(new
                                        IntValue(2))), new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        MyDictionary<String, Type> typeEnv3 = new MyDictionary<String, Type>();
        try {
            ex3.typecheck(typeEnv3);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg3 = new PrgState(ex3);
        IRepository repo3 = new Repository(prg3,"src\\logs\\log3.txt");
        RepoWrapperService service3 = new RepoWrapperService(repo3);


        IStmt ex4 = new CompStmt(new VarDeclStmt(new StringValue("varf"), new StringType()),
                new CompStmt(new AssignStmt(new StringValue("varf"), new ValueExp(new StringValue("src\\main\\java\\org\\test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt(new StringValue("varc"), new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), new StringValue("varc")),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), new StringValue("varc")),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new closeRFile(new VarExp("varf"))))))))));

        MyDictionary<String, Type> typeEnv4 = new MyDictionary<String, Type>();
        try {
            ex4.typecheck(typeEnv4);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg4 = new PrgState(ex4);
        IRepository repo4 = new Repository(prg4,"src\\logs\\log4.txt");
        RepoWrapperService service4 = new RepoWrapperService(repo4);



        IStmt ex5 = new CompStmt(new VarDeclStmt(new StringValue("x"), new IntType()),
                new CompStmt(new AssignStmt(new StringValue("x"), new ValueExp(new IntValue(5))),
                        new CompStmt(new VarDeclStmt(new StringValue("y"), new IntType()),
                                new CompStmt(new AssignStmt(new StringValue("y"), new ValueExp(new IntValue(7))),
                                        new PrintStmt(new RelationalExp(new ValueExp(new IntValue(5)), new ValueExp(new IntValue(7)),3))))));

        MyDictionary<String, Type> typeEnv5 = new MyDictionary<String, Type>();
        try {
            ex5.typecheck(typeEnv5);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg5 = new PrgState(ex5);
        IRepository repo5 = new Repository(prg5,"src\\logs\\log5.txt");
        RepoWrapperService service5 = new RepoWrapperService(repo5);


        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStmt ex6 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        MyDictionary<String, Type> typeEnv6 = new MyDictionary<String, Type>();
        try {
            ex6.typecheck(typeEnv6);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg6 = new PrgState(ex6);
        IRepository repo6 = new Repository(prg6,"src\\logs\\log6.txt");
        RepoWrapperService service6 = new RepoWrapperService(repo6);

        // Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)))
        IStmt ex7 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(7))),
                                        new CompStmt(new HeapWriteStmt(new StringValue("v"), new ValueExp(new IntValue(30))),
                                                new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));
        MyDictionary<String, Type> typeEnv7 = new MyDictionary<String, Type>();
        try {
            ex7.typecheck(typeEnv7);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg7 = new PrgState(ex7);
        IRepository repo7 = new Repository(prg7,"src\\logs\\log7.txt");
        RepoWrapperService service7 = new RepoWrapperService(repo7);

        // int v; v = 4; (while (v > 0) (print(v);v = v - 1));print(v)
        IStmt ex8 = new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt(new StringValue("v"), new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))),

                                new PrintStmt(new VarExp("v")))));
        MyDictionary<String, Type> typeEnv8 = new MyDictionary<String, Type>();
        try {
            ex8.typecheck(typeEnv8);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg8 = new PrgState(ex8);
        IRepository repo8 = new Repository(prg8,"src\\logs\\log8.txt");
        RepoWrapperService service8 = new RepoWrapperService(repo8);

        // int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))
        IStmt ex9 = new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new IntType())),
                        new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt(new StringValue("a"), new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a")))))))));
        MyDictionary<String, Type> typeEnv9 = new MyDictionary<String, Type>();
        try {
            ex9.typecheck(typeEnv9);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;

        }

        // Ref int v; Ref( Ref int) a ; new(v,10); new(a,v);
        // fork(new(v,30); new(v,50) ;print(rH(v)); print(rH(rH(a))));
        //print(rH(v));print(rH(rH(a))));

        IStmt ex10 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
                        new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("v")),
                                        new CompStmt(new ForkStmt(new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(30))),
                                                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(50))),
                                                        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))),
                                                                new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))),
                                                new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))),
                                                        new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a"))))))))));
        MyDictionary<String, Type> typeEnv10 = new MyDictionary<String, Type>();
        try {
            ex10.typecheck(typeEnv10);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg9 = new PrgState(ex10);
        IRepository repo9 = new Repository(prg9,"src\\logs\\log9.txt");
        RepoWrapperService service9 = new RepoWrapperService(repo9);

        // int counter; Ref int a; while(counter < 10) { fork(fork ({new (a, counter); print(rH(a))})); counter = counter + 1; }
        IStmt ex11 = new CompStmt(new VarDeclStmt(new StringValue("counter"), new IntType()),
                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new IntType())),
                        new CompStmt(new AssignStmt(new StringValue("counter"), new ValueExp(new IntValue(0))),
                                new WhileStmt(new RelationalExp(new VarExp("counter"), new ValueExp(new IntValue(10)), 1),
                                        new CompStmt(new ForkStmt(new ForkStmt(new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("counter")),
                                                new PrintStmt(new HeapReadExp(new VarExp("a")))))),
                                                new AssignStmt(new StringValue("counter"), new ArithExp(new VarExp("counter"), new ValueExp(new IntValue(1)), 1)))))));
        MyDictionary<String, Type> typeEnv11 = new MyDictionary<String, Type>();
        try {
            ex11.typecheck(typeEnv11);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        PrgState prg10 = new PrgState(ex11);
        IRepository repo10 = new Repository(prg10,"src\\logs\\log10.txt");
        RepoWrapperService service10 = new RepoWrapperService(repo10);


        HashMap<Integer, RepoWrapperService> menu = new HashMap<Integer, RepoWrapperService>();
        menu.put(0,service1);
        menu.put(1,service2);
        menu.put(2,service3);
        menu.put(3,service4);
        menu.put(4,service5);
        menu.put(5,service6);
        menu.put(6,service7);
        menu.put(7,service8);
        menu.put(8,service9);
        menu.put(9,service10);


        return menu;

    }

    public ArrayList<IStmt> loadPrograms()
    {
        IStmt ex1 = new CompStmt(new VarDeclStmt(new StringValue("v"), new BoolType()),
                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new BoolValue(true))), new CompStmt(new VarDeclStmt(new StringValue("x"),new IntType()),
                        new NopStmt()
                )));
        IStmt ex2 = new CompStmt(new VarDeclStmt(new StringValue("a"), new IntType()),
                new CompStmt(new VarDeclStmt(new StringValue("b"), new IntType()),
                        new CompStmt(new AssignStmt(new StringValue("a"), new ArithExp(new ValueExp(new IntValue(2)), new
                                ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)),
                                new CompStmt(new AssignStmt(new StringValue("b"), new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));

        IStmt ex3 = new CompStmt(new VarDeclStmt(new StringValue("a"), new BoolType()),
                new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
                        new CompStmt(new AssignStmt(new StringValue("a"), new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt(new StringValue("v"), new ValueExp(new
                                        IntValue(2))), new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        IStmt ex4 = new CompStmt(new VarDeclStmt(new StringValue("varf"), new StringType()),
                new CompStmt(new AssignStmt(new StringValue("varf"), new ValueExp(new StringValue("src\\main\\java\\org\\test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt(new StringValue("varc"), new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), new StringValue("varc")),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), new StringValue("varc")),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new closeRFile(new VarExp("varf"))))))))));

        IStmt ex5 = new CompStmt(new VarDeclStmt(new StringValue("x"), new IntType()),
                new CompStmt(new AssignStmt(new StringValue("x"), new ValueExp(new IntValue(5))),
                        new CompStmt(new VarDeclStmt(new StringValue("y"), new IntType()),
                                new CompStmt(new AssignStmt(new StringValue("y"), new ValueExp(new IntValue(7))),
                                        new PrintStmt(new RelationalExp(new ValueExp(new IntValue(5)), new ValueExp(new IntValue(7)),3))))));

        IStmt ex6 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        IStmt ex7 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(7))),
                                        new CompStmt(new HeapWriteStmt(new StringValue("v"), new ValueExp(new IntValue(30))),
                                                new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));
        IStmt ex8 = new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt(new StringValue("v"), new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))),

                                new PrintStmt(new VarExp("v")))));

        IStmt ex9 = new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new IntType())),
                        new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt(new StringValue("a"), new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a")))))))));

        IStmt ex10 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
                        new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("v")),
                                        new CompStmt(new ForkStmt(new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(30))),
                                                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(50))),
                                                        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))),
                                                                new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))),
                                                new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))),
                                                        new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a"))))))))));

        IStmt ex11 = new CompStmt(new VarDeclStmt(new StringValue("counter"), new IntType()),
                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new IntType())),
                        new CompStmt(new AssignStmt(new StringValue("counter"), new ValueExp(new IntValue(0))),
                                new WhileStmt(new RelationalExp(new VarExp("counter"), new ValueExp(new IntValue(10)), 1),
                                        new CompStmt(new ForkStmt(new ForkStmt(new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("counter")),
                                                new PrintStmt(new HeapReadExp(new VarExp("a")))))),
                                                new AssignStmt(new StringValue("counter"), new ArithExp(new VarExp("counter"), new ValueExp(new IntValue(1)), 1)))))));


        ArrayList<IStmt> programs = new ArrayList<IStmt>();
        programs.add(ex1);
        programs.add(ex2);
        programs.add(ex3);
        programs.add(ex4);
        programs.add(ex5);
        programs.add(ex6);
        programs.add(ex7);
        programs.add(ex8);
        programs.add(ex9);
        programs.add(ex10);
        programs.add(ex11);

        return programs;

    }


}
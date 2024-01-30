//package organisation;
//
//import organisation.Collection.MyDictionary;
//import organisation.controller.Controller;
//import organisation.model.PrgState;
//import organisation.model.expressions.*;
//import organisation.model.statements.*;
//import organisation.model.types.BoolType;
//import organisation.model.types.*;
//import organisation.model.values.BoolValue;
//import organisation.model.values.IntValue;
//import organisation.model.values.StringValue;
//import organisation.view.Commands.ExitCommand;
//import organisation.view.Commands.RunExample;
//import organisation.view.TextMenu;
//import organisation.repository.*;
//
//public class Interpreter {
//    public static void main(String[] args)
//    {
//
////        IStmt ex1 = new CompStmt(new VarDeclStmt(new StringValue("v"), new BoolType()),
////                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new BoolValue(true))), new CompStmt(new VarDeclStmt(new StringValue("x"),new IntType()),
////                        new NopStmt()
////                )));
////        MyDictionary<String, Type> typeEnv = new MyDictionary<String, Type>();
////        try {
////            ex1.typecheck(typeEnv);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg1 = new PrgState(ex1);
////        IRepository repo1 = new Repository(prg1,"src\\logs\\log1.txt");
////        Controller ctr1 = new Controller(repo1, true);
////
////        IStmt ex2 = new CompStmt(new VarDeclStmt(new StringValue("a"), new IntType()),
////                new CompStmt(new VarDeclStmt(new StringValue("b"), new IntType()),
////                        new CompStmt(new AssignStmt(new StringValue("a"), new ArithExp(new ValueExp(new IntValue(2)), new
////                                ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)),
////                                new CompStmt(new AssignStmt(new StringValue("b"), new ArithExp(new VarExp("a"), new ValueExp(new
////                                        IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
////        MyDictionary<String, Type> typeEnv2 = new MyDictionary<String, Type>();
////        try {
////            ex2.typecheck(typeEnv2);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg2 = new PrgState(ex2);
////        IRepository repo2 = new Repository(prg2,"src\\logs\\log2.txt");
////        Controller ctr2 = new Controller(repo2, true);
////
////        IStmt ex3 = new CompStmt(new VarDeclStmt(new StringValue("a"), new BoolType()),
////                new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
////                        new CompStmt(new AssignStmt(new StringValue("a"), new ValueExp(new BoolValue(true))),
////                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt(new StringValue("v"), new ValueExp(new
////                                        IntValue(2))), new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(3)))), new PrintStmt(new
////                                        VarExp("v"))))));
////        MyDictionary<String, Type> typeEnv3 = new MyDictionary<String, Type>();
////        try {
////            ex3.typecheck(typeEnv3);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg3 = new PrgState(ex3);
////        IRepository repo3 = new Repository(prg3,"src\\logs\\log3.txt");
////        Controller ctr3 = new Controller(repo3, true);
////
////
////        IStmt ex4 = new CompStmt(new VarDeclStmt(new StringValue("varf"), new StringType()),
////                new CompStmt(new AssignStmt(new StringValue("varf"), new ValueExp(new StringValue("src\\test.in"))),
////                        new CompStmt(new openRFile(new VarExp("varf")),
////                                new CompStmt(new VarDeclStmt(new StringValue("varc"), new IntType()),
////                                        new CompStmt(new ReadFile(new VarExp("varf"), new StringValue("varc")),
////                                                new CompStmt(new PrintStmt(new VarExp("varc")),
////                                                        new CompStmt(new ReadFile(new VarExp("varf"), new StringValue("varc")),
////                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
////                                                                        new closeRFile(new VarExp("varf"))))))))));
////
////        MyDictionary<String, Type> typeEnv4 = new MyDictionary<String, Type>();
////        try {
////            ex4.typecheck(typeEnv4);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg4 = new PrgState(ex4);
////        IRepository repo4 = new Repository(prg4,"src\\logs\\log4.txt");
////        Controller ctr4 = new Controller(repo4, true);
////
////
////
////        IStmt ex5 = new CompStmt(new VarDeclStmt(new StringValue("x"), new IntType()),
////                                    new CompStmt(new AssignStmt(new StringValue("x"), new ValueExp(new IntValue(5))),
////                                    new CompStmt(new VarDeclStmt(new StringValue("y"), new IntType()),
////                                            new CompStmt(new AssignStmt(new StringValue("y"), new ValueExp(new IntValue(7))),
////                                                    new PrintStmt(new RelationalExp(new ValueExp(new IntValue(5)), new ValueExp(new IntValue(7)),3))))));
////
////        MyDictionary<String, Type> typeEnv5 = new MyDictionary<String, Type>();
////        try {
////            ex5.typecheck(typeEnv5);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg5 = new PrgState(ex5);
////        IRepository repo5 = new Repository(prg5,"src\\logs\\log5.txt");
////        Controller ctr5 = new Controller(repo5, true);
////
////
////        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
////        IStmt ex6 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
////                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(20))),
////                        new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
////                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("v")),
////                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
////
////        MyDictionary<String, Type> typeEnv6 = new MyDictionary<String, Type>();
////        try {
////            ex6.typecheck(typeEnv6);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg6 = new PrgState(ex6);
////        IRepository repo6 = new Repository(prg6,"src\\logs\\log6.txt");
////        Controller ctr6 = new Controller(repo6, true);
////
////        // Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)))
////        IStmt ex7 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
////                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(20))),
////                        new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
////                                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(7))),
////                                        new CompStmt(new HeapWriteStmt(new StringValue("v"), new ValueExp(new IntValue(30))),
////                                                new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));
////        MyDictionary<String, Type> typeEnv7 = new MyDictionary<String, Type>();
////        try {
////            ex7.typecheck(typeEnv7);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg7 = new PrgState(ex7);
////        IRepository repo7 = new Repository(prg7,"src\\logs\\log7.txt");
////        Controller ctr7 = new Controller(repo7, true);
////
////        // int v; v = 4; (while (v > 0) (print(v);v = v - 1));print(v)
////        IStmt ex8 = new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
////                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(4))),
////                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5),
////                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt(new StringValue("v"), new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))),
////
////                                new PrintStmt(new VarExp("v")))));
////        MyDictionary<String, Type> typeEnv8 = new MyDictionary<String, Type>();
////        try {
////            ex8.typecheck(typeEnv8);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg8 = new PrgState(ex8);
////        IRepository repo8 = new Repository(prg8,"src\\logs\\log8.txt");
////        Controller ctr8 = new Controller(repo8, true);
////
////        // int v; Ref int a; v=10;new(a,22);
////        // fork(wH(a,30);v=32;print(v);print(rH(a)));
////        // print(v);print(rH(a))
////        IStmt ex9 = new CompStmt(new VarDeclStmt(new StringValue("v"), new IntType()),
////                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new IntType())),
////                        new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(10))),
////                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new ValueExp(new IntValue(22))),
////                                        new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt(new StringValue("a"), new ValueExp(new IntValue(30))),
////                                                new CompStmt(new AssignStmt(new StringValue("v"), new ValueExp(new IntValue(32))),
////                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a"))))))),
////                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a")))))))));
////    MyDictionary<String, Type> typeEnv9 = new MyDictionary<String, Type>();
////        try {
////        ex9.typecheck(typeEnv9);
////    }
////        catch (Exception e)
////    {
////        System.out.println(e.getMessage());
////        return;
////
////        }
////
////        // Ref int v; Ref( Ref int) a ; new(v,10); new(a,v);
////        // fork(new(v,30); new(v,50) ;print(rH(v)); print(rH(rH(a))));
////        //print(rH(v));print(rH(rH(a))));
////
////        IStmt ex10 = new CompStmt(new VarDeclStmt(new StringValue("v"), new RefType(new IntType())),
////                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new RefType(new IntType()))),
////                        new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(10))),
////                                new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("v")),
////                                        new CompStmt(new ForkStmt(new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(30))),
////                                                new CompStmt(new HeapAllocStmt(new StringValue("v"), new ValueExp(new IntValue(50))),
////                                                        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))),
////                                                                new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))),
////                                                new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))),
////                                                        new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a"))))))))));
////        MyDictionary<String, Type> typeEnv10 = new MyDictionary<String, Type>();
////        try {
////            ex10.typecheck(typeEnv10);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////       PrgState prg9 = new PrgState(ex10);
////        IRepository repo9 = new Repository(prg9,"src\\logs\\log9.txt");
////        Controller ctr9 = new Controller(repo9, true);
////
////        // int counter; Ref int a; while(counter < 10) { fork(fork ({new (a, counter); print(rH(a))})); counter = counter + 1; }
////        IStmt ex11 = new CompStmt(new VarDeclStmt(new StringValue("counter"), new IntType()),
////                new CompStmt(new VarDeclStmt(new StringValue("a"), new RefType(new IntType())),
////                        new CompStmt(new AssignStmt(new StringValue("counter"), new ValueExp(new IntValue(0))),
////                                new WhileStmt(new RelationalExp(new VarExp("counter"), new ValueExp(new IntValue(10)), 1),
////                                        new CompStmt(new ForkStmt(new ForkStmt(new CompStmt(new HeapAllocStmt(new StringValue("a"), new VarExp("counter")),
////                                                new PrintStmt(new HeapReadExp(new VarExp("a")))))),
////                                                new AssignStmt(new StringValue("counter"), new ArithExp(new VarExp("counter"), new ValueExp(new IntValue(1)), 1)))))));
////        MyDictionary<String, Type> typeEnv11 = new MyDictionary<String, Type>();
////        try {
////            ex11.typecheck(typeEnv11);
////        }
////        catch (Exception e)
////        {
////            System.out.println(e.getMessage());
////            return;
////        }
////        PrgState prg10 = new PrgState(ex11);
////        IRepository repo10 = new Repository(prg10,"src\\logs\\log10.txt");
////        Controller ctr10 = new Controller(repo10, true);
////
////
////
////        TextMenu menu = new TextMenu();
////        menu.addCommand(new ExitCommand("0", "exit"));
////        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
////        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
////        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
////        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
////        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
////        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
////        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
////        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
////        menu.addCommand(new RunExample("9", ex10.toString(), ctr9));
////        menu.addCommand(new RunExample("10", ex11.toString(), ctr10));
////        menu.show();
//    }
//}

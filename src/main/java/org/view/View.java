//package view;
//
//import Collection.MyDictionary;
//import Collection.MyIDictionary;
//import Collection.MyList;
//import Collection.MyStack;
//import controller.Controller;
//import exceptions.*;
//import model.PrgState;
//import model.statements.IStmt;
//import model.values.StringValue;
//import model.values.Value;
//
//
//import java.io.BufferedReader;
//import java.io.FileDescriptor;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class View {
//    Controller controller;
//
//    public String readLogFilePath()
//    {
//        Scanner s = new Scanner(System.in);
//        System.out.println("Enter the path of the log file here: ");
//        return s.nextLine();
//
//    }
//    public View(){
//        String logFilePath = readLogFilePath();
//        controller = new Controller(true,logFilePath);
//
//    }
//
//
//
//
//    public void run()
//    {
//        Scanner s = new Scanner(System.in);
//        System.out.println("Choose a sample program: 1, 2, 3 or 4");
//        int sampleNr = s.nextInt();
//        MyStack<IStmt>exeStack = new MyStack<>();
//        MyDictionary<StringValue, Value> symTable = new MyDictionary<>();
//        MyList<Value> out = new MyList<>();
//        MyDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<>();
//
//        PrgState prgState = new PrgState(exeStack,symTable,out,fileTable,null);
//       switch(sampleNr)
//       {
//           case 1:
//               prgState.sample1();
//               break;
//           case 2:
//               prgState.sample2();
//               break;
//           case 3:
//               prgState.sample3();
//               break;
//           case 4:
//               prgState.sample4();
//               break;
//           default:
//               System.out.println("Nonexistent sample id!");
//       }
//        controller.addPrgState(prgState);
//       try {
//           controller.allStep();
//       }
//       catch (EmptyCollectionError | ZeroDivisionError | DeclarationError | ImproperTypeError | IOException e)
//       {
//           System.out.println(e.getMessage());
//       }
//
//
//    }
//
//}

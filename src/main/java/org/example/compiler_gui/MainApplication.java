package org.example.compiler_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Custom Interpreter");

//        VBox root = new VBox();
//        root.setSpacing(10);
//        root.setAlignment(javafx.geometry.Pos.CENTER);
//        root.setPadding(new Insets(50));
//
//        Label label = new Label("Enter your name: ");
//
//        TextField textField = new TextField();
//
//        Button button = new Button("Say Hello");
//
//        ListView<String> listView = new ListView<String>();
//        TextMenu menu = InterpreterLogic();
//        for(Command c : menu.getCommands().values())
//            listView.getItems().addAll(c.getKey() + " : " + c.getDescription());
//
//
//        // get the currently selected item
//        // when an item is selected, change the text of the label with the selected item
//        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
//            label.setText(newValue);
//
//            // get the index of the selected item
//            int index = listView.getSelectionModel().getSelectedIndex();
//            actionWindow(index);
//
//        });
//
//
//        button.setOnAction(actionEvent -> {
//            String name = textField.getText();
//            if (name.isEmpty()) {
//                name = "World";
//            }
//            label.setText("Hello " + name + "!");
//        });
//
//        root.getChildren().addAll(label, textField, button, listView);
//
//        Scene scene = new Scene(root, 520, 540);
//
        // get the AvailablePrgStates ListView
        stage.setScene(scene);
        stage.show();
    }

//    public void actionWindow(,int index)
//    {
//        Stage stage = new Stage();
//        VBox root = new VBox();
//        root.setSpacing(10);
//        root.setAlignment(javafx.geometry.Pos.CENTER);
//        root.setPadding(new Insets(50));
//
//        TextField nrProgStates = new TextField();
//        nrProgStates.setPromptText("Number of program states");
//
//        HBox heapAndSymTable = new HBox();
//
//        heapAndSymTable.setSpacing(10);
//        heapAndSymTable.setAlignment(javafx.geometry.Pos.CENTER);
//        heapAndSymTable.setMaxWidth(Double.MAX_VALUE);
//
//        HBox labels1 = new HBox();
//        labels1.setAlignment(javafx.geometry.Pos.CENTER);
//        labels1.setMaxWidth(Double.MAX_VALUE);
//        HBox.setHgrow(labels1, Priority.ALWAYS);
//        Label heapLabel = new Label("Heap");
//        heapLabel.setAlignment(javafx.geometry.Pos.CENTER);
//        HBox.setHgrow(heapLabel, Priority.ALWAYS);
//        Label symTableLabel = new Label("Symbol Table");
//        symTableLabel.setAlignment(javafx.geometry.Pos.CENTER);
//        HBox.setHgrow(symTableLabel, Priority.ALWAYS);
//        labels1.getChildren().addAll(heapLabel, symTableLabel);
//
//
//        TableView<String> heapTable = new TableView<String>();
//        // set the nr of columns to 2 with headers address and Value
//        // set only 2 columns to be visible
//
//        heapTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
//        heapTable.setMaxWidth(Double.MAX_VALUE);
//        heapTable.getColumns().add(new TableColumn<String, String>("Address"));
//        heapTable.getColumns().add(new TableColumn<String, String>("Value"));
//
//        HBox labels2 = new HBox();
//        labels2.setAlignment(javafx.geometry.Pos.CENTER);
//        labels2.setMaxHeight(Double.MAX_VALUE);
//
//        Label outLabel = new Label("Out Table");
//        Label fileLabel = new Label("File Table");
//        labels2.getChildren().addAll(outLabel, fileLabel);
//
//        HBox outAndFileTable = new HBox();
//        outAndFileTable.setSpacing(10);
//
//
//        ListView<String> outTable = new ListView<String>();
//        ListView<String> fileTable = new ListView<String>();
//        TableView<String> symTable = new TableView<String>();
//
//        outAndFileTable.getChildren().addAll(outTable, fileTable);
//
//// set the nr of columns to 2 with headers variable name and Value
//
//        symTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
//        symTable.setMaxWidth(Double.MAX_VALUE);
//        symTable.getColumns().addAll(new TableColumn<String, String>("Variable Name"), new TableColumn<String, String>("Value"));
//
//        heapAndSymTable.getChildren().addAll(heapTable, symTable);
//
//        HBox labels3 = new HBox();
//        labels3.setAlignment(javafx.geometry.Pos.CENTER);
//        labels3.setMaxHeight(Double.MAX_VALUE);
//        Label exeStackLabel = new Label("Exe Stack");
//        Label prgStatesLabel = new Label("Program States");
//        labels3.getChildren().addAll(exeStackLabel, prgStatesLabel);
//
//        ListView<String> exeStack = new ListView<String>();
//        ListView<String> prgStates = new ListView<String>();
//
//        HBox exeStackAndPrgStates = new HBox();
//        exeStackAndPrgStates.setSpacing(10);
//        exeStackAndPrgStates.getChildren().addAll(exeStack, prgStates);
//
//        Button oneStepBtn = new Button("One Step");
//
//
//        Scene scene = new Scene(root, 520, 540);
//        // add the heapTable and symtable side by side
//        // add the outTable and fileTable side by side
//
//        //include the labels
//        root.getChildren().addAll(nrProgStates, labels1,heapAndSymTable, labels2, outAndFileTable, exeStackAndPrgStates,labels3,  oneStepBtn);
//
//
//        stage.setScene(scene);
//        stage.show();
//    }
//
//

    public static void main(String[] args) {
        launch();
    }
}
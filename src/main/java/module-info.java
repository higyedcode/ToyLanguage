module org.example.compiler_gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.compiler_gui to javafx.fxml;
    exports org.example.compiler_gui;
}
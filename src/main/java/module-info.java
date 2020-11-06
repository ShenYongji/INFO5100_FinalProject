module FinalProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens FinalProject to javafx.fxml;
    exports FinalProject;
}
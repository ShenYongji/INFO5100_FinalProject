package FinalProject;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.*;

import java.io.IOException;


public class main extends Application {


    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainUI.fxml"));
        primaryStage.setTitle("Image Management Tool - INFO 5100");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();


    }


}

package FinalProject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller {


    public Label HeightLabel;
    public Label WeightLabel;
    public Label NameLabel;
    public Label SizeLabel;
    public ImageView Bigimage;
    public ImageView Smallimage;
    public String path;
    public String filename;
    @FXML private AnchorPane app;

    @FXML
    public void close(){
        Platform.exit();
    }
    @FXML
    public void Github_link(){
        String urlString = "https://github.com/ShenYongji/INFO5100_FinalProject";
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Github");
    }

    @FXML
    public void ConvertFormat(ActionEvent actionEvent) {
        if (path != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("Format.fxml"));
            AnchorPane page = null;
            try {
                page = (AnchorPane)loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage convertStage = new Stage();
            convertStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            convertStage.setScene(scene);

            Fcontroller controller = loader.getController();
            controller.setConvertStage(convertStage,path);
            convertStage.showAndWait();
        }
        else{
            System.out.println("cannot convert");
        }
    }

    @FXML
    public void addPicture(ActionEvent actionEvent) {
        Stage stage = (Stage) app.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpeg","*.png", "*.jpg", "*.gif")
                );
        File file = fileChooser.showOpenDialog(stage);
        path = file.getAbsolutePath();
        if(file != null){
            File img = new File(path);
            try{
                InputStream isImage = (InputStream) new FileInputStream(img);
                Image image = new Image(isImage);
                Smallimage.setImage(image);
                Bigimage.setImage(image);
                get_image_info(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void get_image_info(String path){
        smallimage simg = new smallimage(path);
        System.out.println(simg.get_Path());
        System.out.println(simg.get_filename());
        System.out.println(simg.get_curr_format());
        //Set label
        NameLabel.setText(simg.get_filename());
        NameLabel.setWrapText(true);
        SizeLabel.setText(simg.get_data().get("File Size"));
        HeightLabel.setText(simg.get_height().toString()+" Pixels");
        WeightLabel.setText(simg.get_width().toString()+" Pixels");
    }
}

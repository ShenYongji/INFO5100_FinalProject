package FinalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Fcontroller implements Initializable {
    private Stage convertStage;
    public String path;
    public String filename;
    public String curr_format;
    @FXML private RadioButton ButtonJPG;
    @FXML private RadioButton ButtonJPEG;
    @FXML private RadioButton ButtonPNG;
    @FXML private RadioButton ButtonGIF;


    public void setConvertStage(Stage convertStage, String path) {
        this.convertStage = convertStage;
        Path Path = Paths.get(path);
        Path fileName = Path.getFileName();
        this.path = path;
        int index = fileName.toString().lastIndexOf('.');
        if(index > 0) {
            String extension = fileName.toString().substring(index + 1);
            this.curr_format = extension;
        }
        this.filename = fileName.toString().substring(0, fileName.toString().length()-this.curr_format.length()-1);
    }

    public void handelOK(ActionEvent actionEvent){
        javaxt.io.Image image = new javaxt.io.Image(path);

        if(ButtonGIF.isSelected()){
            image.saveAs("./"+filename+".gif");
            convertStage.close();
            alertWindow("GIF");
        }else if(ButtonJPG.isSelected()){
            image.saveAs("./"+filename+".jpg");
            convertStage.close();
            alertWindow("JPG");
        }else if(ButtonJPEG.isSelected()){
            image.saveAs("./"+filename+".jpeg");
            convertStage.close();
            alertWindow("JPEG");
        }else if(ButtonPNG.isSelected()){
            image.saveAs("./"+filename+".png");
            convertStage.close();
            alertWindow("PNG");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Illegal operation");
            alert.setContentText("You have to choose a format before converting!");
            alert.showAndWait();
        }
    }

    public void handelCancel(ActionEvent actionEvent) {
        convertStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        this.ButtonPNG.setToggleGroup(group);
        this.ButtonJPG.setToggleGroup(group);
        this.ButtonJPEG.setToggleGroup(group);
        this.ButtonGIF.setToggleGroup(group);
    }

    public void alertWindow(String s){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulation!");
        alert.setHeaderText(null);
        alert.setContentText("Now you get a " + s +" picture!");
        alert.showAndWait();
    }

}

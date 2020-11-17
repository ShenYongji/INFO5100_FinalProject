package FinalProject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TextArea info;
    @FXML private ImageView Bigimage;
    @FXML private ImageView Smallimage;
    @FXML private AnchorPane filter;
    @FXML private AnchorPane app;

    private smallimage simg;
    private bigimage bimg;

    @FXML
    public void close(){
        Platform.exit();
    }

    @FXML
    public void Github_link(){
        // Check the resource on Github
        String urlString = "https://github.com/ShenYongji/INFO5100_FinalProject";
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Github");
    }

    @FXML
    public void addPicture(ActionEvent actionEvent) {

        // Create a new window that users are allowed to choose images from local
        Stage stage = (Stage) app.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpeg","*.png", "*.jpg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            //Check if the image page is valid or not
            String path = file.getAbsolutePath();
            File img = new File(path);
            try{
                //initialize UI
                //Create two class to store image values
                //bimg: the bigger preview image that shows at the left side
                //sim (original image): the smaller preview image that shows at the top right corner.
                filter.setVisible(false);
                InputStream isImage = new FileInputStream(img);
                Image image = new Image(isImage);
                //Displaying both images on the frontend at the same time
                Smallimage.setImage(image);
                Bigimage.setRotate(0);
                Bigimage.setImage(image);
                //Setting image structure
                bimg = new bigimage(path);
                simg = new smallimage(path);
                //get the information of image sim (bimg may be added some filter)
                get_image_info(simg);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void ConvertFormat(ActionEvent actionEvent) {
        //System.out.println(bimg);
        //Output function
        if (bimg != null){
            //Loading conformation page
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("Format.fxml"));
            AnchorPane page = null;
            try {
                page = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage convertStage = new Stage();
            convertStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            convertStage.setScene(scene);
            Fcontroller controller = loader.getController();
            //Passing UI and class bigimage
            controller.setConvertStage(convertStage,bimg);
            convertStage.showAndWait();
        }
        else{
            System.out.println("cannot convert");
        }
    }

    @FXML
    public void LeftRotate(ActionEvent actionEvent) {
        //Filter Function
        //Rotate left by 30 degree
        System.out.println("Left Rotate by 30");
        bimg.set_rotate(-30);
        System.out.println(bimg.get_rotate());
        //real-time changing the preview on the frondend
        Bigimage.setRotate(Bigimage.getRotate() - 30);
    }

    @FXML
    public void RightRotate(ActionEvent actionEvent) {
        //Filter Function
        //Rotate right by 30 degree
        System.out.println("Right Rotate by 30");
        bimg.set_rotate(30);
        System.out.println(bimg.get_rotate());
        //real-time changing the preview on the frondend
        Bigimage.setRotate(Bigimage.getRotate() + 30);
    }

    @FXML
    public void imageFilter(ActionEvent actionEvent){
        //Displaying the UI of Filter.
        //System.out.println(bimg);
        if (bimg != null) {
            filter.setVisible(!filter.isVisible());
        }
        else{
            System.out.println("cannot launch image filter");
        }
    }


    public void get_image_info(smallimage simg){
        //To get the information of image that user select from image structure.
        System.out.println("New Image");
        System.out.println(simg.get_Path());
        System.out.println(simg.get_filename_extension());
        System.out.println(simg.get_filename());
        System.out.println(simg.get_curr_format());
        info.setText(
                "File Name: " +simg.get_filename_extension() +"\n"+
                "File Size:  " + simg.get_data().get("File Size")+ "\n" +
                "Image Height: " + simg.get_height().toString()+" Pixels"+ "\n" +
                "Image Width: "+simg.get_width().toString()+" Pixels");
        info.setWrapText(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filter.setVisible(false);
    }
}

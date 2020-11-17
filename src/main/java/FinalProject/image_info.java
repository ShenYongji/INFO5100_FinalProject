package FinalProject;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import javaxt.io.Image;


public class image_info {
    //The data structure of image
    //Parent Class to store basic information of image
    private String Path;
    private String filename_extension;
    private String filename;
    private String curr_format;
    private javaxt.io.Image image;

    public Integer get_height(){
        return image.getHeight();
    }
    public Integer get_width(){
        return image.getWidth();
    }
    public String get_Path(){
        return Path;
    }
    public String get_filename_extension(){
        return filename_extension;
    }
    public String get_filename(){
        return filename;
    }
    public String get_curr_format(){ return curr_format; }
    public javaxt.io.Image get_image(){
        return image;
    }

    public image_info(String Path){
        Path path = Paths.get(Path);
        Path fileName = path.getFileName();
        this.Path = path.toString();
        this.image = new Image(Path);
        this.filename_extension = fileName.toString();
        int index = fileName.toString().lastIndexOf('.');
        if(index > 0) {
            String extension = fileName.toString().substring(index + 1);
            this.curr_format = extension;
        }
        this.filename = fileName.toString().substring(0, fileName.toString().length()-this.curr_format.length()-1);
    }
}

class smallimage extends image_info{
    //Class smallimage extends from its parent class - image_info
    //Contain the description of image itself
    private String s_path;
    //Map<String,String> data stores Metadata from ImageMetadataReader
    //Image Size, Location... will be in the Map<String,String> data
    private Map<String,String> data = new HashMap<>();
    public Map<String, String> get_data() {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(s_path));
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    data.put(tag.getTagName(), tag.getDescription());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }

    public smallimage(String Path) {
        super(Path);
        this.s_path = get_Path();
        }
    }

class bigimage extends image_info{
    //Class bigimage extends from its parent class - image_info
    //Contain the modifications of bigger image from the "Filter" functions
    private int rotate = 0;
    public void set_rotate(int n){
        rotate = rotate + n;
    }
    public int get_rotate(){
        return rotate;
    }
    public bigimage(String Path) {
        super(Path);
    }
}

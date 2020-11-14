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
    public String Path;
    public String filename;
    javaxt.io.Image image;

    public Integer get_height(){
        return image.getHeight();
    }
    public Integer get_width(){
        return image.getWidth();
    }
    public String get_Path(){
        return Path;
    }
    public String get_filename(){
        return filename;
    }

    public image_info(String Path){
        Path path = Paths.get(Path);
        Path fileName = path.getFileName();
        this.Path = path.toString();
        this.filename = fileName.toString();
        this.image = new Image(Path);
    }
}

class smallimage extends image_info{
    public String curr_format;
    public Map<String,String> data = new HashMap<>();


    public String get_curr_format(){
        return curr_format;
    }
    public Map<String, String> get_data() {

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(Path));
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    data.put(tag.getTagName(), tag.getDescription());
                }
            }
        }catch (JpegProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }

    public smallimage(String Path) {
        super(Path);
        int index = this.filename.lastIndexOf('.');
        if(index > 0) {
            String extension = this.filename.substring(index + 1);
            this.curr_format = extension;
        }
    }
}

class bigimage extends image_info{

    public bigimage(String Path) {
        super(Path);
    }
}

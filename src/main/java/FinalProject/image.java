package FinalProject;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.*;
import com.drew.metadata.Tag;

public class image {

    private Map<String,String> data = new HashMap<>();
    private String fileName;

    public image(String s){
        this.fileName = s;
    }
    //return the private field data
    public Map<String, String> getData() {
        return data;
    }
    //once finished this function, you can use Hashmap data to find an image element
    public void getImage(){

        //identify the file, you need to change to your local path
        File img = new File(fileName);
        Metadata metadata;
        try {
            //Get the metadata
            metadata = JpegMetadataReader.readMetadata(img);
            //put data in a Hashmap
            //key is the tag name
            //value is the content
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    data.put(tag.getTagName(),tag.getDescription());
                }

            }
        } catch (JpegProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


}

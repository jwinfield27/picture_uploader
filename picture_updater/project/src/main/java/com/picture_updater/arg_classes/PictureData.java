package com.picture_updater.arg_classes;

import java.util.Map;
import java.util.HashMap;

public class PictureData {

    private Map<String, String> picture_map; // <name_str,path_str>
    private static PictureData picture_data;

    private PictureData(){
        picture_map = new HashMap<>();
        setPictureEntry("test_dog");
    }

    private void setPictureEntry(String name){
        picture_map.put(name,"test/sprites/" + name);
    }

    public static PictureData getInstance(){
        if(picture_data == null){
            picture_data = new PictureData();
        }
        return picture_data;
    }

    public Map<String, String> getPicturePathMap(){
        return picture_data.picture_map; 
    }
}
